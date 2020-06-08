package com.book.test;


import com.book.DAO.Impl.OrderItemDAOImpl;
import com.book.DAO.OrderItemDAO;
import com.book.pojo.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;

public class OrderItemDaoTest {

    @Test
    public void saveOrderItem() {
        OrderItemDAO orderItemDao = new OrderItemDAOImpl();

        orderItemDao.saveOrderItem(new OrderItem(null,"java从入门到精通", new BigDecimal(100),new BigDecimal(100),1,"1234567891"));
        orderItemDao.saveOrderItem(new OrderItem(null,"javaScript从入门到精通", new BigDecimal(200),new BigDecimal(100),2,"1234567891"));
        orderItemDao.saveOrderItem(new OrderItem(null,"Netty入门", new BigDecimal(100),new BigDecimal(100),1,"1234567891"));

    }
}