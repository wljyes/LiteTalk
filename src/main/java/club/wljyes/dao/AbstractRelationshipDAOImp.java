package club.wljyes.dao;

import club.wljyes.bean.FriendRequest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
    public abstract List<FriendRequest> getSendList(String fromUser) throws SQLException;

    @Override
    public abstract List<FriendRequest> getRequestList(String toUser) throws SQLException;

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
        return searchRequest(fromUser, toUser, 1);
    }

    @Override
    public boolean isSend(String fromUser, String toUser) throws SQLException {
        return searchRequest(fromUser, toUser, 0);
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

    protected boolean searchRequest(String fromUser, String toUser, int isFriend) throws SQLException {
        Connection c = getConnection();
        String sql = "select * from relationship where fromUser = ? and toUser = ? and isFriend = ?";
        try (PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, fromUser);
            ps.setString(2, toUser);
            ps.setInt(3, isFriend);
            return ps.executeQuery().next();
        } finally {
            returnConnection(c);
        }
    }

    public ResultSet query(PreparedStatement ps, Object... parameters) throws SQLException {
            for (int i = 0; i < parameters.length; i++) {
                Object param = parameters[i];
                String type = param.getClass().getSimpleName();
                String className = param.getClass().getName();
                String methodName = "set" + type;
                try {
                    Method setParamMethod = ps.getClass().getMethod(methodName, int.class, Class.forName(className));
                    try {
                        setParamMethod.invoke(ps, i + 1, param);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                } catch (NoSuchMethodException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            return ps.executeQuery();
        }
    }
