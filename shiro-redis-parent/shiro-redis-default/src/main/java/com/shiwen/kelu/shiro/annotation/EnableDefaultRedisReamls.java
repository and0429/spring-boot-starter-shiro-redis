package com.shiwen.kelu.shiro.annotation;

import java.lang.annotation.*;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.shiwen.kelu.shiro.reaml.DefaultReamlsConfig;

/**
 * @author: zhangkai
 * @date: 2019-04-23
 * @time 11:13
 * @Title: EnableDefaultRedisReamls.java
 * @deprecated EnableDefaultRedisShiroReamls
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.TYPE)
@Configuration
@Inherited
@EnableDefaultRedisShiroReamls
public @interface EnableDefaultRedisReamls {

}
