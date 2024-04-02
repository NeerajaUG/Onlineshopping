package com.livares.intern.product.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserProductCart extends BaseEntity {
	
	@OneToOne
	@JoinColumn(name="user_id",referencedColumnName = "id")
	private Users users;
	
	@OneToOne
	@JoinColumn(name = "product_id",referencedColumnName = "id")
	private Products products;
}
