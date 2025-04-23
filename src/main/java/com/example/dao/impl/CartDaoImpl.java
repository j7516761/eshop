package com.example.dao.impl;

import com.example.dao.CartDao;
import com.example.pojo.entity.Cart;
import org.springframework.stereotype.Repository;

@Repository
public class CartDaoImpl extends BaseDaoImpl<Cart, String> implements CartDao {

    public CartDaoImpl() {
        super(Cart.class);
    }

    @Override
    public Cart findCartByUserId(String userId) {
        String hql = "FROM Cart WHERE user.id = :userId";
        return getCurrentSession().createQuery(hql, Cart.class)
                .setParameter("userId", userId).uniqueResult();
    }
}