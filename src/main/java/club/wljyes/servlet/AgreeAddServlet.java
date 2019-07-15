package club.wljyes.servlet;

import club.wljyes.bean.User;
import club.wljyes.service.UserException;
import club.wljyes.service.UserService;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AgreeAddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        String fromUser = req.getParameter("fromUser");
        String toUser = user.getUsername();
        JSONObject json = new JSONObject();
        try {
            UserService userService = new UserService();
            userService.agreeAdd(fromUser, toUser);
            json.put("code", 200);
            json.put("msg", "添加成功");
        } catch (UserException e) {
            json.put("code", 500);
            json.put("error", e.getMessage());
        }
        resp.getWriter().write(json.toJSONString());
    }
}
