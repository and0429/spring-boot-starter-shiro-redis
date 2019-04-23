package com.shiwen.kelu.shiro.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName: EnableDefaultShiro
 * @Author zhangkai
 * @Date 2018年7月18日
 * @Time 下午6:00:34
 */
@Inherited
@Documented
@Target(value = ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Deprecated
@EnableDefaultShiroReamlsAndFilters
public @interface EnableDefaultShiro {

}
