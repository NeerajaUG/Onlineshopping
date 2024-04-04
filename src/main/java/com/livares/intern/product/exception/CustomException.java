package com.livares.intern.product.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import lombok.Getter;
import lombok.Setter;

/**
* Custom Exception class to handle the errors/exception
*/
@Getter
@Setter
public class CustomException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
    private HttpStatusCode httpStatus;
    private Integer errorCode;
    
    public CustomException(String message) {
        super(message);
    }

    public CustomException(Integer error, String message) {
        super(message);
        this.errorCode = error;
        this.httpStatus = HttpStatus.valueOf(error);
    }

    public CustomException(Integer error, Exception ex) {
        super(ex);
        this.errorCode = error;
        this.httpStatus = HttpStatus.valueOf(error);
    }

    public CustomException(Exception ex) {
        super(ex);
        this.errorCode = ErrorCode.INTERNAL_SERVER_ERROR.getCode();
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public CustomException(ErrorCode errorCode, String message) {
        this(errorCode.getCode(), message);
    }
    
    public CustomException(HttpStatusCode status, ErrorCode errorCode, String message) {
        this(errorCode, message);
        this.httpStatus = status;
    }

    public CustomException(ErrorCode errorCode, Exception ex) {
        this(errorCode.getCode(), ex);
    }

}
