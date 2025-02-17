package com.pn.food_cart_management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pn.food_cart_management.dto.ProductDTO;
import com.pn.food_cart_management.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long>{

	List<Product> findByCategory(String category);

	

}
