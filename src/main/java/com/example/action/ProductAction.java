package com.example.action;

import com.example.pojo.entity.Product;
import com.example.service.ProductService;
import com.opensymphony.xwork2.ActionContext;

import org.apache.struts2.dispatcher.Parameter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProductAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	
	@Autowired
    private ProductService productService;
	
    public String listProducts() {
        try {
            // 獲取產品列表
            List<Product> products = productService.getAllProducts();
            int page = getCurrentPage();
            int start = Math.max(0, (page - 1) * 10);
            int end = Math.clamp(start + 10, start, products.size());
            List<Product> subProduct = products.subList(start, end);
            		
            getSession().setAttribute("productList", subProduct);
            getSession().setAttribute("totalPages", products.size() / 10);
                   
            return "SUCCESS"; // 返回視圖名稱 
        } catch (Exception e) {
            e.printStackTrace();
            getSession().setAttribute("errorMessage", "無法獲取產品列表");
            return "ERROR"; // 返回錯誤視圖名稱
        }
    }
    
    private int getCurrentPage()
    {
        // 將產品列表放入請求屬性中
        ActionContext context = ActionContext.getContext();
        // 獲取 request 中的 "page" 參數        
        Parameter pageParam = context.getParameters().get("page");
        int page = 1;      
        if (pageParam != null ) {            
        	try {
        		String valueString = pageParam.getValue();
        		page = Integer.parseInt(valueString);            
        	}  catch (NumberFormatException e) {                             
        		page = 1;
        	}
        }
        return page;
    }
}
