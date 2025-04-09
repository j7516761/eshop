package com.example.service;

import java.util.List;

import com.example.pojo.entity.Category;
import com.example.pojo.entity.Product;

//ProductService.java
public interface ProductService {

	Product getProductById(int productId);

	List<Product> getAllProducts();

	void createProduct(Product product);

	void updateProduct(int productId, Product product);

	void deleteProduct(int productId);

	List<Product> getProductsByCategory(Category category);
}
