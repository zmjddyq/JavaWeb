package com.book.DAO.Impl;

import com.book.DAO.OrderItemDAO;
import com.book.pojo.OrderItem;
import com.book.utils.DaoUtils;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author zmj
 * @date 2020/5/4 19:15
 * @Description
 */
public class OrderItemDAOImpl implements OrderItemDAO {
    private Connection connection = null;
    private QueryRunner queryRunner = new QueryRunner();
    @Override
    public void saveOrderItem(OrderItem orderItem) {

        try {
            connection = DaoUtils.getConnection();
            String sql = "insert into orders_item(`name`,`price`,`total_money`,`count`,`order_id`) value (?,?,?,?,?)";
            queryRunner.update(connection,sql,orderItem.getName(),orderItem.getPrice(),orderItem.getTotalMoney(),orderItem.getCount(),orderItem.getOrderId());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
}
