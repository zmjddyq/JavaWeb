package com.book.test;

import com.book.pojo.Page;
import com.book.service.BookService;
import com.book.service.impl.BookServiceImpl;
import org.junit.Test;

/**
 * @author zmj
 * @date 2020/5/1 15:40
 * @Description
 */
public class BookServicel {
    BookService bookService = new BookServiceImpl();
    @Test
    public void page(){
        System.out.println(bookService.page(1, Page.PAGE_SIZE ));
    }
    @Test
    public void pageByPrice(){
        System.out.println(bookService.pageByPrice(1, Page.PAGE_SIZE,10,50 ));
    }
}
