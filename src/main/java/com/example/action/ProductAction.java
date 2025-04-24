package com.example.action;

import com.example.pojo.entity.Product;
import com.example.service.ProductService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class ProductAction extends BaseAction {

	private int categoryId = 0;
	
	private int currentPage = 1;

	private int totalPages;
	
	private List<Product> products = new ArrayList<>();

	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory.getLogger(ProductAction.class);

	@Autowired
	private ProductService productService;

	public String execute() {
		listProducts();
		return SUCCESS;
	}
	
	public String productDetail()
	{
		return SUCCESS;
	}

	public String listProducts() {
	
		//String i18nTestMessage = getText("product.logTest", "default", "Hello i18n");
		//logger.info(i18nTestMessage);
		
		totalPages = productService.findTotalPages(categoryId);

		products = productService.findProductsByCategory(categoryId, currentPage);

		return SUCCESS;
	}

	public int getTotalPages() {
		return totalPages;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = (currentPage < 1) ? 1 : currentPage;
	}

	public List<Product> getProducts() {
		return products;
	}
}
