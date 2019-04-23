package com.shiwen.kelu.shiro.annotation;

import java.lang.annotation.*;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.shiwen.kelu.shiro.filter.DefaultFiltersConfig;

/**
 * @author: zhangkai
 * @date: 2019-04-23
 * @time 11:05
 * @Title: EnableDefaultRedisFilters.java
 * @see EnableDefaultRedisShiroFilters
 * @deprecated EnableDefaultRedisShiroFilters
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Configuration
@Deprecated
public @interface EnableDefaultRedisFilters {
}
