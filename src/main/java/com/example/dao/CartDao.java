package com.example.dao;

import com.example.pojo.entity.Cart;

public interface CartDao {
    Cart findCartByUserId(String userId);
    void saveCart(Cart cart);
    void updateCart(Cart cart);
}