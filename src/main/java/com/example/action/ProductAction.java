package com.example.action;

import com.example.pojo.entity.Category;
import com.example.pojo.entity.Product;
import com.example.service.CategoryService;
import com.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class ProductAction extends BaseAction {

	private static int ProductAmountPerPage = 3;

	private int totalPages; // 總頁數
	private List<Product> products = new ArrayList<>();

	private static final long serialVersionUID = 1L;

	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryService categoryService;

	private int categoryIdCache;

	public String execute() {
		listProducts();
		return SUCCESS;
	}

	public String listProducts() {
		//List<Category> categories = categoryService.getAllCategories();
		//getRequest().setAttribute("categories", categories);

		int categoryId = getCategoryId();
		//getRequest().setAttribute("selectCategoryId", categoryId);

		long productAmount = productService.findProductAmountByCategory(categoryId);
		totalPages = (int) Math.ceil((float) productAmount / ProductAmountPerPage);

		int pageIndex = getCurrentPageIndex(categoryId);
		products = productService.findProductsByCategory(categoryId, pageIndex, ProductAmountPerPage);

		categoryIdCache = categoryId;
		return "SUCCESS";
	}

	public int getTotalPages() {
		return totalPages;
	}

	private int getCategoryId() {
		String categoryString = getRequest().getParameter("category");
		if (categoryString == null)
			return 0;

		try {
			return Integer.parseInt(categoryString);
		} catch (NumberFormatException e) {
			return 0;
		}
	}

	private int getCurrentPageIndex(int categoryId) {

		if (categoryIdCache != categoryId)
			return 1;

		String pageString = getRequest().getParameter("currentPage");
		if (pageString == null)
			return 1;

		try {
			return Integer.parseInt(pageString);
		} catch (NumberFormatException e) {
			return 1;
		}
	}

	public List<Product> getProducts() {
		return products;
	}

	public String initDB() {
//    	for(int i = 1; i <= 50; i++)
//    	{
//    		Product p = new Product();
//    		p.setId(i);
//    		p.setName("Product" + i);
//    		p.setDescription("ProductDescription"+ i);
//    		p.setPrice(i * 100);
//    		p.setStock(i * 100);
//    		Category c = categoryService.getCategoryById(i / 10 + 1);
//    		p.setCategory(c);  		
//    		productService.updateProduct(p);
//    	}

		return "SUCCESS";
	}
}
