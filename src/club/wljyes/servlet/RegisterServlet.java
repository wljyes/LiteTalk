package club.wljyes.servlet;

import club.wljyes.dao.UserDAOImp;
import club.wljyes.service.UserException;
import club.wljyes.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        Map<String, String> errors = new HashMap<>();

        String username = req.getParameter("username");
        if (username == null || username.trim().isEmpty()) {
            errors.put("username", "用户名不能为空");
        } else if (username.length() < 3 || username.length() > 16) {
            errors.put("username", "用户名长度应在3-16位");
            req.setAttribute("username", username);
        }
        String password = req.getParameter("password");
        if (password == null || password.trim().isEmpty()) {
            errors.put("password", "密码不能为空");
        } else if (password.length() < 3 || password.length() > 16) {
            errors.put("password", "密码长度应在3-16位");
            req.setAttribute("password", password);
        }
        if (errors.size() > 0) {
            req.setAttribute("errors", errors);
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
            return;
        }
        try {
            UserService service = new UserService();
            service.register(username, password);
        } catch (UserException ue) {
            errors.put("errors", ue.getMessage());
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
            return;
        }
        //如果注册成功则转发到登录servlet实现自动登录
        req.getRequestDispatcher("/login").forward(req, resp);
    }
}
