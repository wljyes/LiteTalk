package club.wljyes.dao;

import club.wljyes.bean.User;

import java.sql.SQLException;
import java.util.List;

public interface RelationshipDAO<T> {
    //获取好友列表
    List<T> getFriendList(String username) throws SQLException;

    //添加好友
    void addFriend(String user1Name, String user2Name) throws SQLException;

    //删除好友
    void deleteFriend(String user1Name, String user2Name) throws SQLException;

    //获取发送过的申请状态
    List<T> getSendList(String username) throws SQLException;

    //获取申请加自己的好友
    List<T> getRequestList(String username) throws SQLException;

    //同意申请
    void agreeRequest(String beRequestedName, String requestName) throws SQLException;

    //拒绝申请
    void disagreeRequest(String beRequestedName, String requestName) throws SQLException;
}
