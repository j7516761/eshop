package com.example.service;

import java.util.List;
import com.example.pojo.entity.Product;

//ProductService.java
public interface ProductService {

	Product getProductById(int productId);

	long findProductAmount();
	
	long findProductAmountByCategory(int categoryId);
	
	int getTotalPages(int categoryId);
	
	List<Product> findProducts(int start, int maxResults);
	
	List<Product> getProductsByCategory(int category, int start);
}
