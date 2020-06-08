package com.book.DAO.Impl;

import com.book.DAO.BookDAO;
import com.book.pojo.Book;
import com.book.pojo.Page;
import com.book.pojo.User;
import com.book.utils.DaoUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Handler;

/**
 * @author zmj
 * @date 2020/4/30 16:24
 * @Description
 */
public class BookDAOImpl implements BookDAO {
    private QueryRunner queryRunner = new QueryRunner();
    private Connection connection = null;
    @Override
    public int addBook(Book book) {
        try {
            connection = DaoUtils.getConnection();
            String sql = "insert into books(`name`,`author`,`price`,`sales`,`stock`,`img_path`) values(?,?,?,?,?,?)";
            return queryRunner.update(connection,sql,book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImgPath());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public int deleteBook(Integer id) {
        try {
            connection = DaoUtils.getConnection();
            String sql = "delete from books where id = ?";
            return queryRunner.update(connection,sql,id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public int updateBook(Book book) {
        try {
            connection = DaoUtils.getConnection();
            String sql = "update books set `name`=?,`author`=?,`price`=?,`sales`=?,`stock`=?,`img_path`=? where id = ?";
            return queryRunner.update(connection,sql,book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImgPath(),book.getId());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public Book queryBookById(Integer id) {
        try {
            connection = DaoUtils.getConnection();
            String sql = "select `id` , `name` , `author` , `price` , `sales` , `stock` , `img_path` imgPath from books where id = ?";
            return queryRunner.query(connection, sql, new BeanHandler<Book>(Book.class), id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Book> queryBooks() {
        try {
            connection = DaoUtils.getConnection();
            String sql = "select `id` , `name` , `author` , `price` , `sales` , `stock` , `img_path` imgPath from books";
            return queryRunner.query(connection, sql, new BeanListHandler<Book>(Book.class));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public Integer queryForPageTotalCount() {
        Integer pageTotalCount = null;
        try {
            connection = DaoUtils.getConnection();
            String sql = "select count(*) from books";
            //方法一：
//            Number query = (Number) queryRunner.query(connection, sql, new ScalarHandler());
//            return query.intValue();
            //方法二：
            Object query = queryRunner.query(connection, sql, new ScalarHandler());
            return Integer.parseInt(query.toString());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Book> queryForPageItems(int begin, int pageSize) {
        try {
            connection = DaoUtils.getConnection();
            String sql = "select `id` , `name` , `author` , `price` , `sales` , `stock` , `img_path` imgPath from books limit ?,?";
            return  queryRunner.query(connection,sql,new BeanListHandler<Book>(Book.class),begin,pageSize);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    @Override
    public Integer queryForPageTotalCountByPrice(int min, int max) {
        try {
            connection = DaoUtils.getConnection();
            String sql = "select count(*) from books where price between ? and ?";
            Object query = queryRunner.query(connection, sql, new ScalarHandler(), min, max);
            return Integer.parseInt(query.toString());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Book> queryForPageItemsByPrice(int begin, int pageSize, int min, int max) {
        try {
            connection = DaoUtils.getConnection();
            String sql = "select `id`,`name`,`author`,`price`,`sales`,`stock`,`img_path` imgPath from books where price between ? and ? order by price limit ?,?";
            return queryRunner.query(connection,sql,new BeanListHandler<Book>(Book.class),min,max,begin,pageSize);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public Integer queryMostExpensive() {
        try {
            connection = DaoUtils.getConnection();
            String sql = "select max(`price`) from books";
            Number maxPrice = (Number)queryRunner.query(connection, sql, new ScalarHandler());
            return maxPrice.intValue();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
