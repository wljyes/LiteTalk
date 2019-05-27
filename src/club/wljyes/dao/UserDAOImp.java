package club.wljyes.dao;

import club.wljyes.bean.User;
import club.wljyes.util.MyConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImp implements UserDAO {
    //单例
    private static class UserDAOHolder {
        private static final UserDAOImp userDAOImp = new UserDAOImp();
    }

    public static UserDAOImp getUserDAOImp() {
        return UserDAOHolder.userDAOImp;
    }

    private UserDAOImp(){}

    private static MyConnectionPool cp = MyConnectionPool.getConnectionPool(8);

    @Override
    public User getByName(String name) {
        Connection c = cp.getConnection();
        String sql = "select * from tb_users where name = ?";
        try (PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(name, rs.getString("nickname"), null, rs.getString("password"));
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean match(String username, String password) {
        Connection c = cp.getConnection();
        try (PreparedStatement ps = c.prepareStatement("select * from tb_users where name = ? and password = ?");) {
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cp.returnConnection(c);
        }
        return false;
    }

    @Override
    public void addUser(String username, String nickname, String password) {
        Connection c = cp.getConnection();
        String insert = "insert into tb_users values (null, ?, ?, ?, ?)";
        try (PreparedStatement ps = c.prepareStatement(insert)) {
            ps.setString(1, username);
            ps.setString(2, nickname);
            ps.setString(3, password);
            ps.setInt(4, 0);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addUser(User user) {
        addUser(user.getName(), user.getNickname(), user.getPassword());
    }
}
