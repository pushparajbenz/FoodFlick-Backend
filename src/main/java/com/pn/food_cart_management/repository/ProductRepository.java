package com.pn.food_cart_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pn.food_cart_management.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long>{

}
