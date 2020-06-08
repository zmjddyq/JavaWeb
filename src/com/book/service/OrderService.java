package com.book.service;

import com.book.pojo.Cart;
import com.book.pojo.User;

/**
 * @author zmj
 * @date 2020/5/4 19:33
 * @Description
 */
public interface OrderService {
    /**
     * 生成订单
     * @param cart
     * @param UserId
     * @return String 订单号
     */
    String createOrder(Cart cart,Integer UserId);
}
