package chapter3_1;

import chapter1_3.Queue;
import edu.princeton.cs.algs4.StdOut;

public class RedBlackLiteBST<Key extends Comparable<Key>, Value> {
    private Node root;
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {
        private Key key;
        private Value value;
        private Node left, right;
        private boolean color;
        private int count;

        public Node(Key key, Value value, boolean color, int count) {
            this.key = key;
            this.value = value;
            this.color = color;
            this.count = count;
        }
    }

    private boolean isRed(Node x) {
        if (x == null) return false;
        return x.color == RED;
    }

    private Node rotateLeft(Node x) {
        assert (!isRed(x.left) && isRed(x.right));
        Node h = x.right;
        x.right = h.left;
        h.left = x;
        h.color = x.color;
        x.color = RED;
        h.count = x.count;
        x.count = size(x.left) + size(x.right) + 1;        
        return h;
    }

    private Node rotateRight(Node x) {
        assert (isRed(x.left) && isRed(x.left.left));
        Node h = x.left;
        x.left = h.right;
        h.right = x;
        h.color = x.color;
        x.color = RED;
        h.count = x.count;
        x.count = size(x.left) + size(x.right) + 1;
        return h;
    }

    private void flipColor(Node x) {
        assert (isRed(x.left) && isRed(x.right));
        x.color = RED;
        x.left.color = x.right.color = BLACK;
    }

    public int size() {
        return size(root);
    }
    private int size(Node x) {
        if (x == null) return 0;
        return x.count;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int height() {
        return height(root);
    }

    private int height(Node x) {
        if (x == null) return -1;
        return Math.max(height(x.left), height(x.right)) + 1;
    }

    public Key min() {
        if (root == null) return null;
        Node x = root;
        while (x.left != null) {
            x = x.left;
        }
        return x.key;
    }

    public Key max() {
        if (root == null) return null;
        Node x = root;
        while (x.right != null) {
            x = x.right;
        }
        return x.key;
    }

    public void put(Key key, Value value) {
        root = put(root, key, value);
        root.color = BLACK;
    }

    private Node put(Node x, Key key, Value value) {
        if (x == null) return new Node(key, value, RED, 1);
        
        int cmp = key.compareTo(x.key);
        if (cmp == 0) x.value = value;
        else if (cmp > 0) x.right = put(x.right, key, value);
        else if (cmp < 0) x.left = put(x.left, key, value);

        if (isRed(x.right) && !isRed(x.left)) x = rotateLeft(x);
        if (isRed(x.left) && isRed(x.left.left)) x = rotateRight(x);
        if (isRed(x.left) && isRed(x.right)) flipColor(x);
        x.count = size(x.left) + size(x.right) + 1;

        return x;
    }

    public Value get(Key key) {
        assert (check());
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

    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<Key>();
        InOrderEnqueue(queue, root);
        return queue;
    }

    private void InOrderEnqueue(Queue<Key> queue, Node x) {
        if (x == null) return;
        InOrderEnqueue(queue, x.left);
        queue.enqueue(x.key);
        InOrderEnqueue(queue, x.right);
    }

    private boolean check() {
        if (!isBST()) StdOut.println("Not in symetric order!");
        if (!is23()) StdOut.println("Not a 2-3 tree!");
        if (!isBalanced()) StdOut.println("Not Balanced!");
        return isBST() && is23() && isBalanced();
    }

    private boolean isBST() {
        return isBST(root, null, null);
    }

    private boolean isBST(Node x, Key min, Key max) {
        if (x == null) return true;
        if (min != null && min.compareTo(x.key) >= 0) return false;
        if (max != null && max.compareTo(x.key) <= 0) return false;
        return isBST(x.left, min, x.key) && isBST(x.right, x.key, max);
    }

    private boolean is23() {
        return is23(root);
    }

    private boolean is23(Node x) {
        if (x == null) return true;
        if (isRed(x.right)) return false;
        if (x != null && isRed(x) && isRed(x.left)) return false;
        return is23(x.left) && is23(x.right);
    }

    private boolean isBalanced() {
        Node x = root;
        int black = 0;
        while (x != null) {
            if (!isRed(x)) black++;
            x = x.left;
        }
        return isBalanced(root, black);
    }

    private boolean isBalanced(Node x, int black) {
        if (x == null)
            return black == 0;
        if (!isRed(x)) black--;
        return isBalanced(x.left, black) && isBalanced(x.right, black);
    }

    public static void main(String[] args) {
        String str = "E A S Y Q U T I O N";
        String[] keys = str.split(" ");
        RedBlackLiteBST<String, Integer> bst = new RedBlackLiteBST<>();
        for (int i = 0; i < keys.length; i++)
            bst.put(keys[i], i);

        StdOut.println("size: " + bst.size());
        StdOut.println("height: " + bst.height());
        StdOut.println("min: " + bst.min());
        StdOut.println("max: " + bst.max());
        StdOut.println();

        StdOut.println("TEST keys()");
        StdOut.println("----------------------------------");
        Iterable<String> allkeys = bst.keys();
        for (String key : allkeys)
            StdOut.println(key + " ");
        StdOut.println();
        StdOut.println("TEST get()");
        StdOut.println("----------------------------------");
        for (int i = 0; i < keys.length; i++)
            StdOut.println(keys[i] + ":" + bst.get(keys[i]));
    }
}
