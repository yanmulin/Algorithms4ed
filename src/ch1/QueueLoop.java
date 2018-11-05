package ch1;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class QueueLoop<Item> implements Iterable<Item> {
    private Node last;
    private int sz;

    private class Node {
        Item item;
        Node next;
    }

    public void enqueue(Item item) {
        Node x = new Node();
        x.item = item;
        if (last != null)
            x.next = last.next;
        else last = x;
        last.next = x;
        sz++;
    }

    public Item dequeue() {
        Item item = last.next.item;
        if (last.next == last) last = null;
        else last.next = last.next.next;
        sz--;
        return item;
    }

    public boolean isEmpty() {
        return last == null;
    }

    public int size() {
        return sz;
    }

    public Iterator<Item> iterator() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<Item> {
        private Node current = last.next;
        private int cnt = 0;

        public boolean hasNext() {
            return cnt < sz;
        }

        public Item next() {
            Item item = current.item;
            current = current.next;
            cnt++;
            return item;
        }
    }

    public static void main(String[] args) {
        QueueLoop<Integer> q = new QueueLoop<>();
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        for (int i : q) {
            StdOut.print(i + " ");
        }
        StdOut.println();
        StdOut.println(q.dequeue());
        StdOut.println(q.dequeue());
        StdOut.println(q.dequeue());
    }
}
