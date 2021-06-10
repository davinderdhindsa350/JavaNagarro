package com.nagarro.javaTest.helper;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class AbstractReportsController {

	protected ResponseEntity<ByteArrayResource> sendOKReport(byte[] bytes) {
		ByteArrayResource out = new ByteArrayResource(bytes);
		return new ResponseEntity<ByteArrayResource>(out, HttpStatus.OK);
	}
	
	protected ResponseEntity<ByteArrayResource> sendOKReport(String responseData) {
		return sendOKReport(responseData.getBytes());
	}
	
	protected ResponseEntity<ByteArrayResource> sendErrorReport(byte[] errorBytes) {
		ByteArrayResource out = new ByteArrayResource(errorBytes);
		return new ResponseEntity<ByteArrayResource>(out, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
