package com.livares.intern.product.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.livares.intern.product.dto.ProductsDTO;
import com.livares.intern.product.exception.CustomException;
import com.livares.intern.product.exception.ErrorCode;
import com.livares.intern.product.models.Category;
import com.livares.intern.product.models.Products;
import com.livares.intern.product.repository.CategoryRepository;
import com.livares.intern.product.repository.ProductRepository;
import com.livares.intern.product.services.ProductService;

@Service
public class ProductsServiceImpl implements ProductService {
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepo;

	@Override
	// retrieving all products in the table
	public List<Products> getallProducts() {
		return productRepository.findAll();
	}

	@Override
	// retrieving specific product in the table
	public Optional<Products> getProductById(Long id) {
		if (productRepository.existsById(id))
			return productRepository.findById(id);
		else {
			throw new CustomException(ErrorCode.NOT_FOUND, "Product by id: " + id + " does not exists");
		}
	}

	@Override
	// creating a new product
	public Products createProducts(ProductsDTO product) {
		Products newproduct = new Products();
		newproduct.setProductName(product.getName());
		newproduct.setProductDesc(product.getDescription());
		newproduct.setPrice(product.getPrice());
		newproduct.setQuantity(product.getQuantity());
		newproduct.setProductImage(product.getImage());
		Category category = categoryRepo.findById(product.getCategoryid()).get();
		newproduct.setCategory(category);
		return productRepository.save(newproduct);

	}

	@Override
	// deleting a product
	public boolean deleteProduct(Long id) {
		if (productRepository.existsById(id)) {
			productRepository.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

	@Override
	// updating a specific user
	public Products updateProduct(Long id, ProductsDTO product) {
		Optional<Products> optionalProduct = productRepository.findById(id);
		if (optionalProduct.isPresent()) {
			Products existingProduct = optionalProduct.get();
			existingProduct.setProductName(product.getName());
			existingProduct.setProductDesc(product.getDescription());
			existingProduct.setPrice(product.getPrice());
			// existingProduct.setCategory(product.getCategoryid());
			Category category = categoryRepo.findById(product.getCategoryid()).get();
			existingProduct.setCategory(category);
			existingProduct.setProductImage(product.getImage());
			existingProduct.setQuantity(product.getQuantity());
			return productRepository.save(existingProduct);
		} else {
			throw new CustomException(ErrorCode.NOT_FOUND, "The product with the id not found: " + id);
		}
	}

	public Page<Products> getAllProducts(int page, int size) {
		PageRequest pageRequest = PageRequest.of(page, size);
		return productRepository.findAll(pageRequest);
	}

}
