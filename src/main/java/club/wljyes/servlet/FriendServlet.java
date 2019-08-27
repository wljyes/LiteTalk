package club.wljyes.servlet;

import club.wljyes.bean.User;
import club.wljyes.util.Page;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class FriendServlet extends ForeBaseServlet {
    @Override
    public String add(HttpServletRequest req, HttpServletResponse resp, Page page) {
        User user = (User) req.getSession().getAttribute("user");
        String toUser = req.getParameter("toUser");
        String fromUser = user.getUsername();
        JSONObject json = new JSONObject();
        try {
            relationshipDAO.addFriend(fromUser, toUser);
            json.put("code", 200);
            json.put("msg", "添加消息已发送");
        } catch (SQLException e) {
            json.put("code", 500);
            json.put("error", e.getMessage());
        }
        return json.toString();
    }

    @Override
    public String delete(HttpServletRequest req, HttpServletResponse resp, Page page) {
        User user = (User) req.getSession().getAttribute("user");
        String fromUser = user.getUsername();
        String toUser = req.getParameter("toUser");
        JSONObject jo = new JSONObject();
        try {
            relationshipDAO.deleteFriend(fromUser, toUser);
            jo.put("code", 200);
            jo.put("msg", "删除成功");
        } catch (SQLException e) {
            jo.put("code", 500);
            jo.put("msg", "出现错误");
        }
        return jo.toString();
    }

    @Override
    public String update(HttpServletRequest req, HttpServletResponse resp, Page page) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String list(HttpServletRequest req, HttpServletResponse resp, Page page) {
        page.setTotal(relationshipDAO.getTotal());
        User user = (User) req.getAttribute("user");
        List<String> friends = relationshipDAO.getFriendList(user.getUsername(), page.getStart(), page.getCount());
        req.setAttribute("friends", friends);
        return "%listFriend.jsp";
    }
}
