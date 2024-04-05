package com.livares.intern.product.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.util.ThrowableCauseExtractor;
import org.springframework.stereotype.Service;

import com.livares.intern.product.dto.categoryDTO;
import com.livares.intern.product.exception.CustomException;
import com.livares.intern.product.exception.CustomExceptionHandler;
import com.livares.intern.product.exception.ErrorCode;
import com.livares.intern.product.models.Category;
import com.livares.intern.product.repository.CategoryRepository;
import com.livares.intern.product.services.CategoryServices;

@Service
public class CategoryServiceImpl implements CategoryServices {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	//retrieving all category data
	@Override
	public List<categoryDTO> getAllCategories(){
		return categoryRepository.findAllCategory();
	}
	
	//retrieving specific category data
	@Override
	public List<categoryDTO> getCategoryById(Long id){
		if(categoryRepository.existsById(id))
			return categoryRepository.findCategoryById(id);
		else {
			throw new CustomException(ErrorCode.NOT_FOUND,"Could not find the category id");
		}
		}
	
	//creating a new category field
	@Override
	public Category createCategory(categoryDTO category){
		Category catgry = new Category();
		catgry.setCategory(category.getCategory());
		return categoryRepository.save(catgry);
	}
	
	//deleting  a category
	@Override
	public void deleteCategoryById(Long id) {
		if(categoryRepository.existsById(id))
		 categoryRepository.deleteById(id);
		else {
			throw new CustomException(ErrorCode.NOT_FOUND,"Could not find the category id : "+id);
		}
	}

	//updating a category
	@Override
	public Category updateCategory(Long id, categoryDTO category) {
		Optional <Category> optionalCategory = categoryRepository.findById(id);
		if(optionalCategory.isPresent()) {
			Category existingCategory = optionalCategory.get();
			existingCategory.setCategory(category.getCategory());
			return categoryRepository.save(existingCategory);
		}
		else {
			throw new CustomException(ErrorCode.NOT_FOUND,"Could not find the category id: "+id);//using custom exception
			
			//throw new RuntimeException("Category not found with id: " + id);
		}
	}
	
}
