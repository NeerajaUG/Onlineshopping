package com.livares.intern.product.services.impl;

import java.util.List;
import java.util.Optional;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.livares.intern.product.dto.ProductsDTO;
import com.livares.intern.product.exception.CustomException;
import com.livares.intern.product.exception.ErrorCode;
import com.livares.intern.product.models.Products;
import com.livares.intern.product.models.UserProductCart;
import com.livares.intern.product.models.Users;
import com.livares.intern.product.repository.UsersProductCartRepository;
import com.livares.intern.product.repository.UsersRepository;
import com.livares.intern.product.services.UsersProductCartServices;

@Service
public class UserProductCartServiceImpl implements UsersProductCartServices{
	
	@Autowired
	private UsersProductCartRepository userProductCartRepository;
	
//	@Autowired
//	private UsersRepository usersRepository;
//	
//	@Autowired
//	private ProductRepository  
	
	
	
	//View cart 
	@Override
	public List<ProductsDTO> viewCartDataById(Long id){ 
		if(userProductCartRepository.existsById(id))
			return userProductCartRepository.ViewCartDataById1(id);
		else {
			throw new CustomException(ErrorCode.NOT_FOUND,"No user exists with id: "+id);
		}
	}
	
	//add to cart
	@Override  
	public UserProductCart addUserProductCart(Long userid,Long productid) {
		
//		Optional<Users> optionalUser = usersRepository.findById(userid);
//        Optional<Products> optionalProduct = productRepository.findById(productid);
        
		Users users = new Users();
		users.setId(userid);
		
		Products products = new Products();
		products.setId(productid);
		
		UserProductCart userProductCart = new UserProductCart();
		userProductCart.setUsers(users);
		userProductCart.setProducts(products);
		return userProductCartRepository.save(userProductCart);
	}
	
	//delete cart data
	@Override
	public void removeFromCart(Long id) {
		if(userProductCartRepository.existsById(id))
			userProductCartRepository.deleteById(id);
		else {
			throw new CustomException(ErrorCode.NOT_FOUND,"No product exists with id: "+id);
		}
	
	}
	
}
