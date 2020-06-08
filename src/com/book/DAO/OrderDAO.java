package com.book.DAO;

import com.book.pojo.Order;

/**
 * @author zmj
 * @date 2020/5/4 18:47
 * @Description
 */
public interface OrderDAO {
    /**
     * 保存订单
     * @param order
     */
    void saveOrder(Order order);
}
