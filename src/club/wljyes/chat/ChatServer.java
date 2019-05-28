package club.wljyes.chat;

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
import java.util.Vector;

@ServerEndpoint(value="/websocket/{username}")
public class ChatServer {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static Vector<Session> room = new Vector<>();

    @OnOpen
    public void onOpen(@PathParam("username") String username, Session session) {
        room.add(session);
        System.out.println(session + "连接");
        ChatRoom.addUser(username);
    }

    @OnMessage
    public void onMessage(@PathParam("username") String username, String message, Session session) {
        JSONObject obj = new JSONObject(message);
        obj.put("time", sdf.format(new Date()));
        String sender = obj.getString("username");
        for (Session user : room) {
            obj.put("isSelf", username.equals(sender));
            user.getAsyncRemote().sendText(obj.toString());
        }
    }

    @OnClose
    public void onClose(@PathParam("username") String username, Session session) {
        room.remove(session);
        System.out.println(session + "断开");
        ChatRoom.removeUser(username);
    }
}
