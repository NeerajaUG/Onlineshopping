package com.livares.intern.product.services;

import java.util.List;
import java.util.Optional;

import com.livares.intern.product.dto.ProductsDTO;
import com.livares.intern.product.models.Products;

public interface ProductService {
	//to get all products
	public List <Products> getallProducts();
	
	//to get specific product 
	public Optional<Products> getProductById(Long id);
	
	//to create a field in product
	public Products createProducts(ProductsDTO product);
	
	//to delete a field in product	
	public void  deleteProduct(Long id);
	
	//to update a field in product
	public Products updateProduct(Long id,ProductsDTO product);

}
