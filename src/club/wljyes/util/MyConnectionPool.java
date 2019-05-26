package club.wljyes.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MyConnectionPool {
    //获取单例
    public static MyConnectionPool getConnectionPool(int size) {
        if (connectionPool == null || connectionPool.size != size) {
            synchronized (MyConnectionPool.class) {
                if (connectionPool == null || connectionPool.size != size)
                    connectionPool = new MyConnectionPool(size);
            }
        }
        return connectionPool;
    }
    //获取连接
    public synchronized Connection getConnection() {
        while (connections.isEmpty()) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return connections.remove(0);
    }
    //归还连接
    public synchronized void returnConnection(Connection connection) {
        connections.add(connection);
        this.notify();
    }


    //单例
    private volatile static MyConnectionPool connectionPool;

    private List<Connection> connections = new ArrayList<>();
    private int size;

    private MyConnectionPool(int size) {
        this.size = size;
        init();
    }

    private void init() {
        for (int i = 0; i < size; i++) {
            try {
                connections.add(DriverManager.getConnection("jdbc:mysql://127.0.0.1/lite_talk?serverTimezone=GMT%2B8" +
                        "&characterEncoding=utf-8", "root", "admin"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
