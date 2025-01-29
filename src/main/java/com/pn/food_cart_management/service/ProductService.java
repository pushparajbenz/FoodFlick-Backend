package com.pn.food_cart_management.service;

import java.util.List;

import com.pn.food_cart_management.entity.Product;

public interface ProductService {

	public void addProduct(Product p);
	public List<Product>fetch();
	public void deleteProduct(long id);
	public void updateProduct(Product p,long id);
	public List<Product> fetchById(long id);
	public List<Product> fetchByIds(long []ids);
	public List<Product> findProductsByCategory(String category);
	
}
