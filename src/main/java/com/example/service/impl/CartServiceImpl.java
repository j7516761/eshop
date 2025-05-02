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
            cart.setUser(user);
            cartDao.save(cart);
        } else {
            Hibernate.initialize(cart.getCartItems());
        }

		return cart;
	}

	@Override
	public void addItemToCart(User user, Product product, int quantity) {
		Cart cart = getCartByUser(user);
		Set<CartItem> cartItems = cart.getCartItems();
		CartItem existingCartItem = null;
		if (cartItems != null)
		{
			existingCartItem = cartItems.stream().filter(item -> item.getProduct().getId() == product.getId())
					.findFirst().orElse(null);
		}

		if (existingCartItem != null) {
			existingCartItem.setQuantity(existingCartItem.getQuantity() + quantity);
			cartItemDao.save(existingCartItem);
		}
		else
		{
			CartItem cartItem = new CartItem();
			cartItem.setCart(cart);
			cartItem.setProduct(product);
			cartItem.setQuantity(quantity);
			cart.addItem(cartItem);
		}
		
		cartDao.update(cart);
	}

	@Override
	public void removeItemFromCart(User user, int productId) {
		Cart cart = getCartByUser(user);
		Set<CartItem> cartItems = cart.getCartItems();

		CartItem cartItemToRemove = cartItems.stream().filter(item -> item.getProduct().getId() == productId)
				.findFirst().orElse(null);

		if (cartItemToRemove != null) {
			cartItems.remove(cartItemToRemove);
			cartItemDao.delete(cartItemToRemove);
			cartDao.update(cart);
		}
	}

	@Override
	public void updateItemQuantity(User user, int productId, int quantity) {
		Cart cart = getCartByUser(user);
		Set<CartItem> cartItems = cart.getCartItems();

		CartItem existingCartItem = cartItems.stream().filter(item -> item.getProduct().getId() == productId)
				.findFirst().orElse(null);

		if (existingCartItem != null) {
			existingCartItem.setQuantity(quantity);
			cartItemDao.save(existingCartItem);
			cartDao.update(cart);
		}
	}

	@Override
	public double calculateTotalAmount(User user) {
		Cart cart = getCartByUser(user);
		Set<CartItem> cartItems = cart.getCartItems();
		return cartItems.stream().mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity()).sum();
	}
}