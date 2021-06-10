package com.nagarro.javaTest.helper;

import java.util.Map;
import java.util.Map.Entry;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.nagarro.javaTest.Utils;


public abstract class AbstractRestController {
	
	private ResponseEntity<GlobalApiResponseEntity> addHeadersAndSendResponse(GlobalApiResponseEntity response, Map<String, String> customHeaders){
		HttpHeaders headers = new HttpHeaders();
		if( !Utils.isEmpty(customHeaders) ) {
			for(Entry<String, String> keyValue : customHeaders.entrySet()) {
				headers.add(keyValue.getKey(), keyValue.getValue());
			}
		}
		return new ResponseEntity<GlobalApiResponseEntity>(response, headers, HttpStatus.OK);
	}

	protected ResponseEntity<GlobalApiResponseEntity> getOKResponse(String message){
		return getOKResponse(null, message);
	}
	
	protected ResponseEntity<GlobalApiResponseEntity> getOKResponse(Object data) {
		return getOKResponse(data, "SUCCESS");
	}

	protected ResponseEntity<GlobalApiResponseEntity> getOKResponse(Object data, String message) {
		return getOKResponse(data, message, null);
	}

	protected ResponseEntity<GlobalApiResponseEntity> getOKResponse(Object data, String message,
			Map<String, Object> additionalAttributes) {
		GlobalApiResponseEntity response = GlobalApiResponseEntity.getSueccessInstance(data);
		response.setStatusMessage(message);
		response.setAdditionalAttributes(additionalAttributes);
		return addHeadersAndSendResponse(response, null);
	}

	protected ResponseEntity<GlobalApiResponseEntity> missingParamResponse(String param) {
		GlobalApiResponseEntity response = GlobalApiResponseEntity.getFailureInstance("Missing Param : " + param);
		response.setStatusCode(HttpStatus.BAD_REQUEST.value());
		return addHeadersAndSendResponse(response, null);
	}
	
	protected ResponseEntity<GlobalApiResponseEntity> getExceptionResponse(Exception ex){
		GlobalApiResponseEntity response = GlobalApiResponseEntity.getFailureInstance(ex.getClass().getName() + "\n" + ex.getMessage());
		return addHeadersAndSendResponse(response, null);
	}
	
	protected ResponseEntity<GlobalApiResponseEntity> getThrowableResponse(Throwable th) {
		GlobalApiResponseEntity response = GlobalApiResponseEntity.getFailureInstance("Failed : Please Contact Production Support");
		return addHeadersAndSendResponse(response, null);
	}
	
	protected ResponseEntity<GlobalApiResponseEntity> getBadRequestResponse(String messageToUser) {
		GlobalApiResponseEntity response = GlobalApiResponseEntity.getFailureInstance(messageToUser);
		response.setStatusCode(HttpStatus.BAD_REQUEST.value());
		return addHeadersAndSendResponse(response, null);
	}
	
}
