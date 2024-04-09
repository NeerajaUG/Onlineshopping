package com.livares.intern.product.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.livares.intern.product.dto.UsersDTO;
import com.livares.intern.product.models.Users;
import com.livares.intern.product.response.CustomResponseHandler;
import com.livares.intern.product.services.UserService;

@RestController
@RequestMapping("/users")
public class UsersController {

	@Autowired
	UserService usersService;

	@GetMapping
	public ResponseEntity<Object> getAllUsers() {
		List<Users> users = usersService.getallUsers();
		return CustomResponseHandler.generateResponse("All Users",HttpStatus.OK,users);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getUserById(@PathVariable Long userid) {
		Optional<Users> user = usersService.getUserById(userid);
		if(user!=null)
			return CustomResponseHandler.generateResponse("No such user", HttpStatus.NOT_FOUND, user);
		else
			return CustomResponseHandler.generateResponse("Requested Product", HttpStatus.FOUND, user);
	}

	@PostMapping("/create")
	public ResponseEntity<Object> createUser(@RequestBody UsersDTO user) {
		Users createUser = usersService.createUsers(user);
		return CustomResponseHandler.generateResponse("User Created", HttpStatus.CREATED, createUser);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Object> updateUser(@PathVariable Long id, @RequestBody UsersDTO user) {
		Users updateduser = usersService.updateUser(id, user);
		return CustomResponseHandler.generateResponse("User updated", HttpStatus.OK, updateduser);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deleteuser(@RequestParam Long userid) {
		usersService.deleteUser(userid);
		return CustomResponseHandler.generateResponse("deleted user", HttpStatus.OK, userid);
	}
	
	//to handle registering...
	@PostMapping("/register")
	public ResponseEntity<Object> registerUser(@RequestBody UsersDTO user){
		Users registeredUsers = usersService.registerNewUser(user);
		return CustomResponseHandler.generateResponse("User registered", HttpStatus.OK, registeredUsers);
		
	}

}
