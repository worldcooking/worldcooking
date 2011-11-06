package org.worldcooking.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NonEmptyListValidator.class)
public @interface NonEmptyListConstraint {
	String message() default "This list should not be empty.";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}