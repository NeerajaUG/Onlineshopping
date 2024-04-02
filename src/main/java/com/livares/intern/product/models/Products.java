package com.livares.intern.product.models;


import jakarta.annotation.Generated;
import jakarta.persistence.Column;
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
	
	
	@Column(name="product_name")
	private String productName;
	
	@Column(name="product_description")
	private String productDesc;
	
	@Column(name="product_price")
	private Long price;
	
	@ManyToOne
	@JoinColumn(name="category_id", referencedColumnName = "id")//specifying foreign key
	private Category category;//represents the relationship  btw and used to access category object from products
	
	@Column(name="product_image")
	private String productImage;
	
	@Column(name="product_quantity")
	private Long quantity;
	
}
