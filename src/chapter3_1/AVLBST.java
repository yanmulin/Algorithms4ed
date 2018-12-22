package chapter3_1;

import chapter1_3.Queue;
import edu.princeton.cs.algs4.StdOut;

public class AVLBST<Key extends Comparable<Key>, Value> {
    private Node root;

    private class Node {
        private Key key;
        private Value value;
        private Node left, right;
        private int height;
        private int size;

        public Node(Key key, Value value, int height, int size) {
            this.key = key;
            this.value = value;
            this.height = height;
            this.size = size;
        }
    }

    private Node rotateLeft(Node x) {
        assert (x != null && x.right != null);
        Node h = x.right;
        x.right = h.left;
        h.left = x;
        h.size = x.size;
        x.size = size(x.left) + size(x.right) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        h.height = Math.max(height(h.left), height(h.right)) + 1;
        return h;
    }

    private Node rotateRight(Node x) {
        assert (x != null && x.left != null);
        Node h = x.left;
        x.left = h.right;
        h.right = x;
        h.size = x.size;
        x.size = size(x.left) + size(x.right) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        h.height = Math.max(height(h.left), height(h.right)) + 1;
        return h;
    }

    private int getBalance(Node x) {
        if (x == null) return 0;
        else return height(x.left) - height(x.right);
    }

    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    private Node put(Node x, Key key, Value value) {
        if (x == null) return new Node(key, value, 0, 1);
        int cmp = key.compareTo(x.key);
        if (cmp == 0) x.value = value;
        else if (cmp > 0) x.right = put(x.right, key, value);
        else x.left = put(x.left, key, value);

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        x.size = size(x.left) + size(x.right) + 1;


        int balance = getBalance(x);
        if (balance > 1 && key.compareTo(x.left.key) < 0) {
            x = rotateRight(x);
        } else if (balance > 1 && key.compareTo(x.left.key) > 0) {
            x.left = rotateLeft(x.left);
            x = rotateRight(x);
        } else if (balance < -1 && key.compareTo(x.right.key) > 0) {
            x = rotateLeft(x);
        } else if (balance < -1 && key.compareTo(x.right.key) < 0) {
            x.right = rotateRight(x.right);
            x = rotateLeft(x);
        }

        return x;
    }

    public Value get(Key key) {
        Node x = get(root, key);
        if (x == null) return null;
        else return x.value;
    }

    private Node get(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        else if (cmp > 0) return get(x.right, key);
        else return get(x.left, key);
    }

    public Key min() {
        Node x = min(root);
        if (x == null) return null;
        else return x.key;
    }

    private Node min(Node x) {
        if (x.left == null) return x;
        else return min(x.left);
    }

    public Key max() {
        Node x = max(root);
        if (x == null) return null;
        else return x.key;
    }

    private Node max(Node x) {
        if (x.right == null) return x;
        else return max(x.right);
    }

    public void deleteMin() {
        if (root == null) return;
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        else x.left = deleteMin(x.left);

        x.size = size(x.left) + size(x.right) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        int balance = getBalance(x);

        if (balance < -1 && getBalance(x.right) <= 0) {
            x = rotateLeft(x);
        } else if (balance < -1 && getBalance(x.right) > 0) {
            x.right = rotateRight(x.right);
            x = rotateLeft(x);
        }
        return x;
    }

    public void deleteMax() {
        if (root == null) return;
        root = deleteMax(root);
    }

    private Node deleteMax(Node x) {
        if (x.right == null) return x.left;
        else x.right = deleteMax(x.right);

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        x.size = size(x.left) + size(x.right) + 1;

        int balance = getBalance(x);
        if (balance > 1 && getBalance(x.left) >= 0) {
            x = rotateRight(x);
        } else if (balance > 1 && getBalance(x.right) < 0) {
            x.left = rotateLeft(x.left);
            x = rotateRight(x);
        }
        return x;
    }

    public void delete(Key key) {
        root = delete(root, key);
    }

