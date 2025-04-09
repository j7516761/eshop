package com.example.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.constant.ConstantName;
import com.example.pojo.entity.Category;
import com.example.pojo.entity.Product;
import com.example.pojo.entity.User;
import com.example.service.CategoryService;
import com.example.service.ProductService;
import com.example.service.UserService;

/**
 * LoginAction 處理用戶登入的邏輯，包含登入驗證與處理。
 */
public class LoginAction extends BaseAction {

    private static final long serialVersionUID = 1L;

    // 封裝登入所需的 User 資料
    private User user;

    // 使用 Spring 注入 UserService 進行業務邏輯處理
    @Autowired
    private UserService userService;
    
    @Autowired
    private ProductService productService;
    
    @Autowired CategoryService categoryService;

    /**
     * 透過模型驅動封裝 User 物件
     * @return 用來封裝資料的 User 物件
     */
    @Override
    public User getModel() {
        if (user == null) {
            user = new User();
        }
        return user;
    }

    /**
     * 執行用戶登入邏輯，檢查帳號密碼是否正確。
     * 
     * @return 若登入成功返回 SUCCESS，若登入失敗返回 INPUT
     */
    public String doLogin() {
    	
//    	for(int i = 1; i <= 10; i++)
//    	{
//    		Product p = new Product();
//    		p.setId(i);
//    		p.setName("Product" + i);
//    		p.setPrice(i * 100);
//    		p.setStock((int)i * 100);
//    		productService.createProduct(p);
//    	}
//    	
//    	for(int i = 1; i <= 10; i++)
//    	{
//    		Category c = new Category();
//    		c.setId(i);
//    		c.setName("Category" + i);
//    		categoryService.createCategory(c);
//    	}	
    	
    	List<Product> products = productService.getAllProducts();
    	Product p = products.get(0);
    	Category c = categoryService.getCategoryById((int)10);
    	//p.setName("ProductModify" + p.getId());
    	p.setCategory(c); 	
    	int id = p.getId();
    	productService.updateProduct(id, p);

        // 檢查是否提供了登入 ID 和密碼
        if (user == null || user.getLoginId() == null || user.getPassword() == null) {
            getSession().setAttribute("msg", "請提供有效的帳號與密碼");
            return INPUT;
        }

        // 呼叫 UserService 來獲取使用者資料
        user = userService.getLoginUser(user);

        // 如果用戶資料存在且登入 ID 正確
        if (user != null && !"".equals(user.getLoginId())) {
            // 登入成功，將用戶資料放入 session 中
            getSession().setAttribute(ConstantName.SESSION_USER, user);
            return SUCCESS;
        }

        // 登入失敗，顯示錯誤訊息並返回輸入頁
        getSession().setAttribute("msg", "帳號或密碼錯誤");
        return INPUT;
    }

    // Getter 和 Setter 方法
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
