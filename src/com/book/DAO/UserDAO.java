package com.book.DAO;

import com.book.pojo.User;

import java.sql.Connection;

/**
 * @author zmj
 * @date 2020/4/28 20:20
 * @Description
 */
public interface UserDAO {
    /**
     * 根据User对象中的用户名和密码从数据库中获取一条记录
     *
     * @param user
     * @return User 数据库中有记录 null 数据库中无此记录
     */
    User getUser( User user);

    /**
     * 根据User对象中的用户名从数据库中获取一条记录
     *
     * @param user
     * @return true 数据库中有记录 false 数据库中无此记录
     */
    boolean checkUsername( User user);

    /**
     * 向数据库中插入User对象
     *
     * @param user
     */
    void saveUser( User user);
}
