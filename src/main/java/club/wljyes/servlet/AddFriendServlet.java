package club.wljyes.servlet;

import club.wljyes.bean.User;
import club.wljyes.service.UserException;
import club.wljyes.service.UserService;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddFriendServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        String toUser = req.getParameter("toUser");
        String fromUser = user.getUsername();
        JSONObject json = new JSONObject();
        UserService userService = new UserService();
        try {
            userService.addFriend(fromUser, toUser);
            json.put("code", 200);
            json.put("msg", "添加消息已发送");
        } catch (UserException e) {
            json.put("code", 500);
            json.put("error", e.getMessage());
        }
        resp.getWriter().write(json.toJSONString());
    }
}
