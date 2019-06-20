package club.wljyes.chat;

import club.wljyes.bean.User;
import club.wljyes.dao.UserDAOImp;

import java.util.Hashtable;
import java.util.Map;

public class ChatRoom {
    private static Map<String, User> map = new Hashtable<>();

    public static void addUser(String name, User user) {
        map.put(name, user);
        System.out.println(name + "上线");
    }

    public static void addUser(String name) {
        User user = UserDAOImp.getUserDAOImp().getByName(name);
        if (user == null)
            user = new User("游客", name, null);
        addUser(name, user);
    }

    public static void removeUser(String name) {
        map.remove(name);
        System.out.println(name + "下线");
    }
}
