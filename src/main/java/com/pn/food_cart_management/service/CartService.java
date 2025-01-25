package com.pn.food_cart_management.service;

import java.util.List;

import com.pn.food_cart_management.entity.Cart;

public interface CartService {
  public void addCart(Cart cart);
  public List<Cart> getCart(long userId);
  public void updateCart(Cart cart);
  public void deleteCart(long cartId);
  public void moveFromCartToOrder(long userId);
  
}
