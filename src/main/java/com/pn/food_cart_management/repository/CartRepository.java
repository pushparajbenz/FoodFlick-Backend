package com.pn.food_cart_management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pn.food_cart_management.entity.Cart;
import com.pn.food_cart_management.entity.Product;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{

	
//@Query("select c from Cart c where c.userId=?1and c.inorder=?2")
	List<Cart> findByUserIdAndInorder(long userId,boolean inorder);

	void deleteAllByProduct(Product product);

	Cart findByUserIdAndInorderAndProduct(long i, boolean b, Product product);

}
