package com.pn.food_cart_management.service;

import java.io.IOException;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

import com.pn.food_cart_management.dto.ProductDTO;
import com.pn.food_cart_management.entity.Product;

public interface ProductService {

    public Product addProduct(ProductDTO productdto) throws IOException;

    public List<Product> fetch();

    public void deleteProduct(long id);

    public void updateProduct(ProductDTO productdto) throws IOException;

    public List<Product> fetchById(long id);

    public List<Product> fetchByIds(long[] ids);

    public List<Product> findProductsByCategory(String category);
}