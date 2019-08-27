package club.wljyes.dao;

import club.wljyes.bean.FriendRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 所有返回的容器都不为null,可能是空的
 */
public class RelationshipDAOImp extends AbstractRelationshipDAOImp<String> {

    @Override
    public List<String> getFriendList(String fromUser) throws SQLException {
        String sql = "select * from relationship where fromUser = ? and isFriend = 1";
        List<String> friends = new ArrayList<>();
        Connection c = getConnection();
        try (PreparedStatement ps = c.prepareStatement(sql)) {
            ResultSet rs = query(ps, fromUser);
            while (rs.next()) {
                friends.add(rs.getString("toUser"));
            }
            rs.close();
        } finally {
            returnConnection(c);
        }
        return friends;
    }

    @Override
    public List<String> getFriendList(String fromUser, int start, int count) {
        List<String> toUsers = new ArrayList<>();

        String sql = "select * from relationship where fromUser = ? and isFriend = 1 order by toUser, id limit ?, ?";
        Connection c = getConnection();

        try (PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, fromUser);
            ps.setInt(2, start);
            ps.setInt(3, count);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                toUsers.add(rs.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            returnConnection(c);
        }
        return toUsers;
    }
}
