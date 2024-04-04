package com.livares.intern.product.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.livares.intern.product.dto.categoryDTO;
import com.livares.intern.product.models.Category;
import com.livares.intern.product.models.Products;
import com.livares.intern.product.response.ResponseHandler;
import com.livares.intern.product.services.CategoryServices;
import com.livares.intern.product.services.impl.CategoryServiceImpl;

@RestController
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	CategoryServiceImpl categoryServiceImpl;
	
	@GetMapping
	public ResponseEntity<Object> getAllCategory(){
		List<categoryDTO> category = categoryServiceImpl.getAllCategories();
		return ResponseHandler.generateResponse("All Category", HttpStatus.OK,category);
		//return new ResponseEntity<>(categoryServiceImpl.getAllCategories(),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
    public ResponseEntity<Object> getCategoryById(@PathVariable Long id) {
		List<categoryDTO> category = categoryServiceImpl.getCategoryById(id);
		if(category!=null)
			return ResponseHandler.generateResponse("Category by Id", HttpStatus.OK,category);
		else 
			return ResponseHandler.generateResponse("Specified category does not exists", HttpStatus.NOT_FOUND,category);
    }
	
	@PostMapping("/create")
    public ResponseEntity<Object> createCategory(@RequestBody categoryDTO category) {
	 Category  createCategory = categoryServiceImpl.createCategory(category);
        return ResponseHandler.generateResponse("Category created", HttpStatus.CREATED, createCategory);
    }
	
	@PutMapping("/update/{id}")
    public ResponseEntity<Object> updateCategory(@PathVariable Long id, @RequestBody categoryDTO category) {
	 Category updatedcategory = categoryServiceImpl.updateCategory(id, category);
	 return ResponseHandler.generateResponse("Category updated", HttpStatus.OK, updatedcategory);
    }
	
	@DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteCategory(@PathVariable Long id) {
		categoryServiceImpl.deleteCategoryById(id);
        return ResponseHandler.generateResponse("Category deleted", HttpStatus.OK, id);
    }

}
