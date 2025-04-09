package com.example.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.dao.CategoryDao;
import com.example.pojo.entity.Category;

@Repository
public class CategoryDaoImpl implements CategoryDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Category findById(int id) {
		return sessionFactory.getCurrentSession().get(Category.class, id);
	}

	@Override
	//@SuppressWarnings("unchecked")
	public List<Category> findAll() {
		return sessionFactory.getCurrentSession().createQuery("FROM Category", Category.class).list();
	}

	@Override
	public Category save(Category category) {
		// sessionFactory.getCurrentSession().saveOrUpdate(category);
		sessionFactory.getCurrentSession().save(category);
		return category;
	}

	@Override
	public void deleteById(int id) {
		Category category = findById(id);
		sessionFactory.getCurrentSession().delete(category);
	}

	@Override
	//@SuppressWarnings("unchecked")
	public Category findByName(String name) {
		return sessionFactory.getCurrentSession().createQuery("FROM Category WHERE name = :name", Category.class)
				.setParameter("name", name).uniqueResult();
	}

	@Override
	//@SuppressWarnings("unchecked")
	public List<Category> searchByName(String keyword) {
		return sessionFactory.getCurrentSession().createQuery("FROM Category WHERE name LIKE :keyword", Category.class)
				.setParameter("keyword", "%" + keyword + "%").list();
	}
}
