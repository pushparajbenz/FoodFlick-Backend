package com.pn.food_cart_management.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pn.food_cart_management.entity.Product;
import com.pn.food_cart_management.service.ProductService;


@RestController
@RequestMapping("product-rest")
public class ProductController {

	@Autowired
	ProductService productservice;

	private static final Logger logger = LogManager.getLogger(ProductController.class);

	@PostMapping("/admin/product")
	public ResponseEntity<String> addProduct(@RequestBody Product p) {
		logger.info("In Add Product Endpoint");

		try {
			this.productservice.addProduct(p);
			return new ResponseEntity<>("Product Added", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Unable to Add", HttpStatus.BAD_REQUEST);
		}
	}

	
	@GetMapping("/user/fetch")
	public ResponseEntity<List<Product>> fetchProduct() {
		logger.info("In Fetch Product Endpoint");
		
		List<Product> products = this.productservice.fetch();
		logger.info("Fetched products: {}", products);

		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}

	
	@DeleteMapping("/admin/deleteProduct/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable long id) {
		logger.info("In Delete Product Endpoint, ID: {}", id);
		
		try {
			this.productservice.deleteProduct(id);
			logger.info("Product deleted, ID: {}", id);
			return new ResponseEntity<>("Product Deleted", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Error Occured", HttpStatus.BAD_REQUEST);
		}
	}

	
	@PutMapping("/admin/product/{id}")
	public ResponseEntity<String> updateProduct(@RequestBody Product p,@PathVariable long id) {
		logger.info("In Update Product Endpoint");

		try {
			this.productservice.updateProduct(p,id);
			return new ResponseEntity<>("Product Updated", HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>("Error Occured", HttpStatus.BAD_REQUEST);
		}
	}
	

	@GetMapping("/user/fetch/{Id}")
	public ResponseEntity<List<Product>> fetchById(@PathVariable long Id) {
		logger.info("In Fetch Product By ID Endpoint, ID: {}", Id);
		
		List<Product> products = productservice.fetchById(Id);
		logger.info("Fetched products by ID: {}", products);
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
	

	@GetMapping("/user/fetchids/{Ids}")
	public ResponseEntity<List<Product>> fetchByIds(@PathVariable long[] Ids) {
		logger.info("In Fetch Products By IDs Endpoint, IDs: {}", Ids);

		List<Product> products = productservice.fetchByIds(Ids);
		
		logger.info("Fetched products by IDs: {}", products);
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> findProductsByCategory(@PathVariable String category) {
        List<Product> products = productservice.findProductsByCategory(category);
        if (products.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(products);
    }


}
