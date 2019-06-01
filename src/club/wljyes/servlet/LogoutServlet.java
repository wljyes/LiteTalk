package club.wljyes.servlet;

import club.wljyes.bean.User;
import club.wljyes.chat.ChatRoom;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        ChatRoom.removeUser(user.getUsername());
        req.getSession().invalidate();
        resp.sendRedirect("login.jsp");
    }
}
