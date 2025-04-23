package com.example.dao;

import com.example.pojo.entity.Cart;

public interface CartDao extends BaseDao<Cart, String> {
    Cart findCartByUserId(String userId);
}