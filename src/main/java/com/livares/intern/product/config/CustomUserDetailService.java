package com.livares.intern.product.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.livares.intern.product.models.Users;
import com.livares.intern.product.repository.UsersRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	UsersRepository usersRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<Users> user = usersRepository.findByUsername(username);
		if (user.isPresent()) {

			return User.builder().username(user.get().getUsername()).password(user.get().getPassword()).roles().build();

		} else {
			throw new UsernameNotFoundException("Not such user found!!");
		}

	}

}
