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
import org.springframework.web.bind.annotation.RestController;

import com.livares.intern.product.dto.UsersDTO;
import com.livares.intern.product.models.Users;
import com.livares.intern.product.services.UserService;

@RestController
@RequestMapping("/users")
public class UsersController {

	@Autowired
	UserService usersService;

	@GetMapping
	public ResponseEntity<List<Users>> getAllUsers() {
		List<Users> users = usersService.getallUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Optional> getUserById(@PathVariable Long userid) {
		Optional<Users> user = usersService.getUserById(userid);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@PostMapping("/create")
	public ResponseEntity<Users> createUser(@RequestBody UsersDTO user) {
		Users createUser = usersService.createUsers(user);
		return new ResponseEntity<>(createUser, HttpStatus.CREATED);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Users> updateUser(@PathVariable Long id, @RequestBody UsersDTO user) {
		Users updateduser = usersService.updateUser(id, user);
		return new ResponseEntity<>(updateduser, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteuser(@PathVariable Long userid) {
		usersService.deleteUser(userid);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
