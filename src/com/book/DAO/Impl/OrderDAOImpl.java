package com.book.DAO.Impl;

import com.book.DAO.OrderDAO;
import com.book.pojo.Order;
import com.book.utils.DaoUtils;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author zmj
 * @date 2020/5/4 18:51
 * @Description
 */
public class OrderDAOImpl implements OrderDAO {
    private Connection connection = null;
    private QueryRunner queryRunner = new QueryRunner();
    @Override
    public void saveOrder(Order order) {
        try {
            connection = DaoUtils.getConnection();
            String sql = "insert into orders(`order_id`,`create_time`,`total_money`,`status`,`user_id`) value (?,?,?,?,?)";
            queryRunner.update(connection,sql,order.getOrderId(),order.getCreateTime(),order.getTotalMoney(),order.getStatus(),order.getUserId());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
