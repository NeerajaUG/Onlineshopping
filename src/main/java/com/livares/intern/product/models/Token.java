package com.livares.intern.product.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;


	
	@Entity
	@Getter
	@Setter
	public class Token {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "id")
	    private Long id;

	    @Column(name = "token")
	    private String token;

	    @Column(name = "is_logged_out")
	    private boolean loggedOut;

	    @ManyToOne
	    @JoinColumn(name = "user_id")
	    private Users user;

}
