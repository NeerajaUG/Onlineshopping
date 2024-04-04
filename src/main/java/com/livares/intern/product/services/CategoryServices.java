package com.livares.intern.product.services;

import java.util.List;
import java.util.Optional;

import com.livares.intern.product.dto.categoryDTO;
import com.livares.intern.product.models.Category;

public interface CategoryServices {
	
	//retrieving all category
	public List<categoryDTO> getAllCategories();
	
	//retrieving specific category
	public List<categoryDTO> getCategoryById(Long id);
	
	//creating a new category field
	public Category createCategory(categoryDTO category);
	
	//deleting  a category
	public void deleteCategoryById(Long id);
	
	//updating category filed
	public Category updateCategory(Long id,categoryDTO category);	

}
