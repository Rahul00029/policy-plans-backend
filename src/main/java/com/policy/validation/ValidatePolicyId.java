package com.policy.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy =PolicyIdValidator.class )
@Retention(RUNTIME)
@Target(FIELD)
public @interface ValidatePolicyId {
	String message() default "Policy Id already exists";
	Class<?>[]groups() default{};
	Class<? extends Payload>[]payload() default{};
}
