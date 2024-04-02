package com.livares.intern.product.services;

import java.util.List;
import java.util.Optional;

import com.livares.intern.product.models.UserProductCart;

public interface UsersProductCartServices {
	//retrieving all cart data
	public List<UserProductCart> getAllUserProductCart();
	
	//retrieving all cart data by id
	public Optional<UserProductCart> getCartDataById(Long id);
	
	//create cart data
	public UserProductCart createUserProductCart(UserProductCart userProductCart);
	
	//delete cart data
	public void deleteUserProductCart(Long id);
	
	//updating cart data
	public UserProductCart updateUserProductCart(Long id, UserProductCart updateCart);

}
