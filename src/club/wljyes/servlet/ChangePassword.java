package club.wljyes.servlet;

import club.wljyes.bean.User;
import club.wljyes.dao.UserDAOImp;
import club.wljyes.service.UserException;
import club.wljyes.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class ChangePassword extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        String newPassword = req.getParameter("password");
        if (newPassword == null || "".equals(newPassword)) {
            resp.getWriter().write("密码不能为空");
            return;
        }
        //TODO:检测旧密码
        User user = (User) req.getSession().getAttribute("user");
        user.setPassword(newPassword);
        try {
            UserService.changePassword(user);
        } catch (UserException e) {
            resp.getWriter().write(e.getMessage());
            return;
        }
        resp.getWriter().write("密码修改成功");
    }
}
