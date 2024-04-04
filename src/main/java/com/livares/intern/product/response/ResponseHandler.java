package com.livares.intern.product.response;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {
	
	public static ResponseEntity<Object>  generateResponse(String msg, HttpStatus status, Object resObj) {
		
		CustomResponse customResponse = new CustomResponse(msg,status.value(),resObj);

//		Map<String, Object> map = new HashMap<String, Object>();
//        map.put("message", msg);
//        map.put("status", status.value());
//        map.put("data", resObj);
//        return new ResponseEntity<Object>(map,status);
		return new ResponseEntity<Object>(customResponse,status);
		
	}

}
