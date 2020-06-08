package com.book.web;

import com.book.DAO.Impl.UserDaoImpl;
import com.book.pojo.User;
import com.book.service.UserService;
import com.book.service.impl.UserServiceImpl;
import com.book.utils.WebUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 账户服务
 *
 * @author zmj
 * @date 2020/4/30 10:51
 * @Description
 */
public class UserServlet extends BaseServlet {
    private UserService userService = new UserServiceImpl();

    /**
     * 登陆
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void existsUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        boolean isIn = userService.existsUsername(new User(username));
        Map<String, Object> map = new HashMap<>();
        map.put("isIn",isIn);
        Gson gson = new Gson();
        String s = gson.toJson(map);
        resp.getWriter().write(s);

    }
    /**
     * 登陆
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = WebUtils.copyParamToBean(req.getParameterMap(), new User());
        //通过用户名密码获取用户信息
        User getUser = userService.login(user);

        if (getUser == null) {
            MysetAttribute(req, "用户名或者密码错误", user);
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        } else {
            req.getSession().setAttribute("user", getUser);
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
        }
    }


    /**
     * 注册
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //调用webUtils进行bean注入
        User user = WebUtils.copyParamToBean(req.getParameterMap(), new User());
        //获取验证码信息
        Object token = req.getSession().getAttribute("KAPTCHA_SESSION_KEY");
        if (token != null && token.equals(req.getParameter("code"))) {
            //验证码正确
            //4）当用户已经存在----提示用户注册 失败，用户名已存在
            if (userService.existsUsername(user)) {
                MysetAttribute(req, "用户名已存在", user);
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
            } else {
                //5）当用户不存在-----注册成功
                Integer userId = userService.registUser(user);
                user.setId(userId);
                req.getSession().setAttribute("user", user);
                resp.sendRedirect(req.getContextPath() + "/pages/user/regist_success.jsp");
            }

        } else {
            //验证码错误
            MysetAttribute(req, "验证码错误！", user);
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
        }
    }

    /**
     * 错误信息记录
     *
     * @param req
     * @param failMessage
     * @param user
     */
    private static void MysetAttribute(HttpServletRequest req, String failMessage, User user) {
        //Fail 记录错误信息
        req.setAttribute("Fail", failMessage);
        //回退信息
        req.setAttribute("Username", user.getUsername());
        req.setAttribute("Email", user.getEmail());
    }

    /**
     * 注销
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void outLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.注销Session中的账户信息
        req.getSession().invalidate();
        //2.重定向回首页
        resp.sendRedirect(req.getContextPath() + "/index.jsp");
    }


}
