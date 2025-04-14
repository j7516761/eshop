package com.example.dao;

import java.util.List;

import com.example.pojo.entity.Category;
import com.example.pojo.entity.Product;

public interface ProductDao {
	public Product findById(int id);

	public List<Product> findAll();
	
	public long findProductAmount();
	
	public long findProductAmountByCategory(int categoryId);
	
	public List<Product> findProducts(int start, int maxResults);

	public List<Product> findByCategory(Category category);
	
	public List<Product> findByCategory(int categoryId);
	
	public List<Product> findByCategory(int category, int start, int maxResults);

	public void save(Product product);

	public void update(Product product);

	public void delete(Product product);
}
