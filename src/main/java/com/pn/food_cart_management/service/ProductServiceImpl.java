package com.pn.food_cart_management.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pn.food_cart_management.entity.Product;
import com.pn.food_cart_management.repository.CartRepository;
import com.pn.food_cart_management.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CartRepository cartRepository;

	public void addProduct(Product p) {
		try {
			productRepository.save(p);
		} catch (Exception e) {
			throw e;
		}
	}

	public List<Product> fetch() {
		List<Product> ar = new ArrayList<>();
		productRepository.findAll().forEach(product -> ar.add(product));
		return ar;
	}

	@Transactional
	public void deleteProduct(long id) {
		try {
			Optional<Product> product=productRepository.findById(id);
			cartRepository.deleteAllByProduct(product.get());
			productRepository.deleteById(id);	
		}catch(Exception e) {
			throw e;
		}
	}

	public void updateProduct(Product p,long id) {

		try {
			Product product=productRepository.findById(id).get();
			
			product.setName(p.getName());
			product.setImage(p.getImage());
			product.setPrice(p.getPrice());
			product.setCategory(p.getCategory());
			
			productRepository.save(product);
		} catch (Exception e) {
			throw e;
		}
	}

	public List<Product> fetchById(long id) {
		List<Product> res = new ArrayList<>();
		Optional<Product> op = productRepository.findById(id);

		if (op.isPresent()) {
			res.add(op.get());
			return res;
		}

		return res;
	}

	public List<Product> fetchByIds(long[] ids) {
		List<Long> res = new ArrayList<>();
		for (int i = 0; i < ids.length; i++) {
			res.add(ids[i]);
		}
		return productRepository.findAllById(res);
	}

	@Override
	public List<Product> findProductsByCategory(String category) {
		
		return productRepository.findByCategory(category);
	}
	

}
