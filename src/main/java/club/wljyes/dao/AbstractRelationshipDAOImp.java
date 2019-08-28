package club.wljyes.dao;

import club.wljyes.bean.FriendRequest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class AbstractRelationshipDAOImp<T> implements RelationshipDAO<T> {
    @Override
    public abstract List<T> getFriendList(String fromUser, int start, int count);

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
    public List<FriendRequest> getSendList(String fromUser) throws SQLException {
        String sql = "select * from relationship where fromUser = ?";
        List<FriendRequest> sendList = new ArrayList<>();
        Connection c = getConnection();
        try (PreparedStatement ps = c.prepareStatement(sql)) {
            ResultSet rs = query(ps, fromUser);
            while (rs.next()) {
                sendList.add(new FriendRequest(fromUser, rs.getString("toUser"), rs.getInt("isFriend")));
            }
            rs.close();
        } finally {
            returnConnection(c);
        }
        return sendList;
    }

    @Override
    public List<FriendRequest> getRequestList(String toUser, int start, int count) {
        List<FriendRequest> requests = new ArrayList<>();

        String sql = "select * from relationship where toUser = ? and isFriend = 0 order by fromUser, id limit ?, ?";
        Connection c = getConnection();

        try (PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, toUser);
            ps.setInt(2, start);
            ps.setInt(3, count);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String fromUser = rs.getString(2);
                requests.add(new FriendRequest(fromUser, toUser, 0));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            returnConnection(c);
        }
        return requests;
    }

    @Override
    public void agreeRequest(String fromUser, String toUser) throws SQLException {

        dealRequest(fromUser, toUser, 1, 0);

        if (isSend(toUser, fromUser)) {
            dealRequest(toUser, fromUser, 1, 0);
        } else {
            addFriend(toUser, fromUser);
            dealRequest(toUser, fromUser, 1, 0);
        }
    }

    @Override
    public void disagreeRequest(String fromUser, String toUser) throws SQLException {
        dealRequest(fromUser, toUser, -1, 0);
    }

    @Override
    public boolean isAdd(String fromUser, String toUser) throws SQLException {
        return searchRequest(fromUser, toUser, 1);
    }

    @Override
    public boolean isSend(String fromUser, String toUser) throws SQLException {
        return searchRequest(fromUser, toUser, 0);
    }

    private void dealRequest(String fromUser, String toUser, int newIsFriend, int oldIsFriend) throws SQLException {
        Connection c = getConnection();
        String sql = "update relationship set isFriend = ? where fromUser = ? and toUser = ? and isFriend = ?";
        try (PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, newIsFriend);
            ps.setString(2, fromUser);
            ps.setString(3, toUser);
            ps.setInt(4, oldIsFriend);
            ps.execute();
        } finally {
            returnConnection(c);
        }
    }

    public boolean searchRequest(String fromUser, String toUser, int isFriend) throws SQLException {
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
