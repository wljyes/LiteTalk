package club.wljyes.util;

import java.util.Collection;
import java.util.Map;

public interface JsonParser<T> {
    public String toJson(Collection<T> collection);

    public T fromJson(String json);
}
