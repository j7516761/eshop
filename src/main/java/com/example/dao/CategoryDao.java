package com.example.dao;

import java.util.List;
import com.example.pojo.entity.Category;

//CategoryDao.java
public interface CategoryDao extends BaseDao<Category, Long> {
	List<Category> searchByName(String keyword);
}
