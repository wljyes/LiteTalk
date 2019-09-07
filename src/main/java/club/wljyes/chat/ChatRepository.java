package club.wljyes.chat;

import club.wljyes.bean.Message;
import club.wljyes.bean.User;
import club.wljyes.service.UserService;
import club.wljyes.util.JWSUtil;
import com.google.gson.Gson;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@ServerEndpoint(value="/websocket/{user_token}")
public class ChatRepository {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static Map<User, Session> map = new HashMap<>();

    private static Logger logger = LoggerFactory.getLogger(ChatRepository.class);

    @OnOpen
    public void onOpen(@PathParam("user_token") String userToken, Session session) {
        Jws<Claims> jws = JWSUtil.parseToken(userToken);
        //token无效
        if (jws == null) {
            try {
                session.close(new CloseReason(CloseReason.CloseCodes.CANNOT_ACCEPT, "无效的token"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            logger.warn("无效的token试图连接");
        } else {
            User user = UserService.fromMap((Map) jws.getBody().get("user"));
            map.put(user, session);
            logger.info("user=" + user + "新建连接");
        }
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
    public void onClose(@PathParam("user_token") String userToken, Session session) {
        Jws<Claims> jws = JWSUtil.parseToken(userToken);
        if (jws != null) {
            User user = UserService.fromMap((Map) jws.getBody().get("user"));
            map.remove(user);
            logger.info("user=" + user + "断开连接");
        }
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
            Gson gson = new Gson();
            session.getAsyncRemote().sendText(gson.toJson(message));
            logger.info("一条消息:" + message);
        }
    }
}
