package club.wljyes.servlet;

import club.wljyes.bean.User;
import club.wljyes.dao.RelationshipDAO;
import club.wljyes.dao.AbstractRelationshipDAOImp;
import club.wljyes.dao.RelationshipDAOImp;
import club.wljyes.service.UserException;
import club.wljyes.service.UserService;
import club.wljyes.util.StringUserJsonParser;
import club.wljyes.util.UserJsonParser;
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        UserService userService = new UserService();
        JSONObject jsonObject = new JSONObject();
        try {
            List<String> friendList = userService.getFriendList(user.getUsername());
            UserJsonParser<String> parser = new StringUserJsonParser();
            String userJson = parser.toJson(friendList);
            JSONObject json = new JSONObject(userJson);
            JSONArray usernames = json.getJSONArray("users");
            jsonObject.put("code", 200);
            jsonObject.put("usernames", usernames);
            resp.getWriter().write(jsonObject.toString());
        } catch (UserException e) {
            e.getCause().printStackTrace();
            jsonObject.put("code", 500);
            jsonObject.put("error", e.getMessage());
            resp.getWriter().write(jsonObject.toString());
        }
    }
}
