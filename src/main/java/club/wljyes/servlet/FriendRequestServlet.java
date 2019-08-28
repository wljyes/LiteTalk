package club.wljyes.servlet;

import club.wljyes.bean.FriendRequest;
import club.wljyes.bean.User;
import club.wljyes.util.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class FriendRequestServlet extends ForeBaseServlet {
    @Override
    public String add(HttpServletRequest req, HttpServletResponse resp, Page page) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String delete(HttpServletRequest req, HttpServletResponse resp, Page page) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String update(HttpServletRequest req, HttpServletResponse resp, Page page) {
        boolean isAgree = Boolean.parseBoolean(req.getParameter("isAgree"));
        String fromUser = req.getParameter("fromUser");

        User user = (User) req.getSession().getAttribute("user");
        String toUser = user.getUsername();

        try {
            if (isAgree)
                relationshipDAO.agreeRequest(fromUser, toUser);
            else
                relationshipDAO.disagreeRequest(fromUser, toUser);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "@fore_friendRequest_list";
    }

    @Override
    public String list(HttpServletRequest req, HttpServletResponse resp, Page page) {
        User user = (User) req.getSession().getAttribute("user");
        page.setTotal(relationshipDAO.getRequestTotal(user.getUsername()));
        String toUser = user.getUsername();

        List<FriendRequest> requests = relationshipDAO.getRequestList(toUser, page.getStart(), page.getCount());
        req.setAttribute("requests", requests);
        req.setAttribute("page", page);

        return "%listRequest.jsp";
    }
}
