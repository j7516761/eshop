package com.example.service.impl;

import com.example.dao.CartDao;
import com.example.dao.CartItemDao;
import com.example.pojo.entity.Cart;
import com.example.pojo.entity.CartItem;
import com.example.pojo.entity.Product;
import com.example.pojo.entity.User;
import com.example.service.CartService;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class CartServiceImpl implements CartService {

	@Autowired
	private CartDao cartDao;

	@Autowired
	private CartItemDao cartItemDao;

	@Override
	public Cart getCartByUser(User user) {
		Cart cart = cartDao.findCartByUserId(user.getId());
		if (cart == null) {
			cart = new Cart();
			cart.setUser(user); // 假設 User 類有對應的構造函數
			cartDao.saveCart(cart);
		} else {
			// 確保 cart 也能加載 cartItems
			Hibernate.initialize(cart.getCartItems());
		}
		return cart;
	}

	@Override
	public void addItemToCart(User user, Product product, int quantity) {
		Cart cart = getCartByUser(user);
		Set<CartItem> cartItems = cart.getCartItems();

		CartItem existingCartItem = cartItems.stream().filter(item -> item.getProduct().getId() == product.getId())
				.findFirst().orElse(null);

		if (existingCartItem != null) {
			existingCartItem.setQuantity(existingCartItem.getQuantity() + quantity);
			cartItemDao.saveCartItem(existingCartItem);
		} else {
			CartItem cartItem = new CartItem(cart, product, quantity);
			cartItems.add(cartItem);
			cartItemDao.saveCartItem(cartItem);
		}

		cartDao.updateCart(cart);
	}

	@Override
	public void removeItemFromCart(User user, int productId) {
		Cart cart = getCartByUser(user);
		Set<CartItem> cartItems = cart.getCartItems();

		CartItem cartItemToRemove = cartItems.stream().filter(item -> item.getProduct().getId() == productId)
				.findFirst().orElse(null);

		if (cartItemToRemove != null) {
			cartItems.remove(cartItemToRemove);
			cartItemDao.deleteCartItem(cartItemToRemove);
		}

		cartDao.updateCart(cart);
	}

	// 新增更新商品數量的方法
	@Override
	public void updateItemQuantity(User user, int productId, int quantity) {
		Cart cart = getCartByUser(user);
		Set<CartItem> cartItems = cart.getCartItems();
		CartItem existingCartItem = cartItems.stream().filter(item -> item.getProduct().getId() == productId)
				.findFirst().orElse(null);
		if (existingCartItem != null) {
			existingCartItem.setQuantity(quantity);
			cartItemDao.saveCartItem(existingCartItem);
		}
		cartDao.updateCart(cart);
	}

	// 新增計算總金額的方法
	@Override
	public double calculateTotalAmount(User user) {
		Cart cart = getCartByUser(user);
		Set<CartItem> cartItems = cart.getCartItems();
		double totalAmount = 0.0;
		for (CartItem item : cartItems) {
			Product product = item.getProduct();
			totalAmount += product.getPrice() * item.getQuantity();
		}
		return totalAmount;
	}
}