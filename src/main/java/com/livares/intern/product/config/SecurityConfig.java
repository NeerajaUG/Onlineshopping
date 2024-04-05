package com.livares.intern.product.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

//	@Bean
//	SecurityFilterChain confifure(HttpSecurity http) throws Exception {
//
//		http.csrf(csrf -> csrf.disable());
//		return http.build();
//	}

	@Autowired
	CustomUserDetailService customUserDetailService;
	
	@Autowired
	RestAuthenticationEntryPoint restAuthenticationEntryPoint;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity.authorizeHttpRequests(registry -> {
			registry.requestMatchers("/home").permitAll();
			registry.requestMatchers("/admin/**").hasRole("ADMIN");
			registry.requestMatchers("/user/**").hasRole("USER");
			registry.anyRequest().authenticated();
		}) .httpBasic(auth->auth.authenticationEntryPoint(restAuthenticationEntryPoint))
				.build();
	}

//	BasicAuthenticationEntryPoint basicAuthEntrypoint() {  //-------------Written this for basic authentication---------
//		BasicAuthenticationEntryPoint entryPoint = new BasicAuthenticationEntryPoint();
//		entryPoint.setRealmName("API");
//		return entryPoint;
//	}

	@Bean //--------------- A Bean Converts a function into an object--------------------
	public UserDetailsService userDetailsService() {
//		UserDetails normalUser = User.builder()//-------------------hard coding authentication details--------------------
//				.username("neeraja")
//				.password("$2a$12$wDbehnUvmuo3E5Y98S6Ig.6cpTUhmQrgTkSV/uiW3BWiVU38o7k6S")
//				.roles("USER")
//				.build();
//		
//		UserDetails adminUser = User.builder()
//				.username("admin")
//				.password("$2a$12$BX08U2Rbas4B8Rfn9IgOOOudlzh7PgpFNCqafFGH833RZglWEzqvy")
//				.roles("ADMIN","USER")
//				.build();
//		
//		return new InMemoryUserDetailsManager(normalUser,adminUser);
		return customUserDetailService;   //-----------------customized instead of hard coding, Autowired and used customUserDetailService
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(customUserDetailService);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
