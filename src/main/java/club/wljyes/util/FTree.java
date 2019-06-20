package club.wljyes.util;

import club.wljyes.bean.User;

import java.util.*;

//todo: 不强制要求实现Comparable, 如果既没实现Compare接口也每有Comparator则抛出异常
public class FTree<T> {
    private Node root;
    private Comparator<T> comparator;

    public FTree() {
        root = new Node();
    }

    public FTree(Comparator<T> comparator) {
        this();
        this.comparator = comparator;
    }

    public void add(T value) {
        root.add(value);
    }

    public void addAll(Collection<T> collection) {
        for (T item : collection) {
            add(item);
        }
    }

    public List<T> values() {
        return root.values(Order.MIDDLE);
    }

    public List<T> values(Order order) {
        return root.values(order);
    }

    enum Order {LEFT, MIDDLE, RIGHT}

    class Node {
        private Node leftNode;
        private Node rightNode;
        private T value;

        public List<T> values(Order order) {
            List<T> result = new ArrayList<>();
            switch (order) {
                case MIDDLE:
                    //左
                    if (leftNode != null) {
                        result.addAll(leftNode.values(Order.MIDDLE));
                    }
                    //中
                    result.add(value);
                    //右
                    if (rightNode != null) {
                        result.addAll(rightNode.values(Order.MIDDLE));
                    }
                    break;
                case LEFT:
                    //中
                    result.add(value);
                    if (leftNode != null) {
                        result.addAll(leftNode.values(Order.LEFT));
                    }
                    //右
                    if (rightNode != null) {
                        result.addAll(rightNode.values(Order.LEFT));
                    }
                    break;
                case RIGHT:
                    if (leftNode != null) {
                        result.addAll(leftNode.values(Order.LEFT));
                    }
                    //右
                    if (rightNode != null) {
                        result.addAll(rightNode.values(Order.LEFT));
                    }
                    //中
                    result.add(value);
                    break;
            }
            return result;
        }

        @SuppressWarnings("unchecked")
        private void add(T value) {
            if (this.value == null) {
                this.value = value;
            } else {
                int compared;
                //if neither has a comparator nor Comparable will throw a RuntimeException
                try {
                    compared = comparator != null ? comparator.compare(value, this.value) : ((Comparable<T>) value).compareTo(this.value);
                } catch (ClassCastException e) {
                    throw new RuntimeException("the objects can't be compared", e);
                }
                if (compared <= 0) {
                    if (leftNode == null)
                        leftNode = new Node();
                    leftNode.add(value);
                } else {
                    if (rightNode == null)
                        rightNode = new Node();
                    rightNode.add(value);
                }
            }
        }
    }

    public static void main(String[] args) {
//        FTree<Integer> tree = new FTree<>((v1, v2) -> v2 - v1);
////        Random random = new Random();
////        for (int i = 0; i < 10; i++) {
////            tree.add(random.nextInt(100));
////        }
//        tree.addAll(Arrays.asList(67,7,30,73,10,0,78,81,10,74));
        FTree<User> tree = new FTree<>();
        tree.addAll(Arrays.asList(new User(null, null, null), new User(null, null, null)));
        System.out.println(tree.values(Order.MIDDLE));
    }
}
