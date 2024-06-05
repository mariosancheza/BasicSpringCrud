package org.agrosoft.BasicSpringCrud.utils;

import java.util.Set;
import java.util.TreeSet;

import org.springframework.validation.Errors;

public class ErrorUtils {
	public static Set<String> errorsToStringSet(Errors errores) {
		Set<String> errorSet = new TreeSet<>();
		errores.getFieldErrors().forEach( error -> errorSet.add(error.getDefaultMessage()) );
		return errorSet;
	}
}
