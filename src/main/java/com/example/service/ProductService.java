package com.example.service;

import java.util.List;
import com.example.pojo.entity.Product;

//ProductService.java
public interface ProductService {

	public Product findProductById(int productId);

	public long findProductAmount();
	
	public long findProductAmountByCategory(int categoryId);
	
	public List<Product> findProducts(int start, int maxResults);
	
	public List<Product> findProductsByCategory(int category, int start, int maxResults);
}
