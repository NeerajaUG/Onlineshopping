package com.livares.intern.product.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Users extends BaseEntity {

	@Column(name = "user_fname")
	private String firstName;

	@Column(name = "user_lastname")
	private String lastName;

	@Column(name = "user_name", unique = true)
	private String username;

	@Column(name = "user_password")
	private String password;

}
