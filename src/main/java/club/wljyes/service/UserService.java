package club.wljyes.service;

import club.wljyes.bean.User;
import club.wljyes.dao.RelationshipDAO;
import club.wljyes.dao.RelationshipDAOImp;
import club.wljyes.dao.UserDAO;
import club.wljyes.dao.UserDAOImp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService {

    private static RelationshipDAO<String> relationshipDAO;

    public UserService() {
        relationshipDAO = new RelationshipDAOImp();
    }

    public static User login(String username, String password) throws UserException {
        User user = UserDAOImp.getUserDAOImp().getByName(username);
        if (user == null) {
            throw new UserException("用户不存在");
        }
        if (!user.getPassword().equals(password)) {
            throw new UserException("密码错误");
        }
        return user;
    }

    public static void register(String username, String password) throws UserException {
        User user = UserDAOImp.getUserDAOImp().getByName(username);
        if (user != null) {
            throw new UserException("用户名已被使用");
        }
        user = new User(username, username, null, password);
        UserDAOImp.getUserDAOImp().addUser(user);
    }

    public static void changeNickname(User user) throws UserException {
        try {
            updateUser(user);
        } catch (SQLException e) {
            throw new UserException("昵称修改出错");
        }
    }

    public static void changePassword(User user) throws UserException {
        try {
            updateUser(user);
        } catch (SQLException e) {
            throw new UserException("修改密码出错");
        }
    }

    private static void updateUser(User user) throws SQLException {
        UserDAOImp.getUserDAOImp().updateUser(user);
    }

    public List<String> getFriendList(String username) throws UserException {
        try {
            return relationshipDAO.getFriendList(username);
        } catch (SQLException e) {
            throw (UserException) new UserException("获取好友列表失败").initCause(e);
        }
    }

    public void addFriend(String fromUser, String toUser) throws UserException {
        try {
            relationshipDAO.addFriend(fromUser, toUser);
        } catch (SQLException e) {
            throw (UserException) new UserException("添加好友失败").initCause(e);
        }
    }

    //todo 给两方发送消息
    public void agreeAdd(String fromUser, String toUser) throws UserException {
        try {
            relationshipDAO.agreeRequest(fromUser, toUser);
        } catch (SQLException e) {
            throw (UserException) new UserException("添加失败").initCause(e);
        }
    }

    public Map<String, Integer> getRequestMap(String toUser) throws UserException {
        Map<String, Integer> requests;
        try {
            requests = relationshipDAO.getRequestMap(toUser);
        } catch (SQLException se) {
            throw (UserException) new UserException("获取失败").initCause(se);
        }
        return requests;
    }

    public Map<String, Integer> getSendMap(String fromUser) throws UserException {
        Map<String, Integer> sendMap;
        try {
            sendMap = relationshipDAO.getSendMap(fromUser);
        } catch (SQLException se) {
            throw (UserException) new UserException("获取失败").initCause(se);
        }
        return sendMap;
    }
}
