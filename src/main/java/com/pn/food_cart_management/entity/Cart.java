package com.pn.food_cart_management.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Cart{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long cartId;
	
	private long userId;
	
	private int quantity;
	
	private boolean inorder;
	
	@ManyToOne
	private Product product;

}

