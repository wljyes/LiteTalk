package club.wljyes.dao;

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
        ResultSet rs = query(sql);
        while (rs.next()) {
            friends.add(rs.getString("toUser"));
        }
        return friends;
    }

    @Override
    public Map<String, Integer> getSendMap(String fromUser) throws SQLException {
        String sql = "select * from relationship where fromUser = ?";
        Map<String, Integer> sendList = new HashMap<>();
        ResultSet rs = query(sql);
        while (rs.next())
            sendList.put(rs.getString("toUser"), rs.getInt("isFriend"));
        return sendList;
    }

    @Override
    public Map<String, Integer> getRequestMap(String toUser) throws SQLException {
        String sql = "select * from relationship where toUser = ?";
        Map<String, Integer> requestMap = new HashMap<>();
        ResultSet rs = query(sql);
        while (rs.next())
            requestMap.put(rs.getString("fromUser"), rs.getInt("isFriend"));
        return requestMap;
    }
}
