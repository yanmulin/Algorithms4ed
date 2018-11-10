package chapter1_3;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class GeneralizedQueue_LinkedList<Item> implements Iterable<Item> {
    private Node first, last;

    private class Node {
        Item item;
        Node next;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void insert(Item x) {
        Node oldlast = last;
        last = new Node();
        last.item = x;
        last.next = null;
        if (oldlast == null) first = last;
        else oldlast.next = last;
    }

    public Item delete(int x) {
        Node dummy = new Node();
        dummy.next = first;
        Node p = dummy;
        // 前进x-1次
        while (x-- > 1 && p.next != null) p = p.next;
        assert p.next != null;
        Item item = p.next.item;
        p.next = p.next.next;
        first = dummy.next;
        if (first == null) last = null;
        return item;
    }

    public Iterator iterator() {
        return new GeneralizedQueueIterator();
    }

    private class GeneralizedQueueIterator implements Iterator<Item> {
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
        GeneralizedQueue_LinkedList<Integer> gq = new GeneralizedQueue_LinkedList<>();
        gq.insert(1);
        gq.insert(2);
        gq.insert(3);
        gq.insert(4);
        gq.delete(2);
        for (int x : gq)
            StdOut.print(x + " ");
        StdOut.println();
        gq.insert(5);
        gq.insert(6);
        gq.insert(7);
        gq.insert(8);
        gq.delete(4);
        for (int x : gq)
            StdOut.print(x + " ");
        StdOut.println();
    }
}
