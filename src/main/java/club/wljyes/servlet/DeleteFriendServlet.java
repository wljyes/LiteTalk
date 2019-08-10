package club.wljyes.servlet;

import club.wljyes.bean.User;
import club.wljyes.service.UserService;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteFriendServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        String fromUser = user.getUsername();
        String toUser = req.getParameter("toUser");
        JSONObject jo = new JSONObject();
        UserService s = new UserService();
        if (s.deleteFriend(fromUser, toUser)) {
            jo.put("code", 200);
            jo.put("msg", "删除成功");
        } else {
            jo.put("code", 500);
            jo.put("msg", "出现错误");
        }
        resp.getWriter().print(jo);
    }
}
