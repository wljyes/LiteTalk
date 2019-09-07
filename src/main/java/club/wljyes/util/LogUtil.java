package club.wljyes.util;

import org.slf4j.LoggerFactory;

public class LogUtil {
    public static <T> void log(String msg, Class<T> tClass) {
        LoggerFactory.getLogger(tClass).error(msg);
    }

    public static void main(String[] args) {
        log("this is a log", LogUtil.class);
    }
}
