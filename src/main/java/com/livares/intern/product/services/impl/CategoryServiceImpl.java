package com.livares.intern.product.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.livares.intern.product.dto.categoryDTO;
import com.livares.intern.product.models.Category;
import com.livares.intern.product.repository.CategoryRepository;

@Service
public class CategoryServiceImpl {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	//retrieving all category data
	public List <Category> getAllCategories(){
		return categoryRepository.findAll();
	}
	
	//retrieving specific category data
	public Optional <Category> getCategoryById(Long id){
			return categoryRepository.findById(id);
		}
	
	//creating a new category field
	public Category createCategory(categoryDTO category){
		Category catgry = new Category();
		catgry.setCategory(category.getCategory());
		return categoryRepository.save(catgry);
	}
	
	//deleting  a category
	public void deleteCategotyById(Long id) {
		 categoryRepository.deleteById(id);
	}
	
	//updating category filed
	public Category updateCategory(Long id,categoryDTO category) {
		Optional <Category> optionalCategory =categoryRepository.findById(id);
		if(optionalCategory.isPresent()) {
			Category existingCategory = optionalCategory.get();
//			existingCategory.setId(category.getId());
			existingCategory.setCategory(category.getCategory());
			return categoryRepository.save(existingCategory);
		}
		else {
			throw new RuntimeException("Category not found with id: " + id);
		}
		
	}

}
