package com.example.dao.impl;

import java.util.List;

// CategoryDaoImpl.java
import org.springframework.stereotype.Repository;

import com.example.dao.CategoryDao;
import com.example.pojo.entity.Category;

@Repository
public class CategoryDaoImpl extends BaseDaoImpl<Category, Long> implements CategoryDao {

    public CategoryDaoImpl() {
        super(Category.class);
    }

    @Override
    public List<Category> searchByName(String keyword) {
        return getCurrentSession()
            .createQuery("FROM Category WHERE name LIKE :keyword", Category.class)
            .setParameter("keyword", "%" + keyword + "%")
            .list();
    }
}
