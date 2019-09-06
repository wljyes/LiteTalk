package club.wljyes.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBUtil {

    private static Logger logger = LoggerFactory.getLogger(DBUtil.class);
//    //获取单例
//    public static ComboPooledDataSource getConnectionPool() {
//        if (connectionPool == null) {
//            synchronized (DBUtil.class) {
//                if (connectionPool == null)
//                    connectionPool = new Com;
//            }
//        }
//        return connectionPool;
//    }
    //获取连接
    public static Connection getConnection() {
        try {
            return connectionPool.getConnection();
        } catch (SQLException e) {
            logger.error("Exception in DBUtil when getting connection.", e);
            e.printStackTrace();
        }
        return null;
    }

    //归还连接
    public static void returnConnection(Connection connection) {
        if(connection == null) {
            return;
        }

        try {
            //该connection的close方法已被重写
            connection.close();
        } catch (SQLException e) {
            logger.error("Exception in DBUtil when return connection.", e);
            e.printStackTrace();
        }
    }

    private static ComboPooledDataSource connectionPool = new ComboPooledDataSource("mysql");
}
