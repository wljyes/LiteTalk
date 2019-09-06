package club.wljyes.dao;

import club.wljyes.bean.FriendRequest;
import club.wljyes.util.DBUtil;

import java.sql.*;
import java.util.List;

public interface RelationshipDAO<T> {
    //从连接池获取连接
    default Connection getConnection() {
        return DBUtil.getConnection();
    }
    //切记要归还连接，否则会使连接资源被耗尽，造成获取连接永远等待
    default void returnConnection(Connection c) {
        DBUtil.returnConnection(c);
    }

    //获取好友列表
    default List<T> getFriendList(String fromUser) throws SQLException {
        return getFriendList(fromUser, 0, Short.MAX_VALUE);
    }

    List<T> getFriendList(String fromUser, int start, int count);

    default int getRequestTotal(String toUser) {
        int total = 0;

        String sql = "select count(*) from relationship where toUser = ? and isFriend = ?";
        Connection c = getConnection();

        try (PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, toUser);
            ps.setInt(2, 0);
            ResultSet rs = ps.executeQuery();
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

    default int getFriendTotal(String fromUser) {
        int total = 0;

        String sql = "select count(*) from relationship where fromUser = ? and isFriend = ?";
        Connection c = getConnection();

        try (PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, fromUser);
            ps.setInt(2, 1);
            ResultSet rs = ps.executeQuery();
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
    List<FriendRequest> getRequestList(String toUser, int start, int count);

    //同意申请
    void agreeRequest(String fromUser, String toUser) throws SQLException;

    //拒绝申请
    void disagreeRequest(String fromUser, String toUser) throws SQLException;

    //是否添加过好友
    boolean isAdd(String fromUser, String toUser) throws SQLException;

    //是否发送过请求
    boolean isSend(String fromUser, String toUser) throws SQLException;

    boolean searchRequest(String fromUser, String toUser, int isFriend) throws SQLException;
}
