package com.seshu.test;

public class TestDTO {
	
	private String status;
	private String message;
	private ResultDTO result;
	
	public ResultDTO getResult() {
		return result;
	}
	public void setResult(ResultDTO result) {
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