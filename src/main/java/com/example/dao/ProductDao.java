package com.example.dao;

import java.util.List;

import com.example.pojo.entity.Product;

//ProductDao.java
public interface ProductDao extends BaseDao<Product, Integer> {
		
	public long findProductAmountByCategory(int categoryId);
	
	public List<Product> findProducts(int start, int maxResults);
	
	public List<Product> findByCategory(int categoryId);
	
	public List<Product> findByCategory(int category, int start, int maxResults);
	
	public Product findByName(String name);

	public List<Product> searchByName(String keyword);
}
