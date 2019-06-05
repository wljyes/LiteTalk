package club.wljyes.service;

import club.wljyes.bean.User;
import club.wljyes.dao.UserDAOImp;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class UserService {

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
}
