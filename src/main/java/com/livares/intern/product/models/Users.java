package com.livares.intern.product.models;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Users extends BaseEntity implements UserDetails{

	@Column(name = "user_fname")
	private String firstName;

	@Column(name = "user_lastname")
	private String lastName;
	
	@Column(name = "role")
    private String role;

	@Column(name = "user_name", unique = true)
	private String username;

	@JsonIgnore
	@Column(name = "user_password")
	private String password;
	
	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
