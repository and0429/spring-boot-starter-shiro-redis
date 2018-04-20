package com.shiwen.shiro;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.shiro.authc.pam.AbstractAuthenticationStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.ShiroHttpSession;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.DelegatingFilterProxy;

import com.shiwen.shiro.properties.RealmsConfig;
import com.shiwen.shiro.properties.RedisFiltersConfig;
import com.shiwen.shiro.properties.ShiroCookieConfig;
import com.shiwen.shiro.properties.ShiroFilterChainMap;
import com.shiwen.shiro.properties.ShiroRedisConfig;

/**
 * spring.factories:
 * org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
 * com.shiwen.shiro.ShiroConfig
 * 
 * @author zhangkai
 * 
 */
@Configuration
@EnableConfigurationProperties({ ShiroFilterChainMap.class, ShiroRedisConfig.class, ShiroCookieConfig.class, RealmsConfig.class,
		RedisFiltersConfig.class })
@Import(ShiroAnnotationsSupportConfig.class)
public class ShiroConfig {

	private static final Logger logger = LoggerFactory.getLogger(ShiroConfig.class);

	private ShiroFilterChainMap shiroFilterChainMap;
	private ShiroRedisConfig shiroRedisConfig;
	private ShiroCookieConfig shiroCookieConfig;

	private RealmsConfig realms;

	@Autowired(required = false)
	private RedisFiltersConfig filters;

	private ApplicationContext context;

	/**
	 * Constrcator
	 * 
	 * @param shiroFilterChainMap
	 * @param realms
	 */
	public ShiroConfig(ApplicationContext context, ShiroFilterChainMap shiroFilterChainMap, RealmsConfig realms,
			ShiroRedisConfig shiroRedisConfig, ShiroCookieConfig shiroCookieConfig) {
		this.context = context;
		this.realms = realms;
		this.shiroFilterChainMap = shiroFilterChainMap;
		this.shiroRedisConfig = shiroRedisConfig;
		this.shiroCookieConfig = shiroCookieConfig;
		if (logger.isInfoEnabled())
			logger.info("shiro filter chain map <==== \n{} ", this.shiroFilterChainMap.getMap());
	}

