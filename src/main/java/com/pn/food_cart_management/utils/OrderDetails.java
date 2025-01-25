package com.pn.food_cart_management.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetails {

	private String name;
	private int quantity;
	private long price;

}
