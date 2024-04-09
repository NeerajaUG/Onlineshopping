package com.livares.intern.product.response;



import org.springframework.http.HttpStatusCode;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomResponse<T> {

    

	/**
     * HTTPSTATUS code
     */
    private HttpStatusCode status;
    /**
     * Generic data which will adopt to the different resources
     */
    private T data;

    /**
     * @param String message The success/error message of the API requested.
     */
    private String message;

    /**
     * @param boolean success The parameter which indicates the status of API
     *                response.
     */
    private boolean success;

    /**
     * @param String path The hateoas resource link
     */
    private String path;
}

