package org.agrosoft.BasicSpringCrud.validators;

import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;
import jakarta.validation.constraints.NotNull;

@Documented
@Constraint(validatedBy = EnumValidatorImpl.class)
@NotNull(message = "Ignored")
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@ReportAsSingleViolation
public @interface EnumValidator {
	public abstract Class<?>[] groups() default{};
	Class<? extends Payload>[] payload() default {};
	
	Class<? extends java.lang.Enum<?>> enumClass();
	
	public abstract boolean ignoreCase() default true;
	public abstract boolean ignoreAccentMark() default true;
	public String message() default "El valor actual no puede ser aceptado porque es nulo, vacío o inválido";
}
