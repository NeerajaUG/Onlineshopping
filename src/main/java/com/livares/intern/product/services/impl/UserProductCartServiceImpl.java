package com.livares.intern.product.services.impl;

import java.util.List;
import java.util.Optional;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.livares.intern.product.models.UserProductCart;
import com.livares.intern.product.repository.UsersProductCartRepository;

@Service
public class UserProductCartServiceImpl {
	
	@Autowired
	private UsersProductCartRepository userProductCartRepository;
	
	//retrieving all cart data
	public List<UserProductCart> getAllUserProductCart(){
		return userProductCartRepository.findAll();
	}
	
	//retrieving all cart data by id
	public Optional<UserProductCart> getCartDataById(Long id){
		return userProductCartRepository.findById(id);
	}
	
	//create cart data
	public UserProductCart createUserProductCart(UserProductCart userProductCart) {
		return userProductCartRepository.save(userProductCart);
	}
	//delete cart data
	public void deleteUserProductCart(Long id) {
		userProductCartRepository.deleteById(id);
	}
	//updating cart data
	public UserProductCart updateUserProductCart(Long id, UserProductCart updateCart){
		Optional <UserProductCart> optionalCart=userProductCartRepository.findById(id);
		if(optionalCart.isPresent()) {
			
			UserProductCart existingUserProductCart = optionalCart.get();
			existingUserProductCart.setId(updateCart.getId());
			existingUserProductCart.setId(updateCart.getId());
			existingUserProductCart.setId(updateCart.getId());
			
		}else {
			throw new RuntimeException("Cart item not found with id: " + id);
		}
		return updateCart;
			
	}

}
