package com.livares.intern.product.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	CustomUserDetailService customUserDetails;

	@Autowired
	RestAuthenticationEntryPoint authenticationEntryPoint;

	@Autowired
	JwtAuthenticationFilter jwtAuthFilter;

	@Autowired
	CustomLogoutHandler logoutHandler;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(registry -> {
			registry.requestMatchers("/swagger-ui/**", "/api/login/**").permitAll();
//            registry.requestMatchers("/admin/**").hasRole("ADMIN");
//            registry.requestMatchers("/user/**").hasRole("USER");
			registry.anyRequest().authenticated();
		}).userDetailsService(customUserDetails)
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
				.exceptionHandling(e -> e
						.accessDeniedHandler((request, response, accessDeniedException) -> response.setStatus(403))
						.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
				.logout(l -> l.logoutUrl("/logout").addLogoutHandler(logoutHandler).logoutSuccessHandler(
						(request, response, authentication) -> SecurityContextHolder.clearContext()));
//        .httpBasic(t -> t.authenticationEntryPoint(authenticationEntryPoint));

		return http.build();
	}

	/**
	 * To support user login
	 */
	@Bean
	public UserDetailsService userDetailsService() {

//        UserDetails admin = User.builder().username("admin")
//                .password("$2a$12$IlQ67Iu3xsiZ4xPvbrgW0.7ymD.xcmGogWmnrHqTagAnfk9rh4S/a")
//                .roles("ADMIN").build();
//        UserDetails user2 = User.builder().username("user2")
//                .password("$2a$12$IlQ67Iu3xsiZ4xPvbrgW0.7ymD.xcmGogWmnrHqTagAnfk9rh4S/a")
//                .roles("USER").build();
//
//        return new InMemoryUserDetailsManager(admin, user2);

		return customUserDetails;

	}

	/**
	 * To set the authentication provider for the the user/password from DB
	 * 
	 * @return
	 */
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(customUserDetails);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}

	@Bean
	public AuthenticationManager authenicationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}