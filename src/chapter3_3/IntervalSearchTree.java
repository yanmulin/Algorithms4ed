package chapter3_3;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

// 假设每个区间的端点值都不一样
public class IntervalSearchTree<Key extends Comparable<Key>, Value> {
    private Node root;

    private class Node {
        private Key lo, hi;
        private Key max;
        private Value value;
        private Node left, right;

        public Node(Key lo, Key hi, Value value, Key max) {
            this.lo = lo;
            this.hi = hi;
            this.value = value;
            this.max = max;
        }
    }

    public void put(Key lo, Key hi, Value value) {
        root = put(root, lo, hi, value);
    }

    private Node put(Node x, Key lo, Key hi, Value value) {
        if (x == null) return new Node(lo, hi, value, hi);

        int cmp = lo.compareTo(x.lo);
        if (cmp == 0) x.value = value;
        else if (cmp > 0) x.right = put(x.right, lo, hi, value);
        else x.left = put(x.left, lo, hi, value);

        if (x.left == null) x.max = x.right.max;
        else if (x.right == null) x.max = x.left.max;
        else x.max = x.right.max.compareTo(x.left.max) > 0 ? x.right.max : x.left.max;

        return x;
    }

    public Value get(Key lo, Key hi) {
        Node current = root;
        while (current != null) {
            if (isIntersect(current, lo, hi)) return current.value;
            else if (current.left == null) current = current.right;
            else if (current.left.max.compareTo(lo) < 0) current = current.right;
            else current = current.left;
        }
        return null;
    }

    public void delete(Key lo, Key hi) {
    }

    public Iterable<Value> intersect(Key lo, Key hi) {
        Queue<Value> queue = new Queue<>();
        intersect(root, queue, lo, hi);
        return queue;
    }

    private void intersect(Node x, Queue<Value> queue, Key lo, Key hi) {
        if (x == null) return;
        if (isIntersect(x, lo, hi)) {
            intersect(x.left, queue, lo, hi);
            queue.enqueue(x.value);
            intersect(x.right, queue, lo, hi);
        } else if (x.left == null) intersect(x.right, queue, lo, hi);
        else if (hi.compareTo(x.left.max) > 0) intersect(x.right, queue, lo, hi);
        else intersect(x.left, queue, lo, hi);
    }

    private boolean isIntersect(Node x, Key lo, Key hi) {
        if (x == null) throw new java.lang.IllegalArgumentException();
        if (hi.compareTo(x.lo) < 0) return false;
        if (lo.compareTo(x.hi) > 0) return false;
        return true;
    }

    private void traversePrint() {
        StdOut.print("Pre-order: ");
        preOrderTraversePrint(root);
        StdOut.println();
        StdOut.print("In-order: ");
        inOrderTraversePrint(root);
    }

    private void inOrderTraversePrint(Node x) {
        if (x == null) return;
        inOrderTraversePrint(x.left);
        StdOut.print(x.value + " ");
        inOrderTraversePrint(x.right);
    }

    private void preOrderTraversePrint(Node x) {
        if (x == null) return;
        StdOut.print(x.value + " ");
        preOrderTraversePrint(x.left);
        preOrderTraversePrint(x.right);
    }

    public static void main(String[] args) {
        IntervalSearchTree<Integer, Integer> st = new IntervalSearchTree<>();
        st.put(15, 28, 1);
        st.put(8, 10, 2);
        st.put(27, 28, 3);
        st.put(1, 5, 4);
        st.put(13, 21, 5);
        st.put(16, 38, 6);
        StdOut.println("TEST traversePrint():");
        StdOut.println("-----------------------------------");
        st.traversePrint();
        StdOut.println();
        StdOut.println();

        StdOut.println("TEST get():");
        StdOut.println("-----------------------------------");
        StdOut.println("get(4, 7): " + st.get(4, 7));
        StdOut.println("get(12, 14): " + st.get(12, 14));
        StdOut.println("get(30, 35): " + st.get(30, 35));
        StdOut.println();

        StdOut.println("TEST intersect():");
        StdOut.println("-----------------------------------");
        Iterable<Integer> set = st.intersect(12, 25);
        for (Integer x : set)
            StdOut.print(x + " ");
        StdOut.println();
    }
}
