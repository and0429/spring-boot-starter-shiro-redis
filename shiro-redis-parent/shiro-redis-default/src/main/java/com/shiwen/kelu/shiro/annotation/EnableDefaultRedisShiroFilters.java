package com.shiwen.kelu.shiro.annotation;

import com.shiwen.kelu.shiro.filter.DefaultFiltersConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author: zhangkai
 * @date: 2019-04-23
 * @time 11:05
 * @Title: EnableDefaultRedisFilters.java
 * @since 1.0.5
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(DefaultFiltersConfig.class)
@Configuration
public @interface EnableDefaultRedisShiroFilters {
}
