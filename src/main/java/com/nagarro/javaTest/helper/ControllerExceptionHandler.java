package com.nagarro.javaTest.helper;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 
 * Do not add Logger in this class. Aspect will take care of logging. Just make
 * proper response here which can be different for different kind of exceptions.
 */

@ControllerAdvice
public class ControllerExceptionHandler extends AbstractRestController {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<GlobalApiResponseEntity> handleException(Exception e) {
		return getExceptionResponse(e);
	}

	@ExceptionHandler(MissingParamsException.class)
	public ResponseEntity<GlobalApiResponseEntity> handleExceptionForMissingParameter(MissingParamsException ex) {
		return missingParamResponse(ex.getMessage());
	}

	
	
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<GlobalApiResponseEntity> handleBadRequest(BadRequestException e) {
		return getBadRequestResponse(e.getMessage());
	}
}
