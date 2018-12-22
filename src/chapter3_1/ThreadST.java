package chapter3_1;

import chapter1_3.Queue;
import edu.princeton.cs.algs4.StdOut;

public class ThreadST<Key extends Comparable<Key>, Value> {
    private Node root;

    private class Node {
        private Key key;
        private Value value;
        private Node left, right;
        private Node pred, succ;
        private int count;

        public Node(Key key, Value value, int count, Node pred, Node succ) {
            this.key = key;
            this.value = value;
            this.count = count;
            this.pred = pred;
            this.succ = succ;
        }
    }

    public void put(Key key, Value value) {
        root = put(root, key, value, null, null);
    }

    private Node put(Node x, Key key, Value value, Node pred, Node succ) {
        if (x == null) {
            Node n = new Node(key, value, 1, pred, succ);
            if (pred != null) pred.succ = n;
            if (succ != null) succ.pred = n;
            return n;
        }
        int cmp = key.compareTo(x.key);
        if (cmp == 0) x.value = value;
        else if (cmp > 0) {
            x.right = put(x.right, key, value, x, succ);
        } else {
            x.left = put(x.left, key, value, pred, x);
        }
        x.count = size(x.left) + size(x.right) + 1;
        return x;
    }

    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x.value;
        else if (cmp > 0) return get(x.right, key);
        else return get(x.left, key);
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) return 0;
        else return x.count;
    }

    public void delete(Key key) {
        root = delete(root, key, null, null);
    }

    private Node delete(Node x, Key key, Node pred, Node succ) {
        int cmp = key.compareTo(x.key);
        if (cmp == 0) {
            if (x.pred != null) x.pred.succ = x.succ;
            if (x.succ != null) x.succ.pred = x.pred;
            if (x.left == null) return x.right;
            if (x.right == null) return x.left;
            Node p = x;
            x = min(x.right);
            deleteMin(p.right);
            x.left = p.left;
            x.right = p.right;
        } else if (cmp > 0) {
            x.right = delete(x.right, key, x, succ);
        } else {
            x.left = delete(x.left, key, pred, x);
        }
        x.count = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void deleteMin() {
        deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x.left == null) {
            x.succ.pred = null;
            return x.right;
        } else {
            x.left = deleteMin(x.left);
            x.count = size(x.left) + size(x.right) + 1;
            return x;
        }
    }

    public void deleteMax() {
        deleteMax(root);
    }

    public Node deleteMax(Node x) {
        if (x.right == null) {
            x.pred.succ = null;
            return x.left;
        } else {
            x.right = deleteMax(x.right);
            x.count = size(x.right) + size(x.left) + 1;
            return x;
        }
    }

    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<>();
        inOrderTraverse(root, queue);
        return queue;
    }

    private void inOrderTraverse(Node x, Queue<Key> q) {
        if (x == null) return;
        inOrderTraverse(x.left, q);
        q.enqueue(x.key);
        inOrderTraverse(x.right, q);
    }

    public Key min() {
        Node x = min(root);
        if (x == null) return null;
        else return x.key;
    }

    private Node min(Node x) {
        if (x == null) return null;
        if (x.left == null) return x;
        else return min(x.left);
    }

    public Key max() {
        Node x = max(root);
        if (x == null) return null;
        else return x.key;
    }

    private Node max(Node x) {
        if (x == null) return null;
        if (x.right == null) return x;
        else return max(x.right);
    }

    private void testSucc() {
        Node x = min(root);
        while (x != null) {
            StdOut.print(x.key + " ");
            x = x.succ;
        }
        StdOut.println();
    }

    private void testPred() {
        Node x = max(root);
        while (x != null) {
            StdOut.print(x.key + " ");
            x = x.pred;
        }
        StdOut.println();
    }

    public static void main(String[] args) {
        String test = "S E A R C H E X A M P L E";
        String[] keys = test.split(" ");
        int n = keys.length;
        ThreadST<String, Integer> st = new ThreadST<String, Integer>();
        for (int i = 0; i < n; i++)
            st.put(keys[i], i);

        StdOut.println("size = " + st.size());
        StdOut.println();

        StdOut.println("Testing pred");
        StdOut.println("---------------------------------");
        st.testPred();
        StdOut.println();
        StdOut.println("Testing succ");
        StdOut.println("---------------------------------");
        st.testSucc();
        StdOut.println();

        // print keys in order using allKeys()
        StdOut.println("Testing keys()");
        StdOut.println("--------------------------------");
        for (String s : st.keys())
            StdOut.println(s + " " + st.get(s));
        StdOut.println();

        // delete the smallest keys
        for (int i = 0; i < st.size() / 2; i++) {
            st.deleteMin();
        }
        StdOut.println("After deleting the smallest " + st.size() / 2 + " keys");
        StdOut.println("--------------------------------");
        for (String s : st.keys())
            StdOut.println(s + " " + st.get(s));
        StdOut.println();

        StdOut.println("Testing pred");
        StdOut.println("---------------------------------");
        st.testPred();
        StdOut.println();
        StdOut.println("Testing succ");
        StdOut.println("---------------------------------");
        st.testSucc();

        // delete all the remaining keys
        while (!st.isEmpty()) {
            st.delete(st.max());
        }
        StdOut.println("After deleting the remaining keys");
        StdOut.println("--------------------------------");
        for (String s : st.keys())
            StdOut.println(s + " " + st.get(s));
        StdOut.println();

        StdOut.println("Testing pred");
        StdOut.println("---------------------------------");
        st.testPred();
        StdOut.println();
        StdOut.println("Testing succ");
        StdOut.println("---------------------------------");
        st.testSucc();
        StdOut.println();

        StdOut.println("After adding back the keys");
        StdOut.println("--------------------------------");
        for (int i = 0; i < n; i++)
            st.put(keys[i], i);
        for (String s : st.keys())
            StdOut.println(s + " " + st.get(s));
        StdOut.println();

        StdOut.println("Testing pred");
        StdOut.println("---------------------------------");
        st.testPred();
        StdOut.println();
        StdOut.println("Testing succ");
        StdOut.println("---------------------------------");
        st.testSucc();
        StdOut.println();

    }
}
