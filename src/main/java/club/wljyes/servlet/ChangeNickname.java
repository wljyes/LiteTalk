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
        if ("".equals(nickname) || null == nickname) {
            resp.getWriter().write("昵称不能为空");
            return;
        }
        //filter确保session存在
        User user = (User) req.getSession().getAttribute("user");
        user.setNickname(nickname);
        try {
            UserService.changeNickname(user);
        } catch (UserException e) {
            resp.getWriter().write(e.getMessage());
            return;
        }
        resp.getWriter().write("昵称修改成功");
    }
}
