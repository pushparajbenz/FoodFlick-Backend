package com.pn.food_cart_management.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;


import com.pn.food_cart_management.entity.Product;
import com.pn.food_cart_management.repository.ProductRepository;
import com.pn.food_cart_management.service.ProductService;
import com.pn.food_cart_management.service.ProductServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
	
	@Mock
	private ProductRepository productRepository;
	
	@InjectMocks
	private ProductServiceImpl productService;
	
	private Product product;
	
	private List<Product> productList ;
	
	@BeforeEach
	void setUp() {
		product = new Product();
		product.setProductId(1L);
		product.setName("Test product");
		
		productList = new ArrayList<>();
		productList.add(product);
	}
	
	@Test
	void testAddProduct() {
		when(productRepository.save(product)).thenReturn(product);
		productService.addProduct(product);
		verify(productRepository, times(1)).save(product);
	}
	
	@Test
	void testFetchProduct() {
		when(productRepository.findAll()).thenReturn(productList);
		List<Product> res = productService.fetch();
		verify(productRepository, times(1)).findAll();
		assertEquals(productList, res);
	}
	
	@Test
	void testDeleteProduct() {
		doNothing().when(productRepository).deleteById(1L);
		productService.deleteProduct(1L);
		verify(productRepository, times(1)).deleteById(1L);
		
	}
	
	@Test
	void testUpdateProduct() {
		when(productRepository.save(product)).thenReturn(product);
		productService.updateProduct(product, 1L);
		verify(productRepository, times(1)).save(product);
	}
	
	
	

}