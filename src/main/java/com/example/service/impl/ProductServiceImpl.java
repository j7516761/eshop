package com.example.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.dao.ProductDao;
import com.example.pojo.entity.Category;
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
		return productDao.findProductAmount();
	}

	@Override
	public long findProductAmountByCategory(int categoryId) {
		return productDao.findProductAmountByCategory(categoryId);
	}

	@Override
	public List<Product> findAllProducts() {
		return productDao.findAll();
	}

	@Override
	public List<Product> findProducts(int start, int maxResults) {
		return productDao.findProducts(start, maxResults);
	}

	@Override
	public void createProduct(Product product) {
		productDao.save(product);
	}

	@Override
	public void updateProduct(Product productDetails) {
//     Product existingProduct = getProductById(productId);
//     
//     existingProduct.setName(productDetails.getName());
//     existingProduct.setDescription(productDetails.getDescription());
//     existingProduct.setPrice(productDetails.getPrice());
//     existingProduct.setStock(productDetails.getStock());
//     existingProduct.setCategory(productDetails.getCategory());
//     
		productDao.update(productDetails);
	}

	@Override
	public void deleteProduct(int productId) {
		Product product = findProductById(productId);
		productDao.delete(product);
	}

	@Override
	public List<Product> findProductsByCategory(Category category) {
		return productDao.findByCategory(category);
	}

	@Override
	public List<Product> findProductsByCategory(int categoryId, int start, int maxResults) {
		return productDao.findByCategory(categoryId, start, maxResults);
	}
}