package com.livares.intern.product.response;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomResponse {
	String statusmessage;
	int httpStatus;
	Object responseObject;

}