    public Node delete(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp > 0) x.right = delete(x.right, key);
        else if (cmp < 0) x.left = delete(x.left, key);
        else {
            if (x.left == null) return x.right;
            if (x.right == null) return x.left;
            Node p = x;
            x = min(x.right);
            x.right = deleteMin(p.right);
            x.left = p.left;
        }

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        x.size = size(x.left) + size(x.right) + 1;

        int balance = getBalance(x);

        if (balance > 1 && getBalance(x.left) > 0) {
            x = rotateRight(x);
        } else if (balance > 1 && getBalance(x.left) < 0) {
            x.left = rotateLeft(x.left);
            x = rotateRight(x);
        } else if (balance < -1 && getBalance(x.right) > 0) {
            x.right = rotateRight(x.right);
            x = rotateLeft(x);
        } else if (balance < -1 && getBalance(x.right) < 0) {
            x = rotateLeft(x);
        }
        return x;
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) return 0;
        else return x.size;
    }

    public int height() {
        return height(root);
    }

    private int height(Node x) {
        if (x == null) return -1;
        else return x.height;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<>();
        InOrderEnqueue(queue, root);
        return queue;
    }

    private void InOrderEnqueue(Queue<Key> queue, Node x) {
        if (x == null) return;
        InOrderEnqueue(queue, x.left);
        queue.enqueue(x.key);
        InOrderEnqueue(queue, x.right);
    }

    public static void main(String[] args) {
        String str = "E A S Y Q U S T I O N";
        String[] keys = str.split(" ");
        AVLBST<String, Integer> st = new AVLBST<>();
        for (int i = 0; i < keys.length; i++) 
            st.put(keys[i], i);

        StdOut.println("TEST keys()");
        StdOut.println("--------------------------------");
        Iterable<String> allkeys = st.keys();
        for (String key : allkeys)
            StdOut.print(key + " ");
        StdOut.println();
        StdOut.println();

        StdOut.println("TEST get()");
        StdOut.println("--------------------------------");
        for (int i = 0; i < keys.length; i++)
            StdOut.print(keys[i] + ": " + st.get(keys[i]) + ", ");
        StdOut.println();
        StdOut.println();

        StdOut.println("TEST deleteMin()");
        StdOut.println("--------------------------------");
        for (int i = 0; i < keys.length / 2; i++) {
            StdOut.println("delete " + st.min());
            st.deleteMin();
        }
        StdOut.println();

        StdOut.println("After deleteMin()");
        StdOut.println("--------------------------------");
        allkeys = st.keys();
        for (String key : allkeys)
            StdOut.print(key + " ");
        StdOut.println();
        StdOut.println();

        StdOut.println("TEST deleteMax()");
        StdOut.println("--------------------------------");
        for (int i = 0; i < keys.length / 2; i++) {
            StdOut.println("delete " + st.max());
            st.deleteMax();
        }
        StdOut.println();

        StdOut.println("After deleteMax()");
        StdOut.println("--------------------------------");
        allkeys = st.keys();
        for (String key : allkeys)
            StdOut.print(key + " ");
        StdOut.println();
        StdOut.println();

        StdOut.println("Add keys");
        StdOut.println("--------------------------------");
        for (int i = 0; i < keys.length; i++)
            st.put(keys[i], i);
        allkeys = st.keys();
        for (String key : allkeys)
            StdOut.print(key + " ");
        StdOut.println();
        StdOut.println();

        StdOut.println("TEST delete()");
        StdOut.println("--------------------------------");
        for (int i = 0; i < keys.length; i++) {
            StdOut.println("delete " + keys[i]);
            st.delete(keys[i]);
        }
        StdOut.println();

        StdOut.println("After delete()");
        StdOut.println("--------------------------------");
        allkeys = st.keys();
        for (String key : allkeys)
            StdOut.print(key + " ");
        StdOut.println();
        StdOut.println();
    }
}
