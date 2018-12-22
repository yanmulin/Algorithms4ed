package chapter3_1;

import chapter1_3.Queue;
import edu.princeton.cs.algs4.StdOut;

public class RedBlackBST<Key extends Comparable<Key>, Value> {
    private Node root;
    private int n;
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
        return x.color;
    }

    private Node moveRedLeft(Node x) {
        flipColors(x);
        if (isRed(x.right.left)) {
            x.right = rotateRight(x.right);
            x = rotateLeft(x);
            flipColors(x);
        }
        return x;
    }

    private Node moveRedRight(Node x) {
        flipColors(x);
        if (isRed(x.left.left)) {
            x = rotateRight(x);
            flipColors(x);
        }
        return x;
    }

    private Node rotateLeft(Node x) {
        assert (x != null && x.right != null);
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
        assert (x != null && x.left != null);
        Node h = x.left;
        x.left = h.right;
        h.right = x;
        h.color = x.color;
        x.color = RED;
        h.count = x.count;
        x.count = size(x.left) + size(x.right) + 1;
        return h;
    }

    private void flipColors(Node x) {
        assert (x != null && x.left != null && x.right != null);
        assert ((isRed(x) && !isRed(x.left) && !isRed(x.right)) ||
                (!isRed(x) && isRed(x.left) && isRed(x.right)));
        x.color = !x.color;
        x.left.color = !x.left.color;
        x.right.color = !x.right.color;
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) return 0;
        else return x.count;
    }

    public int size(Key lo, Key hi) {
        return size(root, lo, hi);
    }

    private int size(Node x, Key lo, Key hi) {
        if (x == null) return 0;
        int cmp1 = lo.compareTo(x.key);
        int cmp2 = hi.compareTo(x.key);
        if (cmp1 > 0) return size(x.right, lo, hi);
        else if (cmp2 < 0) return size(x.left, lo, hi);
        else return size(x.left, lo, hi) + size(x.right, lo, hi) + 1;
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

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public Key min() {
        if (root == null) return null;
        Node x = min(root);
        return x.key;
    }

    private Node min(Node x) {
        assert (x != null);
        if (x.left == null) return x;
        else return min(x.left);
    }

    public Key max() {
        if (root == null) return null;
        Node x = max(root);
        return x.key;
    }

    private Node max(Node x) {
        assert (x != null);
        if (x.right == null) return x;
        else return max(x.right);
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
        return balance(x);
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

    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> queue = new Queue<>();
        InOrderEnqueue(queue, root, lo, hi);
        return queue;
    }

    private void InOrderEnqueue(Queue<Key> queue, Node x, Key lo, Key hi) {
        if (x == null) return;
        int cmp1 = lo.compareTo(x.key);
        int cmp2 = hi.compareTo(x.key);
        if (cmp1 > 0) InOrderEnqueue(queue, x.right, lo, hi);
        else if (cmp2 < 0) InOrderEnqueue(queue, x.left, lo, hi);
        else {
            InOrderEnqueue(queue, x.left, lo, hi);
            queue.enqueue(x.key);
            InOrderEnqueue(queue, x.right, lo, hi);
        }
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
        if (x == null) return black == 0;
        if (!isRed(x)) black--;
        return isBalanced(x.left, black) && isBalanced(x.right, black);
    }

    public void deleteMin() {
        if (root == null) return;
        // flipColors()假设x与x.left, x.right的颜色不同
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;
        root = deleteMin(root);
        // 恢复root 的颜色
        if (!isEmpty()) root.color = BLACK;
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        if (!isRed(x.left) && !isRed(x.left.left))
            x = moveRedLeft(x);
        x.left = deleteMin(x.left);
        return balance(x);
    }

    public void deleteMax() {
        if (root == null) return;
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;
        root = deleteMax(root);
        if (!isEmpty()) root.color = BLACK;
    }

    private Node deleteMax(Node x) {
        if (isRed(x.left)) x = rotateRight(x);
        if (x.right == null) return x.left;
        // 右子节点是2-node
        if (!isRed(x.right) && !isRed(x.right.left))
            x = moveRedRight(x);
        x.right = deleteMax(x.right);
        return balance(x);
    }

    public void delete(Key key) {
        if (root == null) return;
        if (!contains(key)) return;
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;
        root = delete(root, key);
        if (!isEmpty()) root.color = BLACK;
    }

    private Node delete(Node x, Key key) {
        if (key.compareTo(x.key) < 0) {
            if (!isRed(x.left) && !isRed(x.left.left))
                x = moveRedLeft(x);
            x.left = delete(x.left, key);
        } else {
            if (isRed(x.left)) x = rotateRight(x);
            if (key.compareTo(x.key) == 0 && x.right == null)
                return x.left;
            if (!isRed(x.right) && !isRed(x.right.left))
                x = moveRedRight(x);
            if (key.compareTo(x.key) == 0) {
                Node h = min(x.right);
                x.key = h.key;
                x.value = h.value;
                x.right = deleteMin(x.right);
            } else x.right = delete(x.right, key);
        }
        return balance(x);
    }

    public void InOrderTraverse() {
        InOrderTraverse(root);
    }

    private void InOrderTraverse(Node x) {
        if (x == null) return;
        InOrderTraverse(x.left);
        StdOut.print(x.key + " ");
        InOrderTraverse(x.right);
    }

    public void PostOrderTraverse() {
        PostOrderTraverse(root);
    }

    private void PostOrderTraverse(Node x) {
        if (x == null) return;
        PostOrderTraverse(x.left);
        PostOrderTraverse(x.right);
        StdOut.print(x.key + " ");
    }

    private Node balance(Node x) {
        if (isRed(x.right) && !isRed(x.left)) x = rotateLeft(x);
        if (isRed(x.left) && isRed(x.left.left)) x = rotateRight(x);
        if (isRed(x.left) && isRed(x.right)) flipColors(x);
        x.count = size(x.left) + size(x.right) + 1;
        return x;
    }

    public static void main(String[] args) {
        String str = "E A S Y Q U T I O N";
        String[] keys = str.split(" ");
        RedBlackBST<String, Integer> bst = new RedBlackBST<>();
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
            StdOut.print(key + " ");
        StdOut.println();

        StdOut.println("TEST InOrderTraverse()");
        StdOut.println("----------------------------------");
        bst.InOrderTraverse();
        StdOut.println();
        StdOut.println();

        StdOut.println("TEST PostOrderTraverse()");
        StdOut.println("----------------------------------");
        bst.PostOrderTraverse();
        StdOut.println();
        StdOut.println();

        StdOut.println("TEST get()");
        StdOut.println("----------------------------------");
        for (int i = 0; i < keys.length; i++)
            StdOut.println(keys[i] + ":" + bst.get(keys[i]));
        StdOut.println();

        StdOut.println("TEST size(lo, hi), keys(lo, hi):");
        StdOut.println("----------------------------------");
        StdOut.print("size(K, T): " + bst.size("K", "T") + ", ");
        allkeys = bst.keys("K", "T");
        for (String key : allkeys)
            StdOut.print(key + " ");
        StdOut.println();
        StdOut.print("size(A, O): " + bst.size("A", "O") + ", ");
        allkeys = bst.keys("A", "O");
        for (String key : allkeys)
            StdOut.print(key + " ");
        StdOut.println();
        StdOut.print("size(B, Q): " + bst.size("B", "Q") + ", ");
        allkeys = bst.keys("B", "Q");
        for (String key : allkeys)
            StdOut.print(key + " ");
        StdOut.println();
        StdOut.print("size(V, Y): " + bst.size("V", "Y") + ", ");
        allkeys = bst.keys("V", "Y");
        for (String key : allkeys)
            StdOut.print(key + " ");
        StdOut.println();
        StdOut.print("size(B, R): " + bst.size("B", "R") + ", ");
        allkeys = bst.keys("B", "R");
        for (String key : allkeys)
            StdOut.print(key + " ");
        StdOut.println();
        StdOut.println();

        StdOut.println("TEST deleteMin()");
        StdOut.println("----------------------------------");
        for (int i = 0; i < keys.length / 2; i++) {
            StdOut.print("delete: " + bst.min());
            bst.deleteMin();
            StdOut.println(", now size: " + bst.size() + ", height: " + bst.height() + ", min: " + bst.min());
        }
        StdOut.println();

        StdOut.println("After deleteMin()");
        StdOut.println("----------------------------------");
        allkeys = bst.keys();
        for (String key : allkeys)
            StdOut.print(key + " ");
        StdOut.println();
        StdOut.println();

        StdOut.println("TEST deleteMax()");
        StdOut.println("----------------------------------");
        for (int i = 0; i < keys.length / 2; i++) {
            StdOut.print("delete: " + bst.max());
            bst.deleteMax();
            StdOut.println(", now size: " + bst.size() + ", height: " + bst.height() + ", max: " + bst.max());
        }
        StdOut.println();

        StdOut.println("After deleteMax()");
        StdOut.println("----------------------------------");
        allkeys = bst.keys();
        for (String key : allkeys)
            StdOut.print(key + " ");
        StdOut.println();

        StdOut.println("Add all keys");
        StdOut.println("----------------------------------");
        for (int i = 0; i < keys.length; i++)
            bst.put(keys[i], i);
        allkeys = bst.keys();
        for (String key : allkeys)
            StdOut.print(key + " ");
        StdOut.println();
        StdOut.println();

        StdOut.println("TEST delete()");
        StdOut.println("----------------------------------");
        for (int i = 0; i < keys.length; i++) {
            bst.delete(keys[i]);
            StdOut.println("delete " + keys[i] + ", now size: " + bst.size() + ", height:" + bst.height());
        }
        StdOut.println();

        StdOut.println("After deletion");
        StdOut.println("----------------------------------");
        allkeys = bst.keys();
        for (String key : allkeys)
            StdOut.println(key + " ");
        StdOut.println();

    }
}
