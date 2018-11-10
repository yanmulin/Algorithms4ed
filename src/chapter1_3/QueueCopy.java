package chapter1_3;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class QueueCopy<Item> implements Iterable<Item> {
    private Node first, last;
    private int N;

    private class Node {
        public Item item;
        public Node next;
    }

    public QueueCopy() {
    }

    public QueueCopy(QueueCopy<Item> q) {
        for (Item x : q)
            enqueue(x);
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
        QueueCopy<Integer> q = new QueueCopy<>();//自动装箱，拆箱
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(4);
        QueueCopy<Integer> r = new QueueCopy<>(q);
        for (int x : r)
            StdOut.print(x);
        StdOut.println();
    }
}
