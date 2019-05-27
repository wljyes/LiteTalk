package club.wljyes.servlet;

import club.wljyes.dao.UserDAOImp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if (null == username || null == password) {
            resp.sendRedirect("/LiteTalk/register.jsp");
        }
        else if (UserDAOImp.getUserDAOImp().getByName(username) == null) {
            UserDAOImp.getUserDAOImp().addUser(username, null, password);
            //注册成功在服务端转发实现自动登录
            req.getRequestDispatcher("/LiteTalk/login").forward(req, resp);
        }
        return;
    }
}
