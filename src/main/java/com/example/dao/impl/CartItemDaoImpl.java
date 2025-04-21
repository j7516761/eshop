package com.example.dao.impl;

import com.example.dao.CartItemDao;
import com.example.pojo.entity.CartItem;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CartItemDaoImpl implements CartItemDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public CartItem findCartItemById(int id) {
        return sessionFactory.getCurrentSession().get(CartItem.class, id);
    }

    @Override
    public void saveCartItem(CartItem cartItem) {
        sessionFactory.getCurrentSession().saveOrUpdate(cartItem);
    }

    @Override
    public void deleteCartItem(CartItem cartItem) {
        sessionFactory.getCurrentSession().delete(cartItem);
    }
}