package com.livares.intern.product.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductsDTO {

	public ProductsDTO(String name, String description, Long price, Long categoryid, String image, Long quantity) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
		this.categoryid = categoryid;
		this.image = image;
		this.quantity = quantity;
	}

	private String name;
	private String description;
	private Long price;
	private Long categoryid;
	private String image;
	private Long quantity;
}
