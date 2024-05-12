package com.policy.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy =IdProofNoValidator.class )
@Retention(RUNTIME)
@Target(FIELD)
public @interface ValidateIdProofNo {
	
	String message() default "Subscriber already exists";
	Class<?>[]groups() default{};
	Class<? extends Payload>[]payload() default{};

}
