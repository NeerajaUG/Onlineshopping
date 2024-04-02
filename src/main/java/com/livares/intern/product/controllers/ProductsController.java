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

import com.livares.intern.product.dto.ProductsDTO;
import com.livares.intern.product.models.Products;
import com.livares.intern.product.models.Users;
import com.livares.intern.product.services.impl.ProductsServiceImpl;

@RestController
@RequestMapping("/products")
public class ProductsController {
	@Autowired
	ProductsServiceImpl productsService;
	
	@GetMapping
	public ResponseEntity<List<Products>> getAllProducts(){
		return (ResponseEntity<List<Products>>) productsService.getallProducts();
	}
	
	@GetMapping("/{id}")
    public ResponseEntity<Optional> getProductById(@PathVariable Long productid) {
        Optional<Products> product = productsService.getProductById(productid);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
	
	@PostMapping("/create")
    public ResponseEntity<Products> createProduct(@RequestBody ProductsDTO product) {
	 Products  createProduct = productsService.createProducts(product);
        return new ResponseEntity<>(createProduct, HttpStatus.CREATED);
    }
	
	@PutMapping("/update/{id}")
    public ResponseEntity<Products> updateProducts(@PathVariable Long id, @RequestBody ProductsDTO product) {
	 Products updatedProduct = productsService.updateProduct(id, product);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }
	
	@DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
		productsService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
