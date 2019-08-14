package club.wljyes.chat;

import club.wljyes.bean.Message;
import club.wljyes.bean.User;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

@ServerEndpoint(value="/websocket/{username}")
public class ChatRepository {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static Map<String, Session> map = new HashMap<>();

    @OnOpen
    public void onOpen(@PathParam("username") String username, Session session) {
        map.put(username, session);
        System.out.println(username + "websocket连接");
    }

    @OnMessage
    public void onMessage(@PathParam("username") String fromUser, String message, Session session) {
        JSONObject obj = new JSONObject(message);
        obj.put("time", sdf.format(new Date()));
//        String sender = obj.getString("username");
        for (String username : map.keySet()) {
            obj.put("isSelf", username.equals(fromUser));
            map.get(username).getAsyncRemote().sendText(obj.toString());
        }
    }

    @OnClose
    public void onClose(@PathParam("username") String username, Session session) {
        map.remove(username);
        System.out.println(username + "websocket断开");
    }

    public static void sendMessage(Message message) {
        String toUser = message.toUser;
        assert toUser != null;
        Session session = null;
        for (String username : map.keySet()) {
            if (username.equals(toUser)) {
                session = map.get(username);
                break;
            }
        }
        if (session == null) {
            if ("server".equals(message.fromUser)) {
                return;
            }
            Message msg = new Message("server", message.fromUser, "用户不在线");
            sendMessage(msg);
        }
        assert session != null;
        JSONObject json = new JSONObject();
        json.put("fromUser", message.fromUser);
        json.put("toUser", message.toUser);
        json.put("content", message.content);
        session.getAsyncRemote().sendText(json.toString());
    }
}
