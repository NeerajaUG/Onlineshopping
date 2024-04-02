package com.livares.intern.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.livares.intern.product.models.Products;

public interface ProductRepository extends JpaRepository<Products, Long> {

}
