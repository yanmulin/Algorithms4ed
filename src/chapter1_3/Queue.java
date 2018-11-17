package chapter1_3;

import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;
import java.util.Iterator;

public class Queue<Item> implements Iterable<Item> {
    private Node first, last;
    private int N;

    private class Node {
        public Item item;
        public Node next;
    }

    public void enqueue(Item item) {
        //尾插法
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else oldlast.next = last;
        N++;
    }

    public Item dequeue() {
        Item item = first.item;
        first = first.next;
        N--;
        if (isEmpty()) last = first;
        return item;
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void Merge(Queue q, Comparator c) {
        Node dummy = new Node();
        dummy.next = this.first;
        Node p1 = q.first;
        Node front = dummy, back = this.first;
        while (p1 != null) {
            while (back != null && c.compare(p1.item, back.item) > 0) {
                front = back;
                back = back.next;
            }
            Node n = new Node();
            n.item = p1.item;
            front.next = n;
            n.next = back;
            front = n;
            p1 = p1.next;
        }
        while (back != null) {
            front = back;
            back = back.next;
        }
        this.last = front;
        this.first = dummy.next;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    private static class TEST_COMPARATOR implements Comparator<Integer> {
        public int compare(Integer x, Integer y) {
            if (x > y) return 1;
            else if (x == y) return 0;
            else return -1;
        }
    }

    public static void main(String[] args) {
        TEST_COMPARATOR cmp = new TEST_COMPARATOR();
        Queue<Integer> q = new Queue<>();//自动装箱，拆箱
        q.enqueue(1);
        q.enqueue(3);
        q.enqueue(5);
        q.enqueue(7);
        Queue<Integer> qr = new Queue<>();
        qr.enqueue(2);
        qr.enqueue(4);
        qr.enqueue(6);
        q.Merge(qr, cmp);
        for (int x : q) {
            StdOut.println(x);
        }
//        if (!q.isEmpty()) StdOut.println("init not empty!");
//        In in = new In("algs4-data/1Kints.txt");
//        int[] data = in.readAllInts();
//        for (int i = 0; i < data.length; i++)
//            q.enqueue(data[i]);
//        StdOut.println("size: " + q.size());
//
//        int cnt = 0;
//        for (int item : q) {
//            cnt++;
//        }
//        if (cnt != q.size()) StdOut.println("iterator size not match");
//
//        for (int i = 0; i < data.length; i++) {
//            if (q.dequeue() != data[i]) StdOut.println("#" + i + "not match");
//        }
//        if (!q.isEmpty()) StdOut.println("last not empty!");
    }
}

