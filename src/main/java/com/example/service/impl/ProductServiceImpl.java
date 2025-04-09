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
 public Product getProductById(Long productId) {
	 return productDao.findById(productId);
 }

 @Override
 public List<Product> getAllProducts() {
     return productDao.findAll();
 }

 @Override
 public void createProduct(Product product) {
     productDao.save(product);
 }

 @Override
 public void updateProduct(Long productId, Product productDetails) {
     Product existingProduct = getProductById(productId);
     
     existingProduct.setName(productDetails.getName());
     existingProduct.setDescription(productDetails.getDescription());
     existingProduct.setPrice(productDetails.getPrice());
     existingProduct.setStock(productDetails.getStock());
     
     productDao.save(existingProduct);
 }

 @Override
 public void deleteProduct(Long productId) {
     Product product = getProductById(productId);
     productDao.delete(product);
 }

 @Override
 public List<Product> getProductsByCategory(Category category) {
     return productDao.findByCategory(category);
 }
}