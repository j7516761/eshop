package com.example.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.CategoryDao;
import com.example.pojo.entity.Category;
import com.example.service.CategoryService;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
	private final CategoryDao categoryDao;

	@Autowired
	public CategoryServiceImpl(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	@Override
	public Category getCategoryById(int categoryId) {
		return categoryDao.findById(categoryId);
	}

	@Override
	public List<Category> getAllCategories() {
		return categoryDao.findAll();
	}

	@Override
	public Category createCategory(Category category) {
		return categoryDao.save(category);
	}

	@Override
	public Category updateCategory(int categoryId, Category categoryDetails) {
		Category existingCategory = getCategoryById(categoryId);
		existingCategory.setName(categoryDetails.getName());
		return categoryDao.save(existingCategory);
	}

	@Override
	public void deleteCategory(int categoryId) {
		categoryDao.deleteById(categoryId);
	}

	@Override
	public Category findByName(String name) {
		return categoryDao.findByName(name);
	}

	@Override
	public List<Category> searchCategories(String keyword) {
		return categoryDao.searchByName(keyword);
	}
}
