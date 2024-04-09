package com.livares.intern.product.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.livares.intern.product.repository.ProductRepository;
import com.livares.intern.product.response.CustomResponseHandler;
import com.livares.intern.product.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductsController {
	@Autowired
	ProductService productService;

	@Autowired
	private ProductRepository productRepository;

	@GetMapping
	public ResponseEntity<Object> getAllProducts() {
		// return (ResponseEntity<List<Products>>) productsService.getallProducts();
		List<Products> products = productService.getallProducts();
		return CustomResponseHandler.generateResponse("All products", HttpStatus.OK, products);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getProductById(@RequestParam Long productid) {
		Optional<Products> product = productService.getProductById(productid);
//        if(product != null)
		return CustomResponseHandler.generateResponse("Requested Product", HttpStatus.FOUND, product);
//        else 
//			return ResponseHandler.generateResponse("Product Not Found", HttpStatus.NOT_FOUND, product);

	}

	@PostMapping("/create")
	public ResponseEntity<Object> createProduct(@RequestBody ProductsDTO product) {
		Products createProduct = productService.createProducts(product);
		return CustomResponseHandler.generateResponse("Product Created", HttpStatus.OK, createProduct);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Object> updateProducts(@RequestParam Long id, @RequestBody ProductsDTO product) {
		Products updatedProduct = productService.updateProduct(id, product);
		return CustomResponseHandler.generateResponse("Product Created", HttpStatus.OK, updatedProduct);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deleteProduct(@PathVariable Long id) {
		if (productService.deleteProduct(id))
			return CustomResponseHandler.generateResponse("Product deleted", HttpStatus.OK, id);
		else {
			throw new CustomException(ErrorCode.NOT_FOUND, "Product by id: " + id + "does not exist");
		}
	}

	@GetMapping("/viewAllproductsByPage")
	public ResponseEntity<Object> getAllProductsByPage(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "3") int size) {
		PageRequest pageRequest = PageRequest.of(page, size);
		Object pageObject = productRepository.findAll(pageRequest);
		return CustomResponseHandler.generateResponse("Paged Products", HttpStatus.OK, pageObject);
	}

}
