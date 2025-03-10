package com.itheima.anno;

import com.itheima.validation.StateValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Documented//元注解
@Target({FIELD})//元注解
@Retention(RUNTIME)//元注解，表示该注解保持到什么阶段
@Constraint(validatedBy = {StateValidation.class})//指定提供校验规则的类
public @interface State {
    //提供校验失败后的提示信息
    String message() default "state参数的值只能是已发布或草稿";
    //指定分组
    Class<?>[] groups() default {};
    //负载，获取state注解的附加信息
    Class<? extends Payload>[] payload() default {};
}
