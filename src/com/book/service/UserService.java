package com.book.service;

import com.book.pojo.User;

/**
 * @author zmj
 * @date 2020/4/30 18:53
 * @Description
 */
public interface UserService {
    /**
     * 注册用户
     * @param user
     */
    public Integer registUser(User user);

    /**
     * 登录
     * @param user
     * @return 如果返回null，说明登录失败，返回有值，是登录成功
     */
    public User login(User user);

    /**
     * 检查 用户名是否可用
     * @param User user
     * @return 返回true表示用户名已存在，返回false表示用户名可用
     */
    public boolean existsUsername(User user);
}
