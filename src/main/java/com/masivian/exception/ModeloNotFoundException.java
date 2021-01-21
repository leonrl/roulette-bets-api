package com.masivian.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ModeloNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 6866192010707710952L;

	public ModeloNotFoundException(String mensaje) {
		super(mensaje);
	}

}
