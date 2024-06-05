package org.agrosoft.BasicSpringCrud.validators;

import java.text.Normalizer;
import java.text.Normalizer.Form;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EnumValidatorImpl implements ConstraintValidator<EnumValidator,String> {
	
	private EnumValidator annotation;
	
	@Override
	public void initialize(EnumValidator annotation) {
		this.annotation = annotation;
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		boolean result = false;
		Object[] enumValues = this.annotation.enumClass().getEnumConstants();
		
		String valueForValidation = this.annotation.ignoreAccentMark() ? normalize(value) : value.trim();
		valueForValidation = valueForValidation.replace(" ", "_");
		
		for( Object enumValue : enumValues ) {
			if( (this.annotation.ignoreCase() && enumValue.toString().equalsIgnoreCase(valueForValidation)) || (enumValue.toString().equals(valueForValidation)) ) {
				result = true;
				break;
			}
		}
		
		return result;
	}
	
	//quita acentos
	private String normalize(String texto) {
		return Normalizer.normalize(texto, Form.NFD)
				.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
	}

}
