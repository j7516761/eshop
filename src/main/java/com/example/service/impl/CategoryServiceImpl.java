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
	public List<Category> getAllCategories()
	{
		return categoryDao.findAll();
	}

	@Override
	public List<Category> searchCategories(String keyword) {
		return categoryDao.searchByName(keyword);
	}
}
