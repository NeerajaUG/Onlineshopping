package com.livares.intern.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.livares.intern.product.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
