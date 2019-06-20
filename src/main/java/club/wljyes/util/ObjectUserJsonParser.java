package club.wljyes.util;

import club.wljyes.bean.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Collection;

public class ObjectUserJsonParser extends UserJsonParser<User> {
    @Override
    public String toJson(Collection<User> users) {
        JSONArray array = new JSONArray();
        for (User u : users) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("username", u.getUsername());
            jsonObject.put("nickname", u.getNickname());
            jsonObject.put("avatar", u.getAvatarUrl());
            array.put(jsonObject);
        }
        JSONObject json = new JSONObject();
        json.put("users", array);
        return json.toString();
    }

    @Override
    public User fromJson(String json) {
        return null;
    }
}
