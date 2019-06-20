package club.wljyes.util;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Collection;

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
}
