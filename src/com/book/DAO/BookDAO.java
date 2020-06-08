package com.book.DAO;

import com.book.pojo.Book;
import com.book.pojo.Page;

import java.util.List;

/**
 * @author zmj
 * @date 2020/4/30 16:15
 * @Description
 */
public interface BookDAO {
    /**
     * 添加书书目
     * @return 完成返回影响行数,失败返回-1
     */
    int addBook(Book book);
    /**
     * 通过id删除书目
     * @return 完成返回影响行数,失败返回-1
     */
    int deleteBook(Integer id);
    /**
     * 通过id修改书目
     * @return 完成返回影响行数,失败返回-1
     */
    int updateBook(Book book);
    /**
     * 通过id查询书目
     * @return 完成返回影响行数,失败返回-1
     */
    Book queryBookById(Integer id);

    /**
     * 获取图书列表
     * @return 图书列表
     */
    List<Book> queryBooks();

    /**
     * 获取总记录数
     * @return Integer总记录数
     */
    Integer queryForPageTotalCount();

    /**
     * 获取当前页中的书目
     * @param begin
     * @param pageSize
     * @return 当前页中的书目
     */
    List<Book> queryForPageItems(int begin, int pageSize);

    /**
     * 获取价格区间内的书的总数
     * @param min
     * @param max
     * @return Integer
     */
    Integer queryForPageTotalCountByPrice(int min, int max);


    /**
     * 获取价格区间内的书目
     * @param begin
     * @param pageSize
     * @param min
     * @param max
     * @return List<Book>
     */
    List<Book> queryForPageItemsByPrice(int begin, int pageSize, int min, int max);

    /**
     * 获取所有书中价格最高的书的价格
     * @return Integer
     */
    Integer queryMostExpensive();
}
