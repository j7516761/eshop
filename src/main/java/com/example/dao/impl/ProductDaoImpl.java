package com.example.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.example.dao.ProductDao;
import com.example.pojo.entity.Product;

@Repository
public class ProductDaoImpl extends BaseDaoImpl<Product, Integer> implements ProductDao {

	public ProductDaoImpl() {
		super(Product.class);
	}

	@Override
	public Product findByName(String name) {
		return getCurrentSession().createQuery("FROM Product WHERE name = :name", Product.class)
				.setParameter("name", name).uniqueResult();
	}

	@Override
	public List<Product> searchByName(String keyword) {
		return getCurrentSession().createQuery("FROM Product WHERE name LIKE :keyword", Product.class)
				.setParameter("keyword", "%" + keyword + "%").list();
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
}