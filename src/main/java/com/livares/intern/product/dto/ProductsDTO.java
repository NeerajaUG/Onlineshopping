package com.livares.intern.product.dto;

import lombok.Data;

@Data
public class ProductsDTO {

	private String name;
	private String description;
	private Long price;
	private Long categoryid;
	private String image;
	private Long quantity; 
}
