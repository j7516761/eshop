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
		if (categoryId == 0)
			return findProductAmount();
					
		return productDao.findProductAmountByCategory(categoryId);
	}

	@Override
	public List<Product> findProducts(int start, int maxResults) {
		return productDao.findProducts(start, maxResults);
	}

	@Override
	public List<Product> findProductsByCategory(int categoryId, int pageIndex, int productAmountPerPage) {		
		long productAmount = findProductAmount();
		int startIndex = Math.max(0, (pageIndex - 1) * productAmountPerPage);
		int maxResults = pageIndex * productAmountPerPage > productAmount ? (int) (productAmount % productAmountPerPage) : (int) productAmountPerPage;			
	
		if (categoryId == 0)
			return findProducts(startIndex, maxResults);
		
		return productDao.findByCategory(categoryId, startIndex, maxResults);
	}
}