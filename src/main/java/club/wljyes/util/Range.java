package club.wljyes.util;

public class Range {
    public static int[] range(int end) {
        return range(0, end);
    }

    public static int[] range(int start, int end) {
        int[] array = new int[end - start];
        for (int i = 0; i < array.length; i++) {
            array[i] = start;
            start++;
        }
        return array;
    }

    public static int[] range(int start, int end, int step) {
        int num = (end - start) / step + (end - start) % step;
        int[] array = new int[num];
        for (int i = 0; i < array.length; i++) {
            array[i] = start;
            start += step;
        }
        return array;
    }

    public static void main(String[] args) {
        for (int i : range(5)) {
            System.out.println(i);
        }
        for (int i : range(1, 5)) {
            System.out.println(i);
        }
        for (int i : range(1, 10, 3)) {
            System.out.println(i);
        }
    }
}
