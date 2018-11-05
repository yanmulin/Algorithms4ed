package ch1;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

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

    public static void main(String[] args) {
        Queue<Integer> q = new Queue<>();//自动装箱，拆箱
        if (!q.isEmpty()) StdOut.println("init not empty!");
        In in = new In("algs4-data/1Kints.txt");
        int[] data = in.readAllInts();
        for (int i = 0; i < data.length; i++)
            q.enqueue(data[i]);
        StdOut.println("size: " + q.size());

        int cnt = 0;
        for (int item : q) {
            cnt++;
        }
        if (cnt != q.size()) StdOut.println("iterator size not match");

        for (int i = 0; i < data.length; i++) {
            if (q.dequeue() != data[i]) StdOut.println("#" + i + "not match");
        }
        if (!q.isEmpty()) StdOut.println("last not empty!");
    }
}
