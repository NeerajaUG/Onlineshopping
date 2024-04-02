package com.livares.intern.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.livares.intern.product.models.Users;

public interface UsersRepository extends JpaRepository<Users, Long>{

}
