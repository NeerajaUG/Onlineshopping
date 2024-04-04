package com.livares.intern.product.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class categoryDTO {
	public categoryDTO(String category) {
		super();
		this.category = category;
	}

	private String category;
	
}
