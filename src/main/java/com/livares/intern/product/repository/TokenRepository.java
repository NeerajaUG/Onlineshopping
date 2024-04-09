package com.livares.intern.product.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.livares.intern.product.models.Token;



public interface TokenRepository extends JpaRepository<Token, Long> {

    Optional<Token> findByToken(String token);
}