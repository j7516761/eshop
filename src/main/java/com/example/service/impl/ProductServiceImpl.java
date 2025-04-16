package com.example.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.ProductDao;
import com.example.pojo.entity.Product;
import com.example.service.ProductService;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	private static int ProductAmountPerPage = 3;
	
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
	public int findTotalPages(int categoryId)
	{
		long productAmount = findProductAmountByCategory(categoryId);
		return (int)Math.ceil((float) productAmount / ProductAmountPerPage);
	}

	@Override
	public List<Product> findProducts(int start, int maxResults) {
		return productDao.findProducts(start, maxResults);
	}

	@Override
	public List<Product> findProductsByCategory(int categoryId, int pageIndex) {		
		long productAmount = findProductAmount();
		int startIndex = Math.max(0, (pageIndex - 1) * ProductAmountPerPage);
		int maxResults = pageIndex * ProductAmountPerPage > productAmount ? (int) (productAmount % ProductAmountPerPage) : (int) ProductAmountPerPage;			
	
		if (categoryId == 0)
			return findProducts(startIndex, maxResults);
		
		return productDao.findByCategory(categoryId, startIndex, maxResults);
	}
}