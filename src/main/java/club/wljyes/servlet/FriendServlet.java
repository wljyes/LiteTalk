package club.wljyes.servlet;

import club.wljyes.bean.User;
import club.wljyes.dao.AbstractRelationshipDAOImp;
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
            if (relationshipDAO.searchRequest(fromUser, toUser, 0))
                return "已经向" + toUser + "发送过申请, 不能再次发送。";
            else if (relationshipDAO.searchRequest(fromUser, toUser, 1))
                return "您与" + toUser + "已经是好友。";
            relationshipDAO.addFriend(fromUser, toUser);
        } catch (SQLException e) {
            e.printStackTrace();
            return "发送失败。";
        }
        return "已向" + toUser + "发送申请。";
    }

    @Override
    public String delete(HttpServletRequest req, HttpServletResponse resp, Page page) {
        User user = (User) req.getSession().getAttribute("user");
        String fromUser = user.getUsername();
        String toUser = req.getParameter("toUser");
        try {
            relationshipDAO.deleteBothFriend(fromUser, toUser);
        } catch (SQLException e) {
           e.printStackTrace();
        }
        return "@fore_friend_list";
    }

    @Override
    public String update(HttpServletRequest req, HttpServletResponse resp, Page page) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String list(HttpServletRequest req, HttpServletResponse resp, Page page) {
        User user = (User) req.getSession().getAttribute("user");
        int total = relationshipDAO.getFriendTotal(user.getUsername());
        page.setTotal(total);
        List<String> friends = relationshipDAO.getFriendList(user.getUsername(), page.getStart(), page.getCount());
        req.setAttribute("friends", friends);
        req.setAttribute("page", page);
        return "%listFriend.jsp";
    }
}
