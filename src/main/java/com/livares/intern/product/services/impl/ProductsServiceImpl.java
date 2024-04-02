package com.livares.intern.product.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.livares.intern.product.dto.ProductsDTO;
import com.livares.intern.product.models.Products;
import com.livares.intern.product.models.Users;
import com.livares.intern.product.repository.ProductRepository;
import com.livares.intern.product.repository.UsersRepository;

@Service
public class ProductsServiceImpl {
	@Autowired
	private ProductRepository productRepository;
	
	//retrieving all products in the table
	public List <Products> getallProducts(){
		return productRepository.findAll();	
	}
	
	//retrieving specific product in the table
	public Optional<Products> getProductById(Long id){
		return productRepository.findById(id);	
	}
	
	//creating a new product
	public Products createProducts(ProductsDTO product){
		Products newproduct = new Products();
		newproduct.setProductName(product.getName());
		newproduct.setProductDesc(product.getDescription());
		newproduct.setPrice(product.getPrice());
		newproduct.setQuantity(product.getQuantity());
		newproduct.setProductImage(product.getImage());
		return productRepository.save(newproduct);
		
	}
	
	//deleting a product
	public void  deleteProduct(Long id) {
		productRepository.deleteById(id);
	}
	
	//updating a specific user
	public Products updateProduct(Long id,ProductsDTO product) {
		Optional <Products> optionalProduct= productRepository.findById(id);
			if(optionalProduct.isPresent()) {
				Products existingProduct = optionalProduct.get(); 
				existingProduct.setProductName(product.getName());
				existingProduct.setProductDesc(product.getDescription());
				existingProduct.setPrice(product.getPrice());
				//existingProduct.setCategory(product.getCategoryid());
				existingProduct.setProductImage(product.getImage());
				existingProduct.setQuantity(product.getQuantity());
				return productRepository.save(existingProduct);
			}
			else {
	            throw new RuntimeException("Product not found with id: " + id);
	        }
	}

}
