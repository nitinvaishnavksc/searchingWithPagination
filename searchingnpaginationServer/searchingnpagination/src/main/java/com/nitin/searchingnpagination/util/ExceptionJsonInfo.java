package com.nitin.searchingnpagination.util;

import java.util.Optional;

import org.springframework.http.HttpStatus;

public class ExceptionJsonInfo {

	private String message;
	private int statusCode;
	private String statusName;

	/* Contractor to show exception specific info */ 	
	public ExceptionJsonInfo(String message, int statusCode, String statusName)
	{ 
		this.message = message; 
		this.statusCode = statusCode; 
		this.statusName = statusName; 		 
	}
	
	/* Contractor to show exception specific info */ 	
	public ExceptionJsonInfo(String message, HttpStatus httpStatus)
	{ 
		this.message = message; 
		this.statusCode = httpStatus.value(); 
		this.statusName = httpStatus.name(); 		 
	}
	
	/* Contractor to show global exception ( internal server error with actual message ) */ 
	public ExceptionJsonInfo(Exception exception)
	{ 
		final String exceptionMessage = Optional.of(exception.getMessage())
				.orElse(exception.getClass().getSimpleName()); 
		
		this.message = exceptionMessage; 
		this.statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value(); 
		this.statusName = HttpStatus.INTERNAL_SERVER_ERROR.name(); 		 
	}	

	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

}

