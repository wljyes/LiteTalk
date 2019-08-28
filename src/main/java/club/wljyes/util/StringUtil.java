package club.wljyes.util;

public class StringUtil {
    /**
     *
     * @param s 原字符串
     * @param begin substring的前端
     * @param end substring的后端
     * @return 返回夹在第一个begin与最后一个end中间的字串，如果不存在则返回 {@code null}
     */
    public static String subStringBetween(String s, String begin, String end) {
        int first = s.indexOf(begin);
        int last = s.lastIndexOf(end);

        if (first < 0 || last < 0 || first == last) {
            return null;
        }

        first += begin.length();
        return s.substring(first, last);
    }

    public static String subStringAfterLast(String s, String lastString) {
        int lastIndex = s.lastIndexOf(lastString);
        if (lastIndex < 0) {
            return null;
        }
        lastIndex = lastIndex + lastString.length();
        return new String(s.toCharArray(), lastIndex, s.length() - lastIndex);
    }

    public static String remove(String s, String removed) {
        return s.replaceFirst(removed, "");
    }

    public static void main(String[] args) {
        System.out.println(new String("abcd".toCharArray(), 1, 0));
        System.out.println(subStringBetween("abcd", "abc", "abc"));
        System.out.println(subStringBetween("abcd", "a", "b"));
        System.out.println(subStringAfterLast("abcd", "e"));
        String removing = "abcd";
        System.out.println(remove(removing, "e"));
        System.out.println(removing);
    }
}
