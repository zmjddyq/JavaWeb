package com.book.DAO.Impl;

import com.book.DAO.UserDAO;
import com.book.pojo.User;
import com.book.utils.DaoUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author zmj
 * @date 2020/4/28 20:25
 * @Description
 */
public  class UserDaoImpl implements UserDAO {


    /**
     * 根据User对象中的用户名和密码查询用户信息
     *
     * @param user
     * @return User 数据库中有记录 null 数据库中无此记录
     */
    @Override
    public  User getUser(User user) {
        Connection connection = null;
        User u = null;
        try {
            connection = DaoUtils.getConnection();
            String sql = "select `id`,`username`,`password`,`email` from  users where username = ? and password = ?";
            QueryRunner queryRunner = new QueryRunner();
            u = queryRunner.query(connection, sql, new BeanHandler<User>(User.class), user.getUsername(), user.getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return u;
    }



    /**
     * 根据User对象中的用户名从数据库中获取一条记录
     *
     * @param user
     * @return true 数据库中有记录 false 数据库中无此记录
     */
    @Override
    public boolean checkUsername(User user) {
        Connection connection = null;
        User query = null;
        try {
            connection = DaoUtils.getConnection();
            String sql = "select `id`,`username`,`password`,`email` from  users where username = ?";
            QueryRunner queryRunner = new QueryRunner();
            query = queryRunner.query(connection, sql, new BeanHandler<User>(User.class), user.getUsername());
            if (query == null) {
                return false;
            } else {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }



    /**
     * 向数据库中插入User对象
     *
     * @param user
     */
    @Override
    public void saveUser(User user) {
        Connection connection = null;
        try {
            connection = DaoUtils.getConnection();
            String sql = "insert into users(`username`,`password`,`email`) value (?,?,?)";
            QueryRunner queryRunner = new QueryRunner();
            queryRunner.update(connection, sql, user.getUsername(), user.getPassword(), user.getEmail());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
