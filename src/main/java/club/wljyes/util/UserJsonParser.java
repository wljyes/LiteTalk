package club.wljyes.util;

import club.wljyes.bean.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Collection;


//todo 抽象类，使用多态
public abstract class UserJsonParser<T> implements JsonParser<T>{

    @Override
    public abstract String toJson(Collection<T> collection);

    @Override
    public abstract T fromJson(String json);
}
