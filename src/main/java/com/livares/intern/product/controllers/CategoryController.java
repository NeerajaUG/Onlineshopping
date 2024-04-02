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
import com.livares.intern.product.services.impl.CategoryServiceImpl;

@RestController
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	CategoryServiceImpl categoryService;
	
	@GetMapping
	public ResponseEntity<List<Category>> getAllCategory(){
		return (ResponseEntity<List<Category>>) categoryService.getAllCategories();
	}
	
	@GetMapping("/{id}")
    public ResponseEntity<Optional> getCategoryById(@PathVariable Long id) {
        Optional<Category> category = categoryService.getCategoryById(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }
	
	@PostMapping("/create")
    public ResponseEntity<Category> createCategory(@RequestBody categoryDTO category) {
	 Category  createCategory = categoryService.createCategory(category);
        return new ResponseEntity<>(createCategory, HttpStatus.CREATED);
    }
	
	@PutMapping("/update/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody categoryDTO category) {
	 Category updatedcategory = categoryService.updateCategory(id, category);
        return new ResponseEntity<>(updatedcategory, HttpStatus.OK);
    }
	
	@DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
		categoryService.deleteCategotyById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
