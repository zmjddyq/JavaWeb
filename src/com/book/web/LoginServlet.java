package com.book.web;

import com.book.DAO.Impl.UserDaoImpl;
import com.book.pojo.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author zmj
 * @date 2020/4/29 10:08
 * @Description 1）访问登陆页面
 * 2）填写用户名密码后提交
 * 3）服务器判断用户是否存在
 * 4）如果登陆失败 --->>>> 返回用户名或者密码错误信息
 * 5）如果登录成功 --->>>> 返回登陆成功 信息
 */
@Deprecated
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = new User(username, password);
        //通过用户名密码获取用户信息
        User getUser = (new UserDaoImpl()).getUser(user);

        if (getUser == null) {
            System.out.println("登录失败");
            //记录登陆失败信息
            req.setAttribute("loginFail","用户名或者密码错误");
            //保留用户名
            req.setAttribute("loginUsername",username);
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        }else{
            HttpSession session = req.getSession();
            session.setAttribute("username",getUser.getUsername());
            System.out.println("登录成功,信息: " + getUser);
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
        }
    }
}
