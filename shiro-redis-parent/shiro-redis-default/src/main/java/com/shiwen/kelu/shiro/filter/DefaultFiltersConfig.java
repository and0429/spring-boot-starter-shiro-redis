package com.shiwen.kelu.shiro.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * @author zhangkai
 *
 */
@Configuration
@SuppressWarnings({ "rawtypes", "unchecked" })
public class DefaultFiltersConfig {

	/**
	 * 
	 * @return
	 */
	@Bean
	public RestFulAuthenticationFilter restAuthc() {
		return new RestFulAuthenticationFilter();
	}

	@Bean
	public RestFulLogoutFilter restLogout() {
		return new RestFulLogoutFilter();
	}

	/**
	 * 取消filter自动注册 (spring-boot 会自动将bean工厂中的filter加入到过滤器链中)
	 * 
	 * @return
	 */
	@Bean
	public FilterRegistrationBean filterRegistrationBeanDefualt() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(restAuthc());
		registration.setEnabled(false);
		return registration;
	}

	@Bean
	public FilterRegistrationBean filterRegistrationBeanLogout() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(restLogout());
		registration.setEnabled(false);
		return registration;
	}

}
