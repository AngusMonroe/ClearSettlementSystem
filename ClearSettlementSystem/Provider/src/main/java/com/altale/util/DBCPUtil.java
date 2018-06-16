package com.altale.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.*;

public class DBCPUtil {

    // 加载名称为mysqlConn 的配置(src下放置 db_dbcp.properties 配置文件)
    private static BasicDataSource ds = null;

    /**
     * 定义一个ThreadLocal,绑定Connection，每个线程对应一个Connection,执行事务使用
     */
    private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();

    static {
        Properties props = new Properties();
        try {
            // 1.加载Properties文件输入流
            InputStream is = DBCPUtil.class.getClassLoader().getResourceAsStream("db_dbcp.properites");
            // 2.加载载配置
            props.load(is);
            is.close();
            // 3. 创建数据源
            ds = BasicDataSourceFactory.createDataSource(props);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @return BasicDataSource
     */
    public static DataSource getDataSource() {
        return ds;
    }

    /**
     *
     * @return 由BasicDataSource创建的 Connection
     */
    public static Connection getConnection() {
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @return 获取当前线程绑定的Connection
     * @throws SQLException
     */
    public static Connection getTranConnection() throws SQLException{
        //得到ThreadLocal中的connection
        Connection conn = tl.get();
        //判断conn是否为空，如果不为空，则说明事务已经开启
        if(conn == null){
            conn = getConnection();
            //把当前开启的事务放入ThreadLocal中
            tl.set(conn);
        }
        return conn;
    }


    /**
     * 开启事务，如果当前线程中没有Connection，则创建该线程对应的一个Connection
     * @throws SQLException
     */
    public static void beginTran() throws SQLException {
        //设置事务提交为手动
        getTranConnection().setAutoCommit(false);
    }




    /**
     * 提交事务
     * @throws SQLException
     */
    public static void commit() throws SQLException {
        //得到ThreadLocal中的connection
        Connection conn = getTranConnection();
        //判断conn是否为空，如果为空，则说明没有开启事务
        if(conn != null){
            //如果conn不为空,提交事务
            conn.commit();
            //事务提交后，关闭连接
            conn.close();
            //将连接移出ThreadLocal
            tl.remove();
        }
    }

    /**
     * 回滚事务
     * @throws SQLException
     */
    public static void rollback() throws SQLException {
        //得到ThreadLocal中的connection
        Connection conn = getTranConnection();
        //判断conn是否为空，如果为空，则说明没有开启事务，也就不能回滚事务
        if(conn != null){
            //事务回滚
            conn.rollback();
            //事务回滚后，关闭连接
            conn.close();
            //将连接移出ThreadLocal
            tl.remove();
        }
    }

}