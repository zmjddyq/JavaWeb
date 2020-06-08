package com.book.test;

import com.book.DAO.Impl.OrderDAOImpl;
import com.book.DAO.OrderDAO;
import com.book.pojo.Order;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;


public class OrderDaoTest {

    @Test
    public void saveOrder() {

        OrderDAO orderDao = new OrderDAOImpl();

        orderDao.saveOrder(new Order("1234567891",new Date(),new BigDecimal(100),0, 1));

    }
}