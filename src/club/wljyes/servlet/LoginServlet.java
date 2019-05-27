package club.wljyes.servlet;

import club.wljyes.bean.User;
import club.wljyes.chat.ChatRoom;
import club.wljyes.dao.UserDAOImp;
import club.wljyes.service.UserException;
import club.wljyes.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;
import java.io.IOException;
import java.sql.SQLException;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        String username = req.getParameter("username");
        if (username == null || username.trim().isEmpty()) {
            req.setAttribute("usernameError", "用户名不能为空");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
            return;
        }
        String password = req.getParameter("password");
        if (password == null || password.trim().isEmpty()) {
            req.setAttribute("passwordError", "密码不能为空");
            req.setAttribute("username", username);
        }
        User user;
        try {
            user = new UserService().login(username, password);
        } catch (UserException ue) {
            req.setAttribute("error", ue.getMessage());
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
            return;
        }
        req.getSession().setAttribute("user", user);
        resp.sendRedirect("/LiteTalk/index.jsp");
     }
}
