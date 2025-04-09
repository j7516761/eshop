package com.example.service;

import java.util.List;

import com.example.pojo.entity.Category;
import com.example.pojo.entity.Product;

//ProductService.java
public interface ProductService {

	Product getProductById(Long productId);

	List<Product> getAllProducts();

	void createProduct(Product product);

	void updateProduct(Long productId, Product product);

	void deleteProduct(Long productId);

	List<Product> getProductsByCategory(Category category);
}
