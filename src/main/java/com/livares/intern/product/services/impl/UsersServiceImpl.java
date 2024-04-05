package com.livares.intern.product.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.livares.intern.product.dto.UsersDTO;
import com.livares.intern.product.exception.CustomException;
import com.livares.intern.product.exception.ErrorCode;
import com.livares.intern.product.models.Users;
import com.livares.intern.product.repository.UsersRepository;
import com.livares.intern.product.services.UserService;

@Service
public class UsersServiceImpl implements UserService {
	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	// retrieving all Users in the table
	@Override
	public List<Users> getallUsers() {
		return usersRepository.findAll();
	}

	// retrieving specific user in the table
	@Override
	public Optional<Users> getUserById(Long id) {
		if(usersRepository.existsById(id))
			return usersRepository.findById(id);
		else {
			throw new CustomException(ErrorCode.NOT_FOUND,"User with id: "+id+"does not exists");
		}
	}

	// creating a new user
	@Override
	public Users createUsers(UsersDTO user) {
		Users newUser = new Users();
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setUsername(user.getUsername());
		newUser.setPassword(passwordEncoder.encode(user.getPassword()));
		return usersRepository.save(newUser);

	}

	// deleting a user
	@Override
	public void deleteUser(Long id) { 
		if(usersRepository.existsById(id))
			usersRepository.deleteById(id);
		else {
			throw new CustomException(ErrorCode.NOT_FOUND, "User with id: "+id+"does not exists");
		}
	}

	// updating a specific user
	@Override
	public Users updateUser(Long id, UsersDTO user) {
		Optional<Users> optionalUser = usersRepository.findById(id);
		if (optionalUser.isPresent()) {
			Users existingUser = optionalUser.get();
			existingUser.setFirstName(user.getFirstName());
			existingUser.setLastName(user.getLastName());
			existingUser.setUsername(user.getUsername());
			existingUser.setPassword(user.getPassword());
			return usersRepository.save(existingUser);
		} else {
			throw new RuntimeException("User not found with id: " + id);
		}
	}
	
	//register a new user
	@Override
	public Users registerNewUser(UsersDTO user) {
		Optional<Users> optionalUser = usersRepository.findByUsername(user.getUsername());
		if(optionalUser.isPresent())
				throw new CustomException(ErrorCode.CONFLICT,"User is an already existing user...");
		else{
			Users registernewUser = new Users(); 
			registernewUser.setFirstName(user.getFirstName());
			registernewUser.setLastName(user.getLastName());
			registernewUser.setUsername(user.getUsername());
			registernewUser.setPassword(passwordEncoder.encode(user.getPassword()));
			return usersRepository.save(registernewUser);
		
		}
	}
}
