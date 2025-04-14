package com.example.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.BaseDao;

import java.io.Serializable;
import java.util.List;

@Transactional
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
    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        return getCurrentSession().createQuery("from " + entityClass.getName()).list();
    }
    
    @Override
    public long findAmount()
    {
    	String hql = "SELECT COUNT(*) FROM ";
    	return getCurrentSession().createQuery(hql + entityClass.getName(), Long.class).uniqueResult();   	
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
    public void update(T entity) {
        getCurrentSession().update(entity);
    }
    
}