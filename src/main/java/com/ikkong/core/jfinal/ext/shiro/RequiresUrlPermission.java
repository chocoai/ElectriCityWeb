package com.ikkong.core.jfinal.ext.shiro;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.apache.shiro.authz.annotation.Logical;

/**
 * 根据URL检查权限
 * Created by ikkong on 2016/11/22.
 */

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface RequiresUrlPermission {
    String[] value() default "";

    Logical logical() default Logical.AND;
}
