package com.nagarro.javaTest.helper;

public class BadRequestException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7549507070915137186L;

	private String message;
	
	public BadRequestException(String message) {
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}
