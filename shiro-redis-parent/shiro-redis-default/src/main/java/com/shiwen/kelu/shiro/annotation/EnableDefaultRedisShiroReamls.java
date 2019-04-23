package com.shiwen.kelu.shiro.annotation;

import com.shiwen.kelu.shiro.reaml.DefaultReamlsConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author: zhangkai
 * @date: 2019-04-23
 * @time 11:11
 * @Title: EnableDefaultRedisShiroReamls.java
 * @since 1.0.5
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.TYPE)
@Import(DefaultReamlsConfig.class)
@Configuration
public @interface EnableDefaultRedisShiroReamls {

}
