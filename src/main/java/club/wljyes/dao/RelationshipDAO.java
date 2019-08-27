package club.wljyes.dao;

import club.wljyes.bean.FriendRequest;
import club.wljyes.util.MyConnectionPool;

import java.sql.*;
import java.util.List;
import java.util.Map;

public interface RelationshipDAO<T> {
    //从连接池获取连接
    default Connection getConnection() {
        return MyConnectionPool.getConnectionPool(8).getConnection();
    }
    //切记要归还连接，否则会使连接资源被耗尽，造成获取连接永远等待
    default void returnConnection(Connection c) {
        MyConnectionPool.getConnectionPool(8).returnConnection(c);
    }

    //获取好友列表
    default List<T> getFriendList(String fromUser) throws SQLException {
        return getFriendList(fromUser, 0, Short.MAX_VALUE);
    }

    List<T> getFriendList(String fromUser, int start, int count);

    default int getTotal() {
        int total = 0;

        String sql = "select count(*) from relationship";
        Connection c = getConnection();

        try (Statement s = c.createStatement()) {
            ResultSet rs = s.executeQuery(sql);
            if (rs.next()) {
                total =  rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            returnConnection(c);
        }
        return total;
    }

    //添加好友
    void addFriend(String fromUser, String toUser) throws SQLException;

    //单向删除好友
    void deleteFriend(String fromUser, String toUser) throws SQLException;

    //双向删除好友
    void deleteBothFriend(String fromUser, String toUser) throws SQLException;

    //获取发送过的申请状态
    List<FriendRequest> getSendList(String fromUser) throws SQLException;

    //获取被申请列表
    List<FriendRequest> getRequestList(String toUser) throws SQLException;

    //同意申请
    void agreeRequest(String fromUser, String toUser) throws SQLException;

    //拒绝申请
    void disagreeRequest(String fromUser, String toUser) throws SQLException;

    //是否添加过好友
    boolean isAdd(String fromUser, String toUser) throws SQLException;

    //是否发送过请求
    boolean isSend(String fromUser, String toUser) throws SQLException;
}
