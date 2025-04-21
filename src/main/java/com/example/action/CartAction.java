package com.example.action;

import com.example.constant.ConstantName;
import com.example.pojo.entity.Cart;
import com.example.pojo.entity.CartItem;
import com.example.pojo.entity.Product;
import com.example.pojo.entity.User;
import com.example.service.CartService;
import com.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
public class CartAction extends BaseAction {

	private User user;
	
    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    private int productId;
    private Cart cart;

    public String viewCart() {
        cart = cartService.getCartByUser(getUser());
        
        Set<CartItem> items = cart.getCartItems();
        getRequest().setAttribute("cartItems", items);
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

    private User getUser() {
    	user = (User)getSession().getAttribute(ConstantName.SESSION_USER);
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
}