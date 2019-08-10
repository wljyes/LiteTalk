package club.wljyes.servlet;

import club.wljyes.bean.User;
import club.wljyes.service.UserService;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DisagreeAddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        String fromUser = req.getParameter("fromUser");
        String toUser = user.getUsername();
        JSONObject jo = new JSONObject();
        UserService service = new UserService();
        if (service.disagreeAdd(fromUser, toUser)) {
            jo.put("code", 200);
            jo.put("msg", "已拒绝");
        } else {
            jo.put("code", 500);
            jo.put("msg", "发生错误");
        }
        resp.getWriter().print(jo);
    }
}
