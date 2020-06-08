package com.book.web;

import com.book.pojo.Book;
import com.book.pojo.Page;
import com.book.service.BookService;
import com.book.service.impl.BookServiceImpl;
import com.book.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zmj
 * @date 2020/5/2 8:39
 * @Description
 */
public class ClientBookServlet extends BaseServlet {
    private BookService bookService = new BookServiceImpl();

    /**
     * 处理分页功能
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求的参数 pageNo当前页数 和 pageSize当前页显示个数
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //2.调用分页服务
        Page<Book> page = bookService.page(pageNo, pageSize);
        //设置分页地址
        page.setUrl("client/bookServlet?action=page");
        //3.保存page对象到Request中
        req.setAttribute("page", page);
        //4.请求转发到/pages/client/index.jsp页面
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
    }

    /**
     * 处理价格区间分页功能
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求参数 pageNo、pageSize、min、max
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        String minPrice = req.getParameter("min");
        int min = WebUtils.parseInt(minPrice, 0);
        String maxPrice = req.getParameter("max");
        int max = WebUtils.parseInt(maxPrice, bookService.queryMostExpensive());
        //2.调用bookService.pageByPrice(pageNo,pageSize,min,max):page对象
        Page<Book> page = bookService.pageByPrice(pageNo, pageSize, min, max);
        //设置分页地址
            page.setUrl("client/bookServlet?action=pageByPrice&min=" + min + "&max=" + max);
        //3.pageNo、pageSize、min、max
        req.setAttribute("page", page);
        //4.请求转发到/pages/client/index.jsp页面
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
    }
}
