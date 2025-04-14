package com.example.service;

import java.util.List;

import com.example.pojo.entity.Category;
import com.example.pojo.entity.Product;

//ProductService.java
public interface ProductService {

	public Product getProductById(int productId);

	public long getProductAmount();
	
	public List<Product> getAllProducts();
	
	public List<Product> getProducts(int start, int maxResults);

	public void createProduct(Product product);

	public void updateProduct(Product product);

	public void deleteProduct(int productId);

	public List<Product> getProductsByCategory(Category category);
}
