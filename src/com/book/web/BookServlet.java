package com.book.web;

import com.book.pojo.Book;
import com.book.pojo.Page;
import com.book.service.BookService;
import com.book.service.impl.BookServiceImpl;
import com.book.utils.DaoUtils;
import com.book.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author zmj
 * @date 2020/4/30 18:50
 * @Description
 */
public class BookServlet extends BaseServlet {
    private BookService bookService = new BookServiceImpl();
    /**
     * 获取图书清单
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private  void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Book> books = bookService.queryBooks();
        req.setAttribute("books", books);
        req.getRequestDispatcher("/book/pages/manager/book_manager.jsp").forward(req, resp);
    }

    /**
     * 添加图书
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private  void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求的参数，封装成bean对象
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());
        String pageNo = req.getParameter("pageNo");
        int i = WebUtils.parseInt(pageNo, 0);
        bookService.addBook(book);
        //错误，通过转发会有刷新重复提交的bug
//        req.getRequestDispatcher("/manager/bookServlet").forward(req,resp);
        //重定向
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + (i + 1));
    }

    /**
     * 删除图书
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private  void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        bookService.deleteBookById(WebUtils.parseInt(id,0));
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + req.getParameter("pageNo"));
    }

    /**
     * 通过id获取图书信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private  void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        Book book = bookService.queryBookById(WebUtils.parseInt(id,0));
        req.setAttribute("bookMess",book);
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req,resp);
    }

    /**
     * 更新图书信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private  void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());

        bookService.updateBook(book);
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + req.getParameter("pageNo"));
    }

    /**
     *处理分页功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求的参数 pageNo当前页数 和 pageSize当前页显示个数
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //2.调用分页服务
        Page<Book> page = bookService.page(pageNo, pageSize);
        //设置分页地址
        page.setUrl("manager/bookServlet?action=page");
        //3.保存page对象到Request中
        req.setAttribute("page",page);
        //4.请求转发到/pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }

}
