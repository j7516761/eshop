package com.example.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.dao.BaseDao;

import java.io.Serializable;

public abstract class BaseDaoImpl<T, ID extends Serializable> implements BaseDao<T, ID> {

	@Autowired
	protected SessionFactory sessionFactory;

	private final Class<T> entityClass;

	protected BaseDaoImpl(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	protected Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public T findById(ID id) {
		return getCurrentSession().get(entityClass, id);
	}

	@Override
	public T save(T entity) {
		getCurrentSession().saveOrUpdate(entity);
		return entity;
	}

	@Override
	public void delete(T entity) {
		getCurrentSession().delete(entity);
	}

	@Override
	public void deleteById(ID id) {
		T entity = findById(id);
		if (entity != null) {
			delete(entity);
		}
	}

	@Override
	public T update(T entity) {
		 getCurrentSession().update(entity);  // 將實體轉為persistent狀態    
		 getCurrentSession().flush();         // 立即同步到數據庫    
		 getCurrentSession().refresh(entity); // 重新加載最新狀態    
		 return entity;
	}

}