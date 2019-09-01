package club.wljyes.chat;

import club.wljyes.bean.Message;
import club.wljyes.bean.User;
import club.wljyes.util.MySessionContext;
import org.json.JSONObject;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ServerEndpoint(value="/websocket/{sessionId}")
public class ChatRepository {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static Map<User, Session> map = new HashMap<>();

    @OnOpen
    public void onOpen(@PathParam("sessionId") String sessionId, Session session) {
        HttpSession hs = MySessionContext.getSession(sessionId);
        User user = (User) hs.getAttribute("user");

        if (user == null) {
            try {
                session.close(new CloseReason(CloseReason.CloseCodes.NORMAL_CLOSURE, "用户不存在"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }

        map.put(user, session);
        System.out.println(user.getUsername() + "websocket连接");
    }

    @OnMessage
    public void onMessage(@PathParam("sessionId") String sessionId, String message, Session session) {
//        JSONObject obj = new JSONObject(message);
//        obj.put("time", sdf.format(new Date()));
////        String sender = obj.getString("username");
//        for (String username : map.keySet()) {
//            obj.put("isSelf", username.equals(fromUser));
//            map.get(username).getAsyncRemote().sendText(obj.toString());
//        }
    }

    @OnClose
    public void onClose(@PathParam("sessionId") String sessionId, Session session) {
        HttpSession hs = MySessionContext.getSession(sessionId);
        User user = (User) hs.getAttribute("user");
        map.remove(user);
        System.out.println(user.getUsername() + "websocket断开");
    }

    public static void sendMessage(Message message) {
        String toUser = message.toUser;
        assert toUser != null;
        Session session = null;
        for (User user : map.keySet()) {
            if (toUser.equals(user.getUsername())) {
                session = map.get(user);
                break;
            }
        }
        if (session == null) {
            if ("server".equals(message.fromUser)) {
                return;
            }
            Message msg = new Message("server", message.fromUser, "用户不在线");
            sendMessage(msg);
        } else {
            JSONObject json = new JSONObject();
            json.put("fromUser", message.fromUser);
            json.put("toUser", message.toUser);
            json.put("content", message.content);
            json.put("date", sdf.format(new Date()));
            session.getAsyncRemote().sendText(json.toString());
        }
    }
}
