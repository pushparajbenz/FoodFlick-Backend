package com.pn.food_cart_management.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.pn.food_cart_management.dto.ProductDTO;
import com.pn.food_cart_management.entity.Product;
import com.pn.food_cart_management.repository.CartRepository;
import com.pn.food_cart_management.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CartRepository cartRepository;

	@Override
    public Product addProduct(ProductDTO productdto) throws IOException {
        Product product = new Product();
        product.setName(productdto.getName());
        product.setPrice(productdto.getPrice());
        product.setCategory(productdto.getCategory());
        product.setImage(productdto.getImage().getBytes()); // Convert MultipartFile to byte array

       return productRepository.save(product);
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

	public void updateProduct(long id,ProductDTO productdto) throws IOException {
        Optional<?> optionalProduct = (Optional<?>) productRepository.findById(id);
        
        if (optionalProduct.isPresent()) {
            ProductDTO product = (ProductDTO) optionalProduct.get();
            product.setName(productdto.getName());
            product.setPrice(productdto.getPrice());
            product.setCategory(productdto.getCategory());
            product.setImage(productdto.getImage());
            MultipartFile image =  productdto.getImage();
            if (image != null && !image.isEmpty()) {
                product.getImage(image.getBytes()); // Update image if a new one is uploaded
            }

            productRepository.save(product);
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
