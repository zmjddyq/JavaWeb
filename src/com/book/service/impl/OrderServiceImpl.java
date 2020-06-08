package com.book.service.impl;

import com.book.DAO.BookDAO;
import com.book.DAO.Impl.BookDAOImpl;
import com.book.DAO.Impl.OrderDAOImpl;
import com.book.DAO.Impl.OrderItemDAOImpl;
import com.book.DAO.OrderDAO;
import com.book.DAO.OrderItemDAO;
import com.book.pojo.*;
import com.book.service.OrderService;

import java.util.Date;
import java.util.Map;

/**
 * @author zmj
 * @date 2020/5/4 19:35
 * @Description
 */
public class OrderServiceImpl implements OrderService {
    private OrderDAO orderDAO = new OrderDAOImpl();
    private OrderItemDAO orderItemDAO = new OrderItemDAOImpl();
    private BookDAO bookDAO = new BookDAOImpl();
    @Override
    public String createOrder(Cart cart, Integer userId) {
        // 订单号===唯一性
        String orderId = System.currentTimeMillis()+""+userId;
        // 创建一个订单对象
        Order order = new Order(orderId,new Date(),cart.getTotalPrice(), 0,userId);
        // 保存订单
        orderDAO.saveOrder(order);
//        int i = 1/0;
        //获取购物车中的书目
        Map<Integer, CartItem> items = cart.getItems();
        if (items.isEmpty())
        {
            return null;
        }
        for (Map.Entry<Integer, CartItem> CartEntry : items.entrySet()) {
            // 转换为每一个订单项
            CartItem cartItem = CartEntry.getValue();
            OrderItem orderItem = new OrderItem(null,cartItem.getName(),cartItem.getPrice(),cartItem.getTotalPrice(),cartItem.getCount(),orderId);
            //保存到数据库中
            orderItemDAO.saveOrderItem(orderItem);

            //更新库存
            Book book = bookDAO.queryBookById(cartItem.getId());
            book.setSales( book.getSales() + cartItem.getCount() );
            book.setStock( book.getStock() - cartItem.getCount() );
            bookDAO.updateBook(book);
        }
        //清空购物车
        cart.clear();
        //返回订单号
        return orderId;
    }
}
