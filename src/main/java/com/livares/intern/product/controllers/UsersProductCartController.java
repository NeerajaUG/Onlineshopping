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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.livares.intern.product.dto.ProductsDTO;
import com.livares.intern.product.models.Products;
import com.livares.intern.product.models.UserProductCart;
import com.livares.intern.product.response.ResponseHandler;
import com.livares.intern.product.services.impl.UserProductCartServiceImpl;

@RestController
@RequestMapping("/cart")
public class UsersProductCartController {
	@Autowired
	UserProductCartServiceImpl userProductCartService;

	@GetMapping("/viewcart/{id}")
	public ResponseEntity<Object> getUserProductCartById(@RequestParam Long id) {
		List<ProductsDTO> cart = userProductCartService.viewCartDataById(id);
		return ResponseHandler.generateResponse("Cart items", HttpStatus.OK, cart);
	}

	@PostMapping("/addtocart")
	public ResponseEntity<Object> addtoCartItems(@RequestParam Long userid, Long productid) {
		UserProductCart addToCart = userProductCartService.addUserProductCart(userid, productid);
		return ResponseHandler.generateResponse("Items added to cart", HttpStatus.CREATED, addToCart);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deleteCart(@PathVariable Long id) {
		userProductCartService.deleteUserProductCart(id);
		return ResponseHandler.generateResponse("Item deleted", HttpStatus.OK,id);
	}

}
