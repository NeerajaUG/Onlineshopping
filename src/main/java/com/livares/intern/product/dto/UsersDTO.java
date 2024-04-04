package com.livares.intern.product.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsersDTO {
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	
	public UsersDTO(String firstName, String lastName, String username, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
	}
	
	
}
