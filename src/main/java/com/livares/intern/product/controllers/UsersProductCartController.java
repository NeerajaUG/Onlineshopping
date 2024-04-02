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

import com.livares.intern.product.models.Products;
import com.livares.intern.product.models.UserProductCart;
import com.livares.intern.product.services.impl.UserProductCartServiceImpl;

@RestController
@RequestMapping("/cart")
public class UsersProductCartController {
	@Autowired
	UserProductCartServiceImpl userProductCartService;
	
	@GetMapping
	public ResponseEntity<List<UserProductCart>> getAllCart(){
		return (ResponseEntity<List<UserProductCart>>) userProductCartService. getAllUserProductCart();
	}
	
	@GetMapping("/{id}")
    public ResponseEntity<Optional> getUserProductCartById(@PathVariable Long id) {
        Optional<UserProductCart> cart = userProductCartService.getCartDataById(id);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }
	
	@PostMapping("/create")
    public ResponseEntity<UserProductCart> createCartData(@RequestBody UserProductCart cart) {
	 UserProductCart  createCart = userProductCartService.createUserProductCart(cart);
        return new ResponseEntity<>(createCart, HttpStatus.CREATED);
    }
	
	@PutMapping("/update/{id}")
    public ResponseEntity<UserProductCart> updateCart(@PathVariable Long id, @RequestBody UserProductCart updateCart) {
	 UserProductCart updatedCart = userProductCartService.updateUserProductCart(id, updateCart);
        return new ResponseEntity<>(updatedCart, HttpStatus.OK);
    }
	
	@DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable Long id) {
		userProductCartService.deleteUserProductCart(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
