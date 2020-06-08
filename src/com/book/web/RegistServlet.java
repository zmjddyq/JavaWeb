package com.book.web;

import com.book.DAO.Impl.UserDaoImpl;
import com.book.pojo.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zmj
 * @date 2020/4/29 8:43
 * @Description
 */
@Deprecated
public class RegistServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");
        User user = new User(username, password, email);
        UserDaoImpl userDao = new UserDaoImpl();
        System.out.println(user);
        //验证码校验 ,写死asd
        if ("asd".equals(code)) {
            //验证码正确
            //4）当用户已经存在----提示用户注册 失败，用户名已存在
            if (userDao.checkUsername(user)){
                MysetAttribute(req,"用户名已存在",username,password);
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
            }else {
                //5）当用户不存在-----注册成功
                userDao.saveUser(user);
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req,resp);
            }

        }else {
            //验证码错误
            MysetAttribute(req,"验证码错误！",username,email);
            System.out.println("验证码错误----" + code);
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
        }
//        需求 1：用户注册 需求如下：
//        1）访问注册页面
//        2）填写注册信息，提交给服务器
//        3）服务器应该保存用户
//        4）当用户已经存在----提示用户注册 失败，用户名已存在
//        5）当用户不存在-----注册成功
    }


    /**
     * 错误信息记录
     * @param req
     * @param failMessage
     * @param username
     * @param email
     */
    private static void MysetAttribute(HttpServletRequest req,String failMessage,String username,String email){
        //registFail 记录错误信息
        req.setAttribute("registFail",failMessage);
        //回退信息
        req.setAttribute("registUsername",username);
        req.setAttribute("registEmail",email);
    }
}
