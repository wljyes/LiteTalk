package club.wljyes.dao;

import club.wljyes.bean.User;

import java.sql.SQLException;

public interface UserDAO {
    User getByName(String name);

    boolean match(String username, String password);

    void addUser(String username, String nickname, String password);

    void addUser(User user);
}
