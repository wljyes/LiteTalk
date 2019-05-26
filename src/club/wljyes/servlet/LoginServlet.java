package club.wljyes.servlet;

import club.wljyes.bean.User;
import club.wljyes.chat.ChatRoom;
import club.wljyes.dao.UserDAOImp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        UserDAOImp udi = UserDAOImp.getUserDAOImp();
        if (udi.match(username, password)) {
            User user = udi.getByName(username);
            req.setAttribute("code", 200);
            req.setAttribute("username", username);
            req.setAttribute("nickname", user.getNickname());
            req.setAttribute("avatarUrl", user.getAvatarUrl());
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        } else {
            req.setAttribute("code", 500);
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }
}
