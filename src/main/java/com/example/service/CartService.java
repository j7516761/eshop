package com.example.service;

import com.example.pojo.entity.Cart;
import com.example.pojo.entity.Product;
import com.example.pojo.entity.User;

public interface CartService {
    Cart getCartByUser(User user);
    void addItemToCart(User user, Product product, int quantity);
    void removeItemFromCart(User user, int productId);
}