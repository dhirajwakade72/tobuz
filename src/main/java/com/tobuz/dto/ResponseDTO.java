package com.tobuz.dto;

public class ResponseDTO<T>{
	
	private T result;
	private String status;
	private String message;
	public ResponseDTO(){}
	
	public ResponseDTO(T result, String status, String message) {
		super();
		this.result = result;
		this.status = status;
		this.message = message;
	}

	public T getResult() {
		return result;
	}
	public void setResult(T result) {
		this.result = result;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
