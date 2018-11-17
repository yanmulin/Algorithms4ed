package chapter2_2;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class LinkedListShuffle {
    public static class Node {
        Comparable data;
        Node next;
    }

    public static void Sort(Comparable[] a) {
        Node first = ConstructLinkedList(a);
        first = Sort(first, a.length);
        for (int i = 0; first != null; i++, first = first.next)
            a[i] = first.data;
    }

    public static Node Sort(Node first, int size) {
        Node dummy = new Node();
        dummy.next = first;
        for (int sz = 1; sz <= size; sz = sz + sz) {
            Node prelo, mid, hi;
            StdOut.println();
            for (prelo = dummy; prelo.next != null; prelo = hi) {
                mid = findNode(prelo, sz);
                hi = findNode(mid, sz);
                hi = merge(prelo, mid, hi);
            }
        }
        return dummy.next;
    }

    private static Node findNode(Node start, int step) {
        while (step-- > 0 && start.next != null)
            start = start.next;
        return start;
    }

    private static Node merge(Node prelo, Node mid, Node hi) {
        Node p = prelo, q = mid;
        Node hi_end = hi.next;
        while (p != mid && q.next != hi_end) {
            // if (less(q.next.data, p.next.data) > 0) {
            if (StdRandom.uniform(2) == 1) {
                Node n = q.next;
                q.next = n.next;
                n.next = p.next;
                p.next = n;
            }
            p = p.next;
        }
        while (q.next != hi_end)
            q = q.next;
        return q;
    }


    private static int less(Comparable a, Comparable b) {
        return b.compareTo(a);
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
        Integer[] a = {4, 5, 6, 1, 2, 3};
        Node first = ConstructLinkedList(a);
        first = Sort(first, a.length);
        Node p = first;
        while (p != null) {
            StdOut.print(p.data + " ");
            p = p.next;
        }
        StdOut.println();
    }
}
