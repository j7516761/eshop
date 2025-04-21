package com.example.dao.impl;

import com.example.dao.CartDao;
import com.example.pojo.entity.Cart;
import org.springframework.stereotype.Repository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

@Repository
public class CartDaoImpl implements CartDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Cart findCartByUserId(String userId) {
        String hql = "FROM Cart WHERE user.id = :userId";
        return sessionFactory.getCurrentSession().createQuery(hql, Cart.class)
                .setParameter("userId", userId).uniqueResult();
    }

    @Override
    public void saveCart(Cart cart) {
        sessionFactory.getCurrentSession().save(cart);
    }
    
    @Override
    public void updateCart(Cart cart){
        sessionFactory.getCurrentSession().update(cart);
    }
}