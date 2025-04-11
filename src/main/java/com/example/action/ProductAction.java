package com.example.action;

import com.example.pojo.entity.Product;
import com.example.service.ProductService;

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
            // 將產品列表放入請求屬性中
            getRequest().setAttribute("productList", products);
            return "SUCCESS"; // 返回視圖名稱
        } catch (Exception e) {
            e.printStackTrace();
            getRequest().setAttribute("errorMessage", "無法獲取產品列表");
            return "ERROR"; // 返回錯誤視圖名稱
        }
    }
}
