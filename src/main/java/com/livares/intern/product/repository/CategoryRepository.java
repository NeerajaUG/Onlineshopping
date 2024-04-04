package com.livares.intern.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.livares.intern.product.dto.categoryDTO;
import com.livares.intern.product.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

	//@Query(value="select * from category", nativeQuery = true) // Native Query,used the table name directly
	
	@Query("select new com.livares.intern.product.dto.categoryDTO(c.category) from Category c ") //Hibernate Query and hence used model name and referred dto 
	List<categoryDTO> findAllCategory();
	
	
	@Query("select new com.livares.intern.product.dto.categoryDTO(c.category) from Category c where id=:id")
	List<categoryDTO> findCategoryById(Long id);
	
	
}
