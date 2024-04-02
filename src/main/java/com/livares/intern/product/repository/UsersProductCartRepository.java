package com.livares.intern.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.livares.intern.product.models.UserProductCart;

public interface UsersProductCartRepository extends  JpaRepository<UserProductCart, Long>{

}
