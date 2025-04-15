package com.example.action;

import com.example.pojo.entity.Category;
import com.example.pojo.entity.Product;
import com.example.service.CategoryService;
import com.example.service.ProductService;
import com.opensymphony.xwork2.ActionContext;

import org.apache.struts2.dispatcher.Parameter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProductAction extends BaseAction {

	private static int ProductAmountPerPage = 10;
	
	private static final long serialVersionUID = 1L;

	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryService categoryService;
	
	private int categorIdCache;

	public String listProducts() {
		List<Category> categories = categoryService.getAllCategories();
		getSession().setAttribute("categories", categories);

		int categoryId = getCategoryId();
		getSession().setAttribute("selectCategoryId", categoryId);
		
		long productAmount;
		if (categoryId == 0)
			productAmount= productService.findProductAmount();
		else
			productAmount= productService.findProductAmountByCategory(categoryId);		
		getSession().setAttribute("totalPages", Math.ceil((float) productAmount / ProductAmountPerPage));
		
		boolean isCategortIdhange = categorIdCache != categoryId;
		int page = isCategortIdhange ? 0 : getCurrentPageIndex();
		int startIndex = Math.max(0, (page - 1) * ProductAmountPerPage);
		int maxResults = page * ProductAmountPerPage > productAmount ? (int) (productAmount % ProductAmountPerPage) : (int) ProductAmountPerPage;			
					
		List<Product> subProduct;	
		if (categoryId == 0) {
			subProduct = productService.findProducts(startIndex, maxResults);
		} else {				
			subProduct = productService.findProductsByCategory(categoryId, startIndex, maxResults);
		}
		getSession().setAttribute("productList", subProduct);
		
		return "SUCCESS";
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

	private int getCurrentPageIndex() {
		String pageString = getRequest().getParameter("page");
		if (pageString == null)
			return 1;

		try {
			return Integer.parseInt(pageString);
		} catch (NumberFormatException e) {
			return 1;
		}
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
