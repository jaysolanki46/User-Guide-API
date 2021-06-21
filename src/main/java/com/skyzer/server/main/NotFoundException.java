package com.skyzer.server.main;

import org.springframework.http.HttpStatus;  
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)  
public class NotFoundException extends RuntimeException {

	/**
	 * Auto-Generated
	 */
	private static final long serialVersionUID = 1L;

	public NotFoundException(String message) {
		super(message);
	}
	
}
