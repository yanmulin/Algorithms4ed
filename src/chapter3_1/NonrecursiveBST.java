package chapter3_1;

import chapter1_3.Queue;
import chapter1_3.Stack;
import edu.princeton.cs.algs4.StdOut;

public class NonrecursiveBST<Key extends Comparable<Key>, Value> {
    private Node root;

    private class Node {
        private Key key;
        private Value value;
        private Node left, right;

        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
        }
    }

    public Value get(Key key) {
        Node x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp == 0) return x.value;
            else if (cmp > 0) x = x.right;
            else if (cmp < 0) x = x.left;
        }
        return null;
    }

    public void put(Key key, Value value) {
        Node z = new Node(key, value);
        if (root == null) {
            root = z;
            return;
        }
        Node x = root, parent = null;
        while (x != null) {
            parent = x;
            int cmp = key.compareTo(x.key);
            if (cmp == 0) {
                x.value = value;
                return;
            } else if (cmp > 0) x = x.right;
            else x = x.left;
        }
        int cmp = key.compareTo(parent.key);
        if (cmp > 0) parent.right = z;
        else parent.left = z;
    }

    public Iterable<Key> keys() {
        Stack<Node> nodes = new Stack<>();
        Queue<Key> keys = new Queue<>();
        Node x = root;
        while (x != null || !nodes.isEmpty()) {
            if (x != null) {
                nodes.push(x);
                x = x.left;
            } else {
                x = nodes.pop();
                keys.enqueue(x.key);
                x = x.right;
            }
        }
        return keys;
    }

    public static void main(String[] args) {
        NonrecursiveBST<String, Integer> st = new NonrecursiveBST<>();
        String[] a = {"e", "b", "a", "c", "h", "f", "i", "d", "g"};
        Integer[] b = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        for (int i = 0; i < a.length; i++) {
            st.put(a[i], b[i]);
        }
        StdOut.println("TEST keys()");
        Iterable<String> keys = st.keys();
        for (String key : keys) {
            StdOut.print(key + " ");
        }
        StdOut.println();
        StdOut.println("TEST get()");
        for (int i = 0; i < a.length; i++)
            StdOut.print("(" + a[i] + ", " + st.get(a[i]) + ") ");
        StdOut.println();
    }
}
