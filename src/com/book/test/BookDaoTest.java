package com.book.test;


import com.book.DAO.BookDAO;
import com.book.DAO.Impl.BookDAOImpl;
import com.book.pojo.Book;
import org.junit.Test;

import java.math.BigDecimal;

public class BookDaoTest {

    private final BookDAO bookDao = new BookDAOImpl();

    @Test
    public void addBook() {
        bookDao.addBook(new Book(null,"国哥为什么这么帅！",  new BigDecimal(9999),"191125",1100000,0,null));
    }

    @Test
    public void deleteBookById() {
        bookDao.deleteBook(21);
    }

    @Test
    public void updateBook() {
        bookDao.updateBook(new Book(96,"两开花！",  new BigDecimal(9999),"国哥",1100000,0,null
        ));
    }

    @Test
    public void queryBookById() {
        System.out.println( bookDao.queryBookById(21) );
    }

    @Test
    public void queryBooks() {
        for (Book queryBook : bookDao.queryBooks()) {
            System.out.println(queryBook);
        }
    }
}