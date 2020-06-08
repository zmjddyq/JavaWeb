package com.book.web;

import com.book.pojo.Cart;
import com.book.pojo.User;
import com.book.service.OrderService;
import com.book.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zmj
 * @date 2020/5/4 20:08
 * @Description
 */
public class OrderServlet extends BaseServlet {
    private OrderService orderService = new OrderServiceImpl();

    /**
     * 创建订单
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void creatOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取购物车
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        //获取登录账户信息
        User user = (User) req.getSession().getAttribute("user");
        //如果未登入，跳转到登录页面
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/pages/user/login.jsp");
            return;
        }
        //生成订单
        String orderId = orderService.createOrder(cart, user.getId());
        //保存订单号
        req.getSession().setAttribute("orderId", orderId);
        resp.sendRedirect(req.getContextPath() + "/pages/cart/checkout.jsp");

    }
}
