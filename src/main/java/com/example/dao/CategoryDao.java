package com.example.dao;

import java.util.List;

import com.example.pojo.entity.Category;

//CategoryDao.java
public interface CategoryDao {

	public Category findById(int id);

	public List<Category> findAll();

	public Category save(Category category);

	public void deleteById(int id);

	public Category findByName(String name);

	public List<Category> searchByName(String keyword);
}
