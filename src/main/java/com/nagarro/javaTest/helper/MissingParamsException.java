package com.nagarro.javaTest.helper;

public class MissingParamsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7579566985483271783L;
	
	public MissingParamsException() {}
	
	public MissingParamsException(String message) {
		super(message);
	}
	
}
