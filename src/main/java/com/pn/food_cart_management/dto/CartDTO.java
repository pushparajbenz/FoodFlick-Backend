package com.pn.food_cart_management.dto;

import com.pn.food_cart_management.entity.Product;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CartDTO {
	
	@Column(name="user_id")
	private long userId;
	
	private int quantity;
	
	private boolean inorder;
	
	
	private Product product;
}