	/**
	 * 
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	public RedisCacheManager cacheManager() {
		RedisCacheManager redisCacheManager = new RedisCacheManager();
		redisCacheManager.setRedisManager(redisManager());
		return redisCacheManager;
	}

	/**
	 * 
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	public SessionManager sessionManager() {
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		sessionManager.setSessionDAO(redisSessionDAO());
		Cookie sessionIdCookie = new SimpleCookie(ShiroHttpSession.DEFAULT_SESSION_ID_NAME);
		String sessionIdCookieName = shiroCookieConfig.getSessionIdCookieName();
		if (sessionIdCookieName != null)
			sessionIdCookie.setName(sessionIdCookieName);
		reSetCookie(sessionIdCookie);
		sessionManager.setSessionIdCookie(sessionIdCookie);
		return sessionManager;
	}

	/**
	 * 
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	public RememberMeManager rememberMeManager() {
		CookieRememberMeManager manager = new CookieRememberMeManager();
		Cookie cookie = new SimpleCookie(CookieRememberMeManager.DEFAULT_REMEMBER_ME_COOKIE_NAME);
		String rememberMeCookieName = shiroCookieConfig.getRememberMeCookieName();
		if (rememberMeCookieName != null)
			cookie.setName(rememberMeCookieName);
		reSetCookie(cookie);
		manager.setCookie(cookie);
		return manager;
	}

	/**
	 * 
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	public SessionDAO redisSessionDAO() {
		RedisSessionDAO sessionDao = new RedisSessionDAO();
		sessionDao.setRedisManager(redisManager());
		SessionIdGenerator sessionIdGenerator = sessionIdGenerator();
		if (sessionIdGenerator != null)
			sessionDao.setSessionIdGenerator(sessionIdGenerator);
		return sessionDao;
	}

	/**
	 * 
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	public SessionIdGenerator sessionIdGenerator() {
		return null;
	}

	/**
	 * 
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	public RedisManager redisManager() {
		RedisManager redisManager = new RedisManager();
		redisManager.setHost(shiroRedisConfig.getHost());
		redisManager.setPort(shiroRedisConfig.getPort());
		redisManager.setExpire(new Long(shiroRedisConfig.getExpire()).intValue());
		redisManager.setTimeout(new Long(shiroRedisConfig.getTimeout()).intValue());
		redisManager.setDatabase(shiroRedisConfig.getDatabase());
		String pwd = shiroRedisConfig.getPassword();
		if (pwd != null)
			redisManager.setPassword(pwd);
		return redisManager;
	}

	/**
	 * 
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	public DefaultWebSecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		if (realms != null && !CollectionUtils.isEmpty(realms.get(context)))
			securityManager.setRealms(realms.get(context));
		securityManager.setSessionManager(sessionManager());
		securityManager.setRememberMeManager(rememberMeManager());
		securityManager.setCacheManager(cacheManager());
		securityManager.setAuthenticator(authenticator());
		return securityManager;
	}

	/**
	 * 使用方在容器中没有定义realms策略， 就使用默认的策略
	 * 
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean(type = "org.apache.shiro.authc.pam.AbstractAuthenticationStrategy")
	public AbstractAuthenticationStrategy authenticationStrategy() {
		return null;
	}

	/*
	 * 
	 */
	@Bean
	public ModularRealmAuthenticator authenticator() {
		ModularRealmAuthenticator authenticator = new ModularRealmAuthenticator();
		if (realms != null && !CollectionUtils.isEmpty(realms.get(context)))
			authenticator.setRealms(realms.get(context));
		AbstractAuthenticationStrategy authenticationStrategy = authenticationStrategy();
		if (authenticationStrategy != null) // default if no bean in spring application.
			authenticator.setAuthenticationStrategy(authenticationStrategy);
		if (realms != null && !CollectionUtils.isEmpty(realms.get(context)))
			authenticator.setRealms(realms.get(context));
		return authenticator;
	}

	/**
	 * 
	 * @return
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws ClassNotFoundException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 */
	@Bean(name = ConfigConstants.SHIRO_FILTER_NAME)
	@ConditionalOnMissingBean
	public ShiroFilterFactoryBean shiroFilterFactoryBean() {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		SecurityManager securityManager = securityManager();
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		if (filters != null && filters.size() > 0)
			shiroFilterFactoryBean.setFilters(filters.get(context));
		String filterChainDefinitions = this.shiroFilterChainMap.getMap();
		if (!StringUtils.isEmpty(filterChainDefinitions))
			shiroFilterFactoryBean.setFilterChainDefinitions(filterChainDefinitions);
		return shiroFilterFactoryBean;
	}

	/**
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new DelegatingFilterProxy());
		Map<String, String> initParameters = Collections.singletonMap("targetFilterLifecycle", "true");
		filterRegistrationBean.setInitParameters(initParameters);
		filterRegistrationBean.setName(ConfigConstants.SHIRO_FILTER_NAME);
		Collection<String> urlPatterns = Stream.of("/*").collect(Collectors.toList());
		filterRegistrationBean.setUrlPatterns(urlPatterns);
		return filterRegistrationBean;
	}

	private interface ConfigConstants {
		String SHIRO_FILTER_NAME = "shiroFilter";
	}

	/**
	 * 
	 * @param cookie
	 */
	private void reSetCookie(Cookie cookie) {
		String dm = shiroCookieConfig.getDomain();
		if (dm != null)
			cookie.setDomain(dm);
		String path = shiroCookieConfig.getPath();
		if (path != null && path.startsWith("/"))
			cookie.setPath(path);
	}

}
