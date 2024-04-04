package com.livares.intern.product.services;

import java.util.List;
import java.util.Optional;

import com.livares.intern.product.dto.ProductsDTO;
import com.livares.intern.product.models.Products;
import com.livares.intern.product.models.UserProductCart;

public interface UsersProductCartServices {

	// View Cart
	public List<ProductsDTO> viewCartDataById(Long id);
	
	// add to cart 
	public UserProductCart addUserProductCart(Long userid, Long productid);
	
	//delete cart data
	public void deleteUserProductCart(Long id);
		

}
