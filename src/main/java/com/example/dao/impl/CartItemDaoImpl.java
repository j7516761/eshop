package com.example.dao.impl;

import com.example.dao.CartItemDao;
import com.example.pojo.entity.CartItem;
import org.springframework.stereotype.Repository;

@Repository
public class CartItemDaoImpl extends BaseDaoImpl<CartItem, Integer> implements CartItemDao {

    public CartItemDaoImpl() {
        super(CartItem.class);
    }

    @Override
    public CartItem findCartItemById(int id) {
        return getCurrentSession().get(CartItem.class, id);
    }
}