package com.livares.intern.product.models;


import org.springframework.web.jsf.FacesContextUtils;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class Users extends BaseEntity{
	
	
	
	@Column(nullable = false)
	private String firstName; 
	private String lastName;
	
	@Column(nullable = false, unique = true)
	private String username;
	
	@Column(nullable = false)
	private String password;
	
    
}
