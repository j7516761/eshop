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
		String hql = "FROM Product WHERE name = :name";
		return getCurrentSession().createQuery(hql, Product.class)
				.setParameter("name", name).uniqueResult();
	}

	@Override
	public List<Product> searchByName(String keyword) {
		String hql = "FROM Product WHERE name LIKE :keyword";
		return getCurrentSession().createQuery(hql, Product.class)
				.setParameter("keyword", "%" + keyword + "%").list();
	}

	public long findProductAmountByCategory(int categoryId) {
		String hql = "SELECT COUNT(p) FROM Product p WHERE p.category.id = :categoryId ";
		return getCurrentSession().createQuery(hql, Long.class)
				.setParameter("categoryId", categoryId).uniqueResult();
	}

	@Override
	public List<Product> findAll() {
		return getCurrentSession().createQuery("FROM Product", Product.class).getResultList();
	}

	@Override
	public List<Product> findProducts(int start, int maxResults) {
		String hql = "FROM Product";
		Query<Product> query = getCurrentSession().createQuery(hql, Product.class);
		query.setFirstResult(start);
		query.setMaxResults(maxResults);
		List<Product> products = query.list();
		return products;
	}

	@Override
	public List<Product> findByCategory(int categoryId) {
		String hql = "FROM Product p WHERE p.category.id = :categoryId";
		return getCurrentSession().createQuery(hql, Product.class)
				.setParameter("categoryId", categoryId).getResultList();
	}

	@Override
	public List<Product> findByCategory(int categoryId, int start, int maxResults) {
		String hql = "FROM Product p WHERE p.category.id = :categoryId";
		Query<Product> query = getCurrentSession().createQuery(hql, Product.class)
				.setParameter("categoryId", categoryId);
		query.setFirstResult(start);
		query.setMaxResults(maxResults);
		return query.getResultList();
	}
}