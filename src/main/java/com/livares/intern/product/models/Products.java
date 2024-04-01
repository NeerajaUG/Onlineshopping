package com.livares.intern.product.models;

import java.util.Locale.Category;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter

public class Products extends BaseEntity{
	
	
	
	private String productName;
	private String productDesc;
	
	private Long price;
	
	@ManyToOne
	@JoinColumn(name="category_id", referencedColumnName = "id")//specifying foreign key
	private Category category;//represents the relationship  btw and used to access category object from products
	
	private String productImage;
	
	private Long quantity;
	
}
