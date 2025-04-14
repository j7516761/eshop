package com.example.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
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
	public Product findById(int id) {
		return getCurrentSession().get(Product.class, id);
	}

	public long findProductAmount() {
		return getCurrentSession().createQuery("SELECT COUNT(p) FROM Product p", Long.class).uniqueResult();
	}

	public long findProductAmountByCategory(int categoryId) {
		return getCurrentSession()
				.createQuery("SELECT COUNT(p) FROM Product p WHERE p.category.id = :categoryId ", Long.class)
				.setParameter("categoryId", categoryId).uniqueResult();
	}

	@Override
	public List<Product> findAll() {
		return getCurrentSession().createQuery("FROM Product", Product.class).getResultList();
	}

	@Override
	public List<Product> findProducts(int start, int maxResults) {
		Session session = sessionFactory.openSession();
		List<Product> products = null;
		try {
			String hql = "FROM Product";
			Query<Product> query = getCurrentSession().createQuery(hql, Product.class);
			// 設置查詢的起始位置和返回的最大數量
			query.setFirstResult(start);
			query.setMaxResults(maxResults);
			products = query.list();
		} finally {
			session.close();
		}
		return products;
	}

	@Override
	public List<Product> findByCategory(Category category) {
		return getCurrentSession().createQuery("FROM Product p WHERE p.category = :category", Product.class)
				.setParameter("category", category).getResultList();
	}

	@Override
	public List<Product> findByCategory(int categoryId) {
		return getCurrentSession().createQuery("FROM Product p WHERE p.category.id = :categoryId", Product.class)
				.setParameter("categoryId", categoryId).getResultList();
	}

	@Override
	public List<Product> findByCategory(int categoryId, int start, int maxResults) {
		Query<Product> query = getCurrentSession()
				.createQuery("FROM Product p WHERE p.category.id = :categoryId", Product.class)
				.setParameter("categoryId", categoryId);
		query.setFirstResult(start);
		query.setMaxResults(maxResults);
		return query.getResultList();
	}

	@Override
	public void save(Product product) {
//		getCurrentSession().persist(product);
		getCurrentSession().save(product);
	}

	@Override
	public void update(Product product) {
		getCurrentSession().saveOrUpdate(product);
	}

	@Override
	public void delete(Product product) {
		getCurrentSession().remove(product);
	}
}