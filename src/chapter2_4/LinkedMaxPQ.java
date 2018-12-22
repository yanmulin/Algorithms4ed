package chapter2_4;

import chapter1_3.Deque;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class LinkedMaxPQ<Key extends Comparable<Key>> {
    private class Node {
        public Key val;
        public Node parent;
        public Node lchild, rchild;
    }

    private Node root;
    private int N;
    private Deque<Node> q = new Deque<>();

    private void sink(Node v) {
        while (v.lchild != null) {
            Node n = v.lchild;
            if (v.rchild != null && less(n.val, v.rchild.val))
                n = v.rchild;
            if (less(n.val, v.val)) break;
            exch(v, n);
            v = n;
        }
    }

    private void swim(Node v) {
        while (v != root && less(v.parent.val, v.val)) {
            exch(v, v.parent);
            v = v.parent;
        }
    }

    public void insert(Key v) {
//        StdOut.println("insert " + v);
        Node last = new Node();
        last.val = v;
        last.lchild = last.rchild = null;
        if (root == null) {
            last.parent = null;
            root = last;
            q.pushRight(last);
//            StdOut.println("Test: " + root.val + "\n");
            return;
        }
        Node parent = q.top();
//        StdOut.println("insert-parent: " + parent.val);
        last.parent = parent;
        if (parent.lchild == null) {
            parent.lchild = last;
        } else {
            parent.rchild = last;
            q.popLeft();
        }
        q.pushRight(last);
        swim(last);
//        StdOut.println("insert-last: " + q.top().val);
//        StdOut.println("insert-size: " + q.size() + " top: " + q.top().val);
//        StdOut.println("insert-root: " + root.val);
//        StdOut.println();
        N++;
    }

    public Key delMax() {
        Key v = root.val;
//        StdOut.println("del " + v);
        Node last = q.popRight();
        if (last == root) {
            root = null;
            return v;
        }
        assert (last.lchild == null && last.rchild == null);
        exch(root, last);

//        StdOut.println("del-size: " + q.size());
//        StdOut.println("del-last: " + root.val);
        if (last.parent.lchild == last)
            last.parent.lchild = null;
        else {
            q.pushLeft(last.parent);
            last.parent.rchild = null;
        }
        sink(root);
        N--;
//        StdOut.println("del-root: " + root.val + "\n");
        return v;
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return root == null;
    }

    private static boolean less(Comparable v, Comparable w) {
        return w.compareTo(v) > 0;
    }

    private void exch(Node v, Node w) {
        Key temp = v.val;
        v.val = w.val;
        w.val = temp;
    }

    public static void main(String[] args) {
        String[] a;
        if (args.length == 0)
            a = StdIn.readAllStrings();
        else {
            In in = new In(args[0]);
            a = in.readAllStrings();
        }
        LinkedMaxPQ<String> pq = new LinkedMaxPQ<>();
        for (int i = 0; i < a.length; i++) {
            if (a[i].equals("*")) StdOut.print(pq.delMax() + " ");
            else pq.insert(a[i]);
        }
    }
}
