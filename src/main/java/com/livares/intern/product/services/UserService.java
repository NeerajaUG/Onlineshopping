package com.livares.intern.product.services;

import java.util.List;
import java.util.Optional;

import com.livares.intern.product.dto.UsersDTO;
import com.livares.intern.product.models.Users;

public interface UserService {

	//retrieving all users in table
	public List<Users> getallUsers();

	// retrieving specific user in the table
	public Optional<Users> getUserById(Long id);

	// creating a new user
	public Users createUsers(UsersDTO user);

	// deleting a user
	public void deleteUser(Long id);

	// updating a specific user
	public Users updateUser(Long id, UsersDTO user);
}
