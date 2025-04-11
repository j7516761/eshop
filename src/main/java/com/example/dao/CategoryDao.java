package com.example.dao;

import java.util.List;

import com.example.pojo.entity.Category;

//CategoryDao.java
public interface CategoryDao {

	Category findById(int id);

	List<Category> findAll();

	Category save(Category category);

	void deleteById(int id);

	Category findByName(String name);

	List<Category> searchByName(String keyword);
}
