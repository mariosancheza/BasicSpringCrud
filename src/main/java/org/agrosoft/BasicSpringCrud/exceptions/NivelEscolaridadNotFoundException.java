package org.agrosoft.BasicSpringCrud.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NO_CONTENT)
public class NivelEscolaridadNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public NivelEscolaridadNotFoundException(String message) {
		super(message);
	}
}
