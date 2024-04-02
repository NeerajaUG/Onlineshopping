package com.livares.intern.product.services;

import java.util.List;
import java.util.Optional;

import com.livares.intern.product.models.Category;

public interface CategoryServices {
	
	//retrieving all category
	public List <Category> getAllCategories();
	
	//retrieving specific category
	public Optional <Category> getCategoryById(Long id);
	
	//creating a new category field
	public Category createCategory(Category category);
	
	//deleting  a category
	public void deleteCategotyById(Long id);
	
	//updating category filed
	public Category updateCategory(Long id,Category category);	

}
