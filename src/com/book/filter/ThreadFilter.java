package com.book.filter;

import com.book.utils.DaoUtils;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author zmj
 * @date 2020/5/5 11:41
 * @Description
 */
public class ThreadFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            filterChain.doFilter(servletRequest,servletResponse);
            DaoUtils.commitAndClose();
        } finally {
            DaoUtils.rollBackAndClose();
        }
    }

    @Override
    public void destroy() {

    }
}
