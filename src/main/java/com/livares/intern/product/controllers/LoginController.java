package com.livares.intern.product.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.livares.intern.product.dto.LoginResponse;
import com.livares.intern.product.dto.UsersLoginDTO;
import com.livares.intern.product.models.Users;
import com.livares.intern.product.response.CustomResponseHandler;
import com.livares.intern.product.services.AuthenticationService;
import com.livares.intern.product.services.JWTService.JwtService;

@RestController
@RequestMapping("/api")
public class LoginController {

	@Autowired
	JwtService jwtService;

	@Autowired
	AuthenticationService authenticationService;

	@PostMapping("/login")
	public ResponseEntity<Object> findById(@RequestBody UsersLoginDTO userDto) {
		Users authenticatedUser = authenticationService.authenticate(userDto);

		String jwtToken = jwtService.generateToken(authenticatedUser);

		LoginResponse loginResponse = new LoginResponse(jwtToken, jwtService.getExpirationTime());

		return CustomResponseHandler.generateResponse("Success", HttpStatus.OK, loginResponse);

	}
}
