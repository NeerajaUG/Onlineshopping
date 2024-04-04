package com.livares.intern.product.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.livares.intern.product.dto.categoryDTO;
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
			return categoryRepository.findCategoryById(id);
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
		 categoryRepository.deleteById(id);
	}

	
	@Override
	public Category updateCategory(Long id, categoryDTO category) {
		Optional <Category> optionalCategory = categoryRepository.findById(id);
		if(optionalCategory.isPresent()) {
			Category existingCategory = optionalCategory.get();
			existingCategory.setCategory(category.getCategory());
			return categoryRepository.save(existingCategory);
		}
		else {
			throw new RuntimeException("Category not found with id: " + id);
		}
	}
	
	//updating category filed
//	public Category updateCategory(Long id,categoryDTO category) {
//		Optional <Category> optionalCategory =categoryRepository.findById(id);
//		if(optionalCategory.isPresent()) {
//			Category existingCategory = optionalCategory.get();
////			existingCategory.setId(category.getId());
//			existingCategory.setCategory(category.getCategory());
//			return categoryRepository.save(existingCategory);
//		}
//		else {
//			throw new RuntimeException("Category not found with id: " + id);
//		}
//		
//	}

}
