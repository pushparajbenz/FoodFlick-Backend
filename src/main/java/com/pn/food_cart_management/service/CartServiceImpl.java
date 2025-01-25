package com.pn.food_cart_management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pn.food_cart_management.entity.Cart;
import com.pn.food_cart_management.repository.CartRepository;


@Service
public class CartServiceImpl implements CartService {

	@Autowired
	CartRepository cartRepository;

	@Override
	public void addCart(Cart cart) {
		Cart cartsave=cartRepository.findByUserIdAndInorderAndProduct(cart.getUserId(), false,cart.getProduct());
		if(cartsave!=null) {
			cartsave.setQuantity(cartsave.getQuantity()+1);
			cartRepository.save(cartsave);
			return;
		}
		cartRepository.save(cart);
	}

	@Override
	public List<Cart> getCart(long userId) {
		return cartRepository.findByUserIdAndInorder(userId, true);
	}

	@Override
	public void updateCart(Cart cart) {
	  cartRepository.save(cart);
	}

	@Override
	public void deleteCart(long cartId) {
		cartRepository.deleteById(cartId);	
	}
	
	public void moveFromCartToOrder(long userId) {
		
		List<Cart>cart=cartRepository.findByUserIdAndInorder(userId, false);
		for(Cart c:cart) {
			c.setInorder(true);
		}
		cartRepository.saveAll(cart);		
	}
}
