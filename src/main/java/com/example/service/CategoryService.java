package com.example.service;

import java.util.List;
import com.example.pojo.entity.Category;

//CategoryService.java
public interface CategoryService {

	List<Category> getAllCategories();
	
	List<Category> searchCategories(String keyword);
}
