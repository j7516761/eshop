package com.example.dao;

import java.util.List;

import com.example.pojo.entity.Category;
import com.example.pojo.entity.Product;

public interface ProductDao {
	Product findById(int id);

	List<Product> findAll();

	List<Product> findByCategory(Category category);

	void save(Product product);

	void update(Product product);

	void delete(Product product);
}
