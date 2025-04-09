package com.example.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.dao.ProductDao;
import com.example.pojo.entity.Category;
import com.example.pojo.entity.Product;

@Repository
public class ProductDaoImpl implements ProductDao {

	@Autowired
	private SessionFactory sessionFactory;

//	@Autowired
//	public ProductDaoImpl(SessionFactory sessionFactory) {
//		this.sessionFactory = sessionFactory;
//	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public Product findById(Long id) {
		return getCurrentSession().get(Product.class, id);
	}

	@Override
	public List<Product> findAll() {
		return getCurrentSession().createQuery("FROM Product", Product.class).getResultList();
	}

	@Override
	public List<Product> findByCategory(Category category) {
		return getCurrentSession().createQuery("FROM Product p WHERE p.category = :category", Product.class)
				.setParameter("category", category).getResultList();
	}

	public List<Product> findByCategory(int categoryId) {
		return getCurrentSession().createQuery("FROM Product p WHERE p.category.id = :categoryId", Product.class)
				.setParameter("categoryId", categoryId).getResultList();
	}

	@Override
	public void save(Product product) {
//		getCurrentSession().persist(product);
		getCurrentSession().save(product);
	}

	@Override
	public void update(Product product) {
		getCurrentSession().merge(product);
	}

	@Override
	public void delete(Product product) {
		getCurrentSession().remove(product);
	}
}