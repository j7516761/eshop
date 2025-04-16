package com.example.service;

import java.util.List;
import com.example.pojo.entity.Product;

//ProductService.java
public interface ProductService {

	Product findProductById(int productId);

	long findProductAmount();
	
	long findProductAmountByCategory(int categoryId);
	
	int findTotalPages(int categoryId);
	
	List<Product> findProducts(int start, int maxResults);
	
	List<Product> findProductsByCategory(int category, int start);
}
