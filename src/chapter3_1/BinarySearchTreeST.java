package chapter3_1;

import chapter1_3.Queue;
import edu.princeton.cs.algs4.StdOut;

public class BinarySearchTreeST<Key extends Comparable<Key>, Value> {
    private Node root;

    private class Node {
        private Key key;
        private Value value;
        private Node left, right;
        private int count;

        public Node(Key key, Value value, int count) {
            this.key = key;
            this.value = value;
            this.count = count;
        }
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

    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    private Node put(Node x, Key key, Value value) {
        if (x == null) return new Node(key, value, 1);
        int cmp = key.compareTo(x.key);
        if (cmp == 0) x.value = value;
        else if (cmp > 0) x.right = put(x.right, key, value);
        else if (cmp < 0) x.left = put(x.left, key, value);
        x.count = size(x.left) + size(x.right) + 1;
        assert (isSizeConsistent(x));
        assert (isBST(x, null, null));
        return x;
    }

    public boolean contain(Key key) {
        return get(key) != null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) return 0;
        else return x.count;
    }

    public void delete(Key key) {
        delete(root, key);
    }

    private Node delete(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = delete(x.left, key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else {
            if (x.left == null) return x.right;
            if (x.right == null) return x.left;
            Node p = min(x.right);
            x.right = deleteMin(x.right);
            p.left = x.left;
            p.right = x.right;
            x = p;
        }
        x.count = size(x.left) + size(x.right) + 1;
        assert (isSizeConsistent(x));
        assert (isBST(x, null, null));
        return x;
    }

    public Key min() {
        if (root == null) return null;
        Node x = min(root);
        return x.key;
    }

    private Node min(Node x) {
        if (x.left == null) return x;
        else return min(x.left);
    }

    public void deleteMin() {
        if (root != null) root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x.left != null) x.left = deleteMin(x.left);
        else return x.right;
        x.count = size(x.left) + size(x.right) + 1;
        assert (isBST(x.left, null, null));
        assert (isSizeConsistent(x));
        return x;
    }

    public Key max() {
        if (root == null) return null;
        Node x = max(root);
        return x.key;
    }

    private Node max(Node x) {
        if (x.right == null) return x;
        else return max(x.right);
    }

    public void deleteMax() {
        root = deleteMax(root);
    }

    private Node deleteMax(Node x) {
        if (x.right == null) return x.left;
        else x.right = deleteMax(x.right);
        x.count = size(x.left) + size(x.right) + 1;
        assert (isSizeConsistent(x));
        assert (isBST(x, null, null));
        return x;
    }

    public int rank(Key key) {
        return rank(root, key);
    }

    // Return the number of key in the symbol table strictly less than key
    private int rank(Node x, Key key) {
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return size(x.left);
        else if (cmp > 0) return rank(x.right, key) + size(x.left) + 1;
        else return rank(x.left, key);
    }

    public Key select(int k) {
        return select(root, k);
    }

    private Key select(Node x, int k) {
        if (k == size(x.left)) return x.key;
        else if (k > size(x.left)) return select(x.right, k - size(x.left) - 1);
        else return select(x.left, k);
    }

    public Key floor(Key key) {
        Node x = floor(root, key);
        if (x == null) return null;
        else return x.key;
    }

    private Node floor(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp > 0) {
            Node p = floor(x.right, key);
            if (p == null) return x;
            else return p;
        } else if (cmp < 0) return floor(x.left, key);
        else return x;
    }

    public Key ceiling(Key key) {
        Node x = ceiling(root, key);
        if (x == null) return null;
        else return x.key;
    }

    private Node ceiling(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp > 0) return ceiling(x.right, key);
        else if (cmp < 0) {
            Node p = ceiling(x.left, key);
            if (p == null) return x;
            else return p;
        } else return x;
    }

    public Iterable<Key> keys() {
        Queue<Key> q = new Queue<Key>();
        InOrderTraverse(root, q);
        return q;
    }

    private void InOrderTraverse(Node x, Queue<Key> q) {
        if (x == null) return;
        InOrderTraverse(x.left, q);
        q.enqueue(x.key);
        InOrderTraverse(x.right, q);
    }

    public int height() {
        return height(root);
    }

    private int height(Node x) {
        if (x == null) return 0;
        else return Math.max(height(x.left), height(x.right)) + 1;
    }

    private boolean isOrdered(Node x) {
        if (x == null) return true;
        if (x.left != null && x.key.compareTo(x.left.key) < 0) return false;
        if (x.right != null && x.key.compareTo(x.right.key) > 0) return false;
        return isOrdered(x.left) && isOrdered(x.right);
    }

    private boolean isSizeConsistent(Node x) {
        if (x == null) return true;
        if (isSizeConsistent(x.left) == false || isSizeConsistent(x.right) == false)
            return false;
        if (size(x.left) + size(x.right) + 1 != size(x)) return false;
        return true;
    }

    public boolean isRankConsistent() {
        int n = size();
        for (int i = 0; i < n; i++) {
            if (rank(select(i)) != i) return false;
        }
        Iterable<Key> keys = keys();
        for (Key key : keys) {
            if (key.compareTo(select(rank(key))) != 0)
                return false;
        }
        return true;
    }

    private boolean isBST() {
        return isBST(root, null, null);
    }

    private boolean isBST(Node x, Key min, Key max) {
        if (x == null) return true;
        if (min != null && min.compareTo(x.key) > 0) return false;
        if (max != null && max.compareTo(x.key) < 0) return false;
        return isBST(x.left, min, x.key) && isBST(x.right, x.key, max);
    }

    public static void main(String[] args) {
        BinarySearchTreeST<String, Integer> st = new BinarySearchTreeST<>();
        String[] a = {"e", "b", "a", "c", "h", "f", "i", "d", "g"};
        Integer[] b = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        for (int i = 0; i < a.length; i++) {
            st.put(a[i], b[i]);
        }
        StdOut.println("TEST keys()");
        Iterable<String> keys = st.keys();
        for (String key : keys) {
            BinarySearchTreeST<String, Integer>.Node x = st.get(st.root, key);
            StdOut.print(key + ":" + x.count + " ");
        }
        StdOut.println();
        StdOut.println("TEST get()");
        for (int i = 0; i < a.length; i++)
            StdOut.print("(" + a[i] + ", " + st.get(a[i]) + ") ");
        StdOut.println();
        StdOut.println("TEST height():" + st.height());
        StdOut.println("TEST min():" + st.min() + ", max():" + st.max());
        StdOut.println("TEST floor(\"b\"):" + st.floor("b") + ", ceiling(\"b\"):" + st.ceiling("b"));
        StdOut.println("TEST floor(\"bc\"):" + st.floor("bc") + ", ceiling(\"bc\"):" + st.ceiling("bc"));
        StdOut.println("TEST rank(\"h\"):" + st.rank("h") + ", select(7):" + st.select(7));
        StdOut.println("TEST isRankConsistent():" + st.isRankConsistent());
        StdOut.println("TEST delete(\"a\")");
        st.delete("a");
        keys = st.keys();
        for (String key : keys)
            StdOut.print("(" + key + ", " + st.get(key) + ") ");
        StdOut.println();
        StdOut.println("TEST delete(\"b\")");
        st.delete("b");
        keys = st.keys();
        for (String key : keys)
            StdOut.print("(" + key + ", " + st.get(key) + ") ");
        StdOut.println();
        StdOut.println("TEST delete(\"h\")");
        st.delete("h");
        keys = st.keys();
        for (String key : keys)
            StdOut.print("(" + key + ", " + st.get(key) + ") ");
    }
}
