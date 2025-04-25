package com.example.action;

import com.example.constant.ConstantName;
import com.example.pojo.entity.Cart;
import com.example.pojo.entity.CartItem;
import com.example.pojo.entity.Product;
import com.example.pojo.entity.User;
import com.example.service.CartService;
import com.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

public class CartAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private User user;

	@Autowired
	private CartService cartService;

	@Autowired
	private ProductService productService;

	private int productId;
	private int quantity;
	private double totalAmount;
	private Cart cart;

	public String viewCart() {
		cart = cartService.getCartByUser(getUser());
		if (cart != null)
		{
			Set<CartItem> items = cart.getCartItems();
			getRequest().setAttribute("cartItems", items);
			totalAmount = cartService.calculateTotalAmount(getUser());
		}
		return SUCCESS;
	}

	public String addToCart() {
		Product product = productService.findProductById(productId);
		if (product != null) {
			cartService.addItemToCart(getUser(), product, 1);
		}
		return SUCCESS;
	}

	public String removeItem() {
		cartService.removeItemFromCart(getUser(), productId);
		return SUCCESS;
	}

	// 新增 updateQuantity 方法
	public String updateQuantity() {
		if (quantity > 0) {
			cartService.updateItemQuantity(getUser(), productId, quantity);
		}
	return SUCCESS;

	}

	private User getUser() {
		user = (User) getSession().getAttribute(ConstantName.SESSION_USER);
		return user;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public Cart getCart() {
		return cart;
	}
	
    public int getQuantity() {       
    	return quantity;    
    }    
    
    public void setQuantity(int quantity) {     
    	this.quantity = quantity;    
    }

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}   
}