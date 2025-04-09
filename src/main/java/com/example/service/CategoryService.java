package com.example.service;

import java.util.List;
import com.example.pojo.entity.Category;

//CategoryService.java
public interface CategoryService {

	Category getCategoryById(int categoryId);

	List<Category> getAllCategories();

	Category createCategory(Category category);

	Category updateCategory(int categoryId, Category category);

	void deleteCategory(int categoryId);

	Category findByName(String name);

	List<Category> searchCategories(String keyword);
}
