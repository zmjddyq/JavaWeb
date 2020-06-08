package com.book.service.impl;

import com.book.DAO.Impl.UserDaoImpl;
import com.book.DAO.UserDAO;
import com.book.pojo.User;
import com.book.service.UserService;
import com.book.utils.DaoUtils;

/**
 * @author zmj
 * @date 2020/5/1 15:16
 * @Description
 */
public class UserServiceImpl implements UserService {
    private UserDAO userDAO = new UserDaoImpl();
    @Override
    public Integer registUser(User user) {
        userDAO.saveUser(user);
        User getUser = userDAO.getUser(user);
        return getUser.getId();
    }

    @Override
    public User login(User user) {
        return userDAO.getUser(user);
    }

    @Override
    public boolean existsUsername(User user) {
        return userDAO.checkUsername(user);
    }
}
