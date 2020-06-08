package com.book.DAO;

import com.book.pojo.OrderItem;

/**
 * @author zmj
 * @date 2020/5/4 19:14
 * @Description
 */
public interface OrderItemDAO {
    /**
     * 保存订单项
     * @param orderItem
     */
    void saveOrderItem(OrderItem orderItem);
}
