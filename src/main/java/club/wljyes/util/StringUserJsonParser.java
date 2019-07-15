package club.wljyes.util;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StringUserJsonParser extends UserJsonParser<String> {
    @Override
    public String toJson(Collection<String> usernames) {
        JSONArray names = new JSONArray();
        for (String username : usernames) {
            JSONObject user = new JSONObject();
            user.put("username", username);
            names.put(user);
        }
        JSONObject users  = new JSONObject();
        users.put("users", names);
        return users.toString();
    }

    @Override
    public String fromJson(String json) {
        return null;
    }

    public static void main(String[] args) {
        StringUserJsonParser parser = new StringUserJsonParser();
        List<String> users = new ArrayList<String>() {{add("aaa"); add("bbb");}};
        JSONObject jsonObject = new JSONObject(parser.toJson(users));
        System.out.println(jsonObject);
    }
}
