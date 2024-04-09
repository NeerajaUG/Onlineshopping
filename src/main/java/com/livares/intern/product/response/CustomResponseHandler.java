package com.livares.intern.product.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CustomResponseHandler {

	public static ResponseEntity<Object> generateResponse(String msg, HttpStatus status, Object resObj) {

		CustomResponse<Object> customResponse = new CustomResponse<Object>();
		customResponse.setMessage(msg);
		customResponse.setStatus(status);
		customResponse.setData(resObj);

//		Map<String, Object> map = new HashMap<String, Object>();
//        map.put("message", msg);
//        map.put("status", status.value());
//        map.put("data", resObj);
//        return new ResponseEntity<Object>(map,status);
		return new ResponseEntity<Object>(customResponse, status);
	}

//	public static CustomResponse createResponse(CustomResponse response, Object data,
//            String successMessage, String errorMessage) {
//        if (data != null) {
//            response.setSuccess(true);
//            response.setData(data);
//            response.setMessage(successMessage);
//        } else {
//            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR, errorMessage);
//        }
//        return response;
//    }

}
