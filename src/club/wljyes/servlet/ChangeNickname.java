package club.wljyes.servlet;

import club.wljyes.bean.User;
import club.wljyes.service.UserException;
import club.wljyes.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeNickname extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        String nickname = req.getParameter("nickname");
        String username = req.getParameter("username");

        UserService service = new UserService();
        try {
            service.changeNickname(username, nickname);
        } catch (UserException e) {
            resp.getWriter().write("修改出错");
            return;
        }
        resp.getWriter().write("修改成功");
    }
}
