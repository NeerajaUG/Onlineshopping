package com.livares.intern.product.controllers;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.livares.intern.product.dto.ProductsDTO;
import com.livares.intern.product.exception.CustomException;
import com.livares.intern.product.exception.ErrorCode;
import com.livares.intern.product.models.Products;
import com.livares.intern.product.models.Users;
import com.livares.intern.product.repository.ProductRepository;
import com.livares.intern.product.response.ResponseHandler;
import com.livares.intern.product.services.impl.ProductsServiceImpl;

@RestController
@RequestMapping("/products")
public class ProductsController {
	@Autowired
	ProductsServiceImpl productsService;
	
	@Autowired
	private ProductRepository productRepository;
	
	@GetMapping
	public ResponseEntity<Object> getAllProducts(){
		//return (ResponseEntity<List<Products>>) productsService.getallProducts();
		List<Products> products =productsService.getallProducts();
		return ResponseHandler.generateResponse("All products", HttpStatus.OK, products);
	}
	
	@GetMapping("/{id}")
    public ResponseEntity<Object> getProductById(@RequestParam Long productid) {
        Optional<Products> product = productsService.getProductById(productid);
//        if(product != null)
        	return ResponseHandler.generateResponse("Requested Product", HttpStatus.FOUND, product);
//        else 
//			return ResponseHandler.generateResponse("Product Not Found", HttpStatus.NOT_FOUND, product);
		
    }
	
	@PostMapping("/create")
    public ResponseEntity<Object> createProduct(@RequestBody ProductsDTO product) {
	 Products  createProduct = productsService.createProducts(product);
        return ResponseHandler.generateResponse("Product Created", HttpStatus.OK, createProduct);
    }
	
	@PutMapping("/update/{id}")
    public ResponseEntity<Object> updateProducts(@RequestParam Long id, @RequestBody ProductsDTO product) {
	 Products updatedProduct = productsService.updateProduct(id, product);
        return ResponseHandler.generateResponse("Product Created", HttpStatus.OK, updatedProduct);
    }
	
	@DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable Long id) {
		if(productsService.deleteProduct(id))
			return ResponseHandler.generateResponse("Product deleted", HttpStatus.OK, id);
		else {
			throw new CustomException(ErrorCode.NOT_FOUND,"Product by id: "+id+"does not exist");
		}
    }
	
	@GetMapping("/viewAllproductsByPage")
    public ResponseEntity<Object> getAllProductsByPage(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Object pageObject= productRepository.findAll(pageRequest);
        return ResponseHandler.generateResponse("Paged Products", HttpStatus.OK,pageObject);
    }

}
