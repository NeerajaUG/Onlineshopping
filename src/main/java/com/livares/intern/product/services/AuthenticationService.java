package com.livares.intern.product.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.livares.intern.product.dto.UsersDTO;
import com.livares.intern.product.dto.UsersLoginDTO;
import com.livares.intern.product.models.Users;
import com.livares.intern.product.repository.UsersRepository;

@Service
public class AuthenticationService {
	@Autowired
	UsersRepository userRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	AuthenticationManager authenticationManager;

	public Users authenticate(UsersLoginDTO input) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(input.getUsername(), input.getPassword()));

		return userRepository.findByUsername(input.getUsername()).orElseThrow();
	}

}
