package org.worldcooking.validator;

import java.util.Collection;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NonEmptyListValidator implements
		ConstraintValidator<NonEmptyListConstraint, Collection<?>> {

	@Override
	public void initialize(NonEmptyListConstraint constraintAnnotation) {
	}

	@Override
	public boolean isValid(Collection<?> collection,
			ConstraintValidatorContext context) {
		for (Object o : collection) {
			if (o != null) {
				if (o instanceof String) {
					// non null string
					if (!((String) o).trim().isEmpty()) {
						// non empty string
						return true;
					}
				} else {
					// non null object
					return true;
				}
			}
		}
		return false;
	}

}