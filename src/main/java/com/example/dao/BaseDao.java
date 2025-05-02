package com.example.dao;

import java.io.Serializable;

public interface BaseDao<T, ID extends Serializable> {
	public T findById(ID id);

	public T save(T entity);

	public void delete(T entity);

	public void deleteById(ID id);
	
	public T update(T entity);
}
