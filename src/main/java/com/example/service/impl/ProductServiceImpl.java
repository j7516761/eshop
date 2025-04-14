package com.example.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.dao.ProductDao;
import com.example.pojo.entity.Product;
import com.example.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;

	@Override
	public Product findProductById(int productId) {
		return productDao.findById(productId);
	}

	@Override
	public long findProductAmount() {
		return productDao.findAmount();
	}

	@Override
	public long findProductAmountByCategory(int categoryId) {
		return productDao.findProductAmountByCategory(categoryId);
	}

	@Override
	public List<Product> findProducts(int start, int maxResults) {
		return productDao.findProducts(start, maxResults);
	}

	@Override
	public List<Product> findProductsByCategory(int categoryId, int start, int maxResults) {
		return productDao.findByCategory(categoryId, start, maxResults);
	}
}