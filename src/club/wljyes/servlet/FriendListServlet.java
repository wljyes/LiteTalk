package club.wljyes.servlet;

import club.wljyes.bean.User;
import club.wljyes.dao.RelationshipDAO;
import club.wljyes.dao.AbstractRelationshipDAOImp;
import club.wljyes.dao.RelationshipDAOImp;
import club.wljyes.service.UserException;
import club.wljyes.service.UserService;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FriendListServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        UserService userService = new UserService();
        try {
            List<String> friendList = userService.getFriendList(user.getUsername());
            JSONObject jsonObject = new JSONObject(friendList);
        } catch (UserException e) {
            e.getCause().printStackTrace();
            resp.getWriter().write(e.getMessage());
        }
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<String>() {{add("aaa"); add("bbb");}};
        List<User> users = new ArrayList<>();
        users.add(new User("wlj", "wljyes", "wljyes.com"));
        users.add(new User("pika", "ppp", null));
        JSONArray array = new JSONArray();
        for (User u : users) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("username", u.getUsername());
            jsonObject.put("nickname", u.getNickname());
            jsonObject.put("avatar", u.getAvatarUrl());
            array.put(jsonObject);
        }
        JSONObject json = new JSONObject();
        json.put("users", array);
        System.out.println(json);
    }
}
