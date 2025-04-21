package com.example.dao;

import com.example.pojo.entity.CartItem;

public interface CartItemDao {
    CartItem findCartItemById(int id);
    void saveCartItem(CartItem cartItem);
    void deleteCartItem(CartItem cartItem);
}