package com.pn.food_cart_management.exception;

public class OrderNotFoundException extends RuntimeException{
	
	public OrderNotFoundException(String message) {
		super(message);
	}

	public OrderNotFoundException() {
		super();
		
	}
	
}
