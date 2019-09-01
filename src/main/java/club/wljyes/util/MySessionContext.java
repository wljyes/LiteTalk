package club.wljyes.util;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class MySessionContext {
    private static Map<String, HttpSession> sMap = new HashMap<>();

    public static void addSession(HttpSession session) {
        sMap.put(session.getId(), session);
    }

    public static void deleteSession(HttpSession session) {
        sMap.remove(session.getId());
    }

    public static HttpSession getSession(String sessionId) {
        return sMap.get(sessionId);
    }
}
