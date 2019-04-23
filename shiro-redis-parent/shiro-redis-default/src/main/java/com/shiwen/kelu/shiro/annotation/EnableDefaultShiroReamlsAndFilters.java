package com.shiwen.kelu.shiro.annotation;

import java.lang.annotation.*;

/**
 * @ClassName: EnableDefaultShiro
 * @Author zhangkai
 * @Date 2018年7月18日
 * @Time 下午6:00:34
 * @since 1.0.5
 */
@Inherited
@Documented
@Target(value = ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@EnableDefaultRedisShiroFilters
@EnableDefaultRedisShiroReamls
public @interface EnableDefaultShiroReamlsAndFilters {

}
