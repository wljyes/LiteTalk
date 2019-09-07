package club.wljyes.util;

import org.json.Property;

import java.io.*;
import java.util.Properties;

public class PropertyUtil {
    public static String getProperty(String path, String key) {
        String path1 = PropertyUtil.class.getClassLoader().getResource(path).getPath();
        try (FileInputStream in = new FileInputStream(path1)) {
            Properties prop = new Properties();
            prop.load(in);
            return prop.getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
