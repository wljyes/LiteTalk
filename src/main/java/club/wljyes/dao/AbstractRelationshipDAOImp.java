package club.wljyes.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public abstract class AbstractRelationshipDAOImp<T> implements RelationshipDAO<T> {
    @Override
    public abstract List<T> getFriendList(String fromUser) throws SQLException;

    @Override
    public void addFriend(String fromUser, String toUser) throws SQLException {
        Connection c = getConnection();
        String sql = "insert into relationship values(null, ?, ?, ?)";
        try (PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, fromUser);
            ps.setString(2, toUser);
            ps.setInt(3, 0);
            ps.execute();
        } finally {
            returnConnection(c);
        }
    }

    @Override
    public void deleteFriend(String fromUser, String toUser) throws SQLException {
        Connection c = getConnection();
        String sql = "delete from relationship where fromUser = ? and toUser = ? and isFriend = 1";
        try (PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, fromUser);
            ps.setString(2, toUser);
            ps.execute();
        } finally {
            returnConnection(c);
        }
    }

    @Override
    public void deleteBothFriend(String fromUser, String toUser) throws SQLException {
        deleteFriend(fromUser, toUser);
        deleteFriend(toUser, fromUser);
    }

    @Override
    public abstract Map<T, Integer> getSendMap(String fromUser) throws SQLException;

    @Override
    public abstract Map<T, Integer> getRequestMap(String toUser) throws SQLException;

    @Override
    public void agreeRequest(String fromUser, String toUser) throws SQLException {
        dealRequest(fromUser, toUser, 1);
        if (isSend(toUser, fromUser)) {
            dealRequest(toUser, fromUser, 1);
        } else {
            addFriend(toUser, fromUser);
            dealRequest(toUser, fromUser, 1);
        }
    }

    @Override
    public void disagreeRequest(String fromUser, String toUser) throws SQLException {
        dealRequest(fromUser, toUser, -1);
    }

    @Override
    public boolean isAdd(String fromUser, String toUser) throws SQLException {
        ResultSet rs = searchRequest(fromUser, toUser, 1);
        return rs.next();
    }

    @Override
    public boolean isSend(String fromUser, String toUser) throws SQLException {
        ResultSet rs = searchRequest(fromUser, toUser, 0);
        return rs.next();
    }

    protected void dealRequest(String fromUser, String toUser, int isFriend) throws SQLException {
        Connection c = getConnection();
        String sql = "update relationship set isFriend = ? where fromUser = ? and toUser = ?";
        try (PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, isFriend);
            ps.setString(2, fromUser);
            ps.setString(3, toUser);
            ps.execute();
        } finally {
            returnConnection(c);
        }
    }

    protected ResultSet searchRequest(String fromUser, String toUser, int isFriend) throws SQLException {
        Connection c = getConnection();
        String sql = "select * from relationship where fromUser = ? and toUser = ? and isFriend = ?";
        try (PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, fromUser);
            ps.setString(2, toUser);
            ps.setInt(3, isFriend);
            return ps.executeQuery();
        } finally {
            returnConnection(c);
        }
    }

    public ResultSet query(String sql) throws SQLException {
        Connection c = getConnection();
        try (PreparedStatement ps = c.prepareStatement(sql)) {
            return ps.executeQuery();
        } finally {
            returnConnection(c);
        }
    }
}
