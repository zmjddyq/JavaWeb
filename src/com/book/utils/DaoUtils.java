package com.book.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author zmj
 * @date 2020/4/28 19:49
 * @Description 编写 JdbcUtils 工具类
 */
public class DaoUtils {
    /**
     * 创建数据库连接池
     */
    private static DataSource druidPool = null;
    private static ThreadLocal<Connection> conns = new ThreadLocal<Connection>();

    static {
        try {
            InputStream rs = DaoUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            Properties properties = new Properties();
            properties.load(rs);
            druidPool = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接池连接
     *
     * @return connection连接
     */
    public static Connection getConnection() {
        Connection connection = conns.get();
        try {
            if (connection == null) {
                connection = druidPool.getConnection();
                conns.set(connection);
                connection.setAutoCommit(false);// 设置为手动管理事务
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return connection;
    }

//    /**
//     * 获取数据库连接池连接
//     * @return connection连接
//     */
//    public static Connection getConnection(){
//        try {
//            Connection connection = druidPool.getConnection();
//            return connection;
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        return null;
//    }

    /**
     * 提交事务并关闭连接，还回连接资源给连接池
     */
    public static void commitAndClose() {
        Connection connection = conns.get();
        if (connection != null) {
            try {
                connection.commit();
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            } finally {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        // 一定要执行 remove 操作，否则就会出错。（因为 Tomcat 服务器底层使用了线程池技术）
        conns.remove();
    }

    /**
     * 提交事务并关闭连接，还回连接资源给连接池
     *
     */
    public static void rollBackAndClose() {
        Connection connection = conns.get();
        if (connection != null) {
            try {
                connection.rollback();
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            } finally {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        // 一定要执行 remove 操作，否则就会出错。（因为 Tomcat 服务器底层使用了线程池技术）
        conns.remove();
    }
//    /**
//     * 关闭连接，还回连接资源给连接池
//     * @param conn
//     */
//    public static void closeConnection(Connection conn){
//
//        try {
//            if(conn != null)
//            conn.close();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }

}
