package com.example.dao;

import com.example.pojo.entity.CartItem;

public interface CartItemDao extends BaseDao<CartItem, Integer> {
    CartItem findCartItemById(int id);
}