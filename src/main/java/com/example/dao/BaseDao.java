package com.example.dao;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<T, ID extends Serializable> {
	public T findById(ID id);

	public List<T> findAll();
	
	public long findAmount();

	public T save(T entity);

	public void delete(T entity);

	public void deleteById(ID id);
	
	public void update(T entity);
}
