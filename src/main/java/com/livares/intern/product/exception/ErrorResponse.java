package com.livares.intern.product.exception;

import lombok.Getter;
import lombok.Setter;


// The response that will be reflected in the API in case of any error/exception.
@Getter
@Setter
public class ErrorResponse {
	private int errorcode;	
	private String message;
	private Boolean success;
	private Object errorData;
}
