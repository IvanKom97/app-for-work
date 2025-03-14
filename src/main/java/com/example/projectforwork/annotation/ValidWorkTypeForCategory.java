package com.example.projectforwork.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = WorkTypeValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidWorkTypeForCategory {
    String message() default "РАБОТА НЕ ОТНОСИТСЯ К ДАННОЙ КАТЕГОРИИ";
}
