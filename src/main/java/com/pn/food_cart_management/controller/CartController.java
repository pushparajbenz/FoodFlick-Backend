package com.pn.food_cart_management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pn.food_cart_management.entity.Cart;
import com.pn.food_cart_management.service.CartService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("cart-rest")
public class CartController {

	private static final Logger logger = LoggerFactory.getLogger(CartController.class);

	@Autowired
	CartService cartService;

	@PostMapping("/addCart")
	public void addCart(@RequestBody Cart cart) {
		logger.info("Adding cart: {}", cart);
		cartService.addCart(cart);
		logger.info("Cart added successfully");
	}

	@GetMapping("/cart/{userId}")
	public List<Cart> getCartsofUser(@PathVariable long userId) {
		logger.info("Fetching carts for user ID: {}", userId);
		List<Cart> carts = cartService.getCart(userId);
		logger.info("Fetched carts: {}", carts);
		return carts;
	}
	
	@PutMapping("/updateCart")
	public void updateCartsofUser(@RequestBody Cart cart) {
		logger.info("Updating cart: {}", cart);
		cartService.updateCart(cart);
		logger.info("Cart updated successfully");
	}
	
	@DeleteMapping("/deleteCart/{cartId}")
	public void deleteCart(@PathVariable long cartId) {
		logger.info("Deleting cart with ID: {}", cartId);
		cartService.deleteCart(cartId);
		logger.info("Cart deleted successfully");
	}
}
