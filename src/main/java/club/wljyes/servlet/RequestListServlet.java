package club.wljyes.servlet;

import club.wljyes.bean.User;
import club.wljyes.service.UserException;
import club.wljyes.service.UserService;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class RequestListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = ((User) req.getSession().getAttribute("user")).getUsername();
        UserService service = new UserService();
        //todo Json优化
        JSONObject jsonObject = new JSONObject();
        try {
            Map<String, Integer> requests = service.getRequestMap(username);
            jsonObject.put("code", 200);
            JSONArray requestArray = new JSONArray();
            for (String key: requests.keySet()) {
                JSONObject request = new JSONObject();
                request.put("username", key);
                request.put("isFriend", requests.get(key));
                requestArray.put(request);
            }
            jsonObject.put("request", requestArray);
        } catch (UserException ue) {
            jsonObject.put("code", 500);
            jsonObject.put("error", "获取请求列表错误");
        }
        resp.getWriter().write(jsonObject.toString());
    }
}
