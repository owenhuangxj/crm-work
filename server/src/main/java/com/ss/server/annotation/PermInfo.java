package com.ss.server.annotation;

import java.lang.annotation.*;

@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PermInfo {
    /**
     * 权限值
     * @return
     */
    String permVal() default "";

    /**
     * 权限名称
     * @return
     */
    String permName() default "";

    /**
     * 权限名称，permName的别名
     * @return
     */
    String value()  default "";
}
