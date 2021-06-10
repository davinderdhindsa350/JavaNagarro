package com.nagarro.javaTest.helper;

import java.util.Map;

public class GlobalApiResponseEntity {

	public static enum API_STATUSES {
		SUCCESS, FAILURE;
	}

	private Integer statusCode;
	private String status;
	private String statusMessage;
	private Object data;
	private Map<String, Object> additionalAttributes;
	
	public GlobalApiResponseEntity() {
	}
	
	public static GlobalApiResponseEntity getInstance() {
		return getSueccessInstance(null);
	}
	
	/**
	 * @param data	--	Successful Response
	 */
	public static GlobalApiResponseEntity getSueccessInstance(Object data) {
		GlobalApiResponseEntity response = new GlobalApiResponseEntity();
		response.setStatusCode(200);
		response.setStatus(API_STATUSES.SUCCESS.toString());
		response.setStatusMessage(API_STATUSES.SUCCESS.toString());
		response.setData(data);
		return response;
	}
	
	public static GlobalApiResponseEntity getFailureInstance(String statusMessage) {
		GlobalApiResponseEntity response = new GlobalApiResponseEntity();
		response.setStatusCode(500);
		response.setStatus(API_STATUSES.FAILURE.toString());
		response.setStatusMessage(statusMessage);
		return response;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Map<String, Object> getAdditionalAttributes() {
		return additionalAttributes;
	}

	public void setAdditionalAttributes(Map<String, Object> additionalAttributes) {
		this.additionalAttributes = additionalAttributes;
	}

}
