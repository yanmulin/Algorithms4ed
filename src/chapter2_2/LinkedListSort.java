package chapter2_2;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class LinkedListSort {
    public static class Node {
        Comparable data;
        Node next;
    }

    public static void Sort(Comparable[] a) {
        Node first = ConstructLinkedList(a);
        first = Sort(first);
        for (int i = 0; first != null; i++, first = first.next)
            a[i] = first.data;
    }

    public static Node Sort(Node first) {
        Node mid = findSorted(first);
        while (mid.next != null) {
            Node hi = findSorted(mid.next);
            first = merge(first, mid, hi);
            mid = less(hi.data, mid.data) > 0 ? mid : hi; // 这里优化
        }
        return first;
    }

    private static Node findSorted(Node start) {
        while (start.next != null
                && less(start.data, start.next.data) >= 0)
            start = start.next;
        return start;
    }

    private static Node merge(Node lo, Node mid, Node hi) {
        Node dummy = new Node();
        dummy.next = lo;
        Node p = dummy, q = mid;
        Node hi_end = hi.next;
        while (p != mid && q.next != hi_end) {
            assert (p.next != null);
            if (less(q.next.data, p.next.data) > 0)
                // insert
                InsertNode(p, q);
            p = p.next;
        }
        return dummy.next;
    }


    private static int less(Comparable a, Comparable b) {
        return b.compareTo(a);
    }

    private static void InsertNode(Node preDst, Node preSrc) {
        assert (preDst != null && preDst.next != null);
        assert (preSrc != null && preSrc.next != null);

        Node n = preSrc.next;
        preSrc.next = n.next;
        n.next = preDst.next;
        preDst.next = n;
    }

    public static boolean isSorted(Node start) {
        if (start == null) return false;
        while (start.next != null && less(start.data, start.next.data) >= 0)
            start = start.next;
        if (start.next == null) return true;
        else return false;
    }

    public static Node ConstructLinkedList(Comparable[] a) {
        int N = a.length;
        Node dummy = new Node();
        Node p = dummy;
        for (int i = 0; i < N; i++) {
            p.next = new Node();
            p = p.next;
            p.data = a[i];
        }
        p.next = null;
        return dummy.next;
    }

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        for (int k = 0; k < 100; k++) {
            Double[] a = new Double[N];
            for (int i = 0; i < N; i++)
                a[i] = StdRandom.uniform();
            Node first = ConstructLinkedList(a);

            Sort(first);
            if (!isSorted(first)) StdOut.println("Failed");
        }
    }
}
