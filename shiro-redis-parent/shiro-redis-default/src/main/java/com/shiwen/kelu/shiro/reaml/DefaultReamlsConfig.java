package com.shiwen.kelu.shiro.reaml;

import java.util.Objects;

import com.shiwen.kelu.shiro.userdetails.UserDetailService;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.shiwen.shiro.properties.ShiroHashConfig;

@Configuration
@EnableConfigurationProperties({ShiroHashConfig.class})
public class DefaultReamlsConfig {

    /**
     * @param shiroHashConfig
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    public CredentialsMatcher matcher(ShiroHashConfig shiroHashConfig) {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        String algorithmName = shiroHashConfig.getAlgorithmName();
        if (Objects.nonNull(algorithmName))
            matcher.setHashAlgorithmName(algorithmName);
        int hashIterations = shiroHashConfig.getHashIterations();
        matcher.setHashIterations(hashIterations);
        return matcher;
    }

    /**
     * @return
     */
    @Bean
    public DefaultDbRealm defaultDbRealm(ShiroHashConfig shiroHashConfig, UserDetailService userdetailsService) {
        DefaultDbRealm realm = new DefaultDbRealm(userdetailsService);
        realm.setCredentialsMatcher(matcher(shiroHashConfig));
        return realm;
    }

}
