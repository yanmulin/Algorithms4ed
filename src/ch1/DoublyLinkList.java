package ch1;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class DoublyLinkList<Item> implements Iterable<Item> {
    private Node first, last;
    private int sz;

    private class Node {
        public Item item;
        public Node next;
        public Node prev;
    }

    public void pushLeft(Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.prev = null;
        first.next = oldfirst;
        if (oldfirst == null) last = first;
        else oldfirst.prev = first;
        sz++;
    }

    public void pushRight(Item item) {
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        last.prev = oldlast;
        if (oldlast == null) first = last;
        else oldlast.next = last;
        sz++;
    }

    public Item popLeft() {
        Item item = last.item;
        last = last.prev;
        last.next = null;
        sz--;
        return item;
    }

    public Item popRight() {
        Item item = first.item;
        first = first.next;
        first.prev = null;
        sz--;
        return item;
    }

    public void InsertAfter(int k, Item item) {
        Node n = new Node();
        n.item = item;
        Node x = first;
        while (k-- > 1 && x.next != null) x = x.next; // 移动k-1次
        n.next = x.next;
        n.prev = x;
        if (x.next != null) x.next.prev = n;
        x.next = n;
        sz++;

        // 更新 last
        if (x == last) last = n;
    }

    public void InsertBefore(int k, Item item) {
        Node n = new Node();
        n.item = item;
        Node x = first;
        while (k-- > 1 && x.next != null) x = x.next;
        n.next = x;
        n.prev = x.prev;
        if (x.prev != null) x.prev.next = n;
        x.prev = n;
        sz++;

        // 更新 first
        if (x == first) first = n;
    }

    public Item remove(int k) {
        Node x = first;
        while (k-- > 1 && x != null) x = x.next; // 移动k-1次
        assert x != null;
        Item item = x.item;
        if (x.prev != null) x.prev.next = x.next;
        else first = x.next;
        if (x.next != null) x.next.prev = x.prev;
        else last = x.prev;
        sz--;
        return item;
    }

    public Iterator<Item> iterator() {
        return new DLLIterator();
    }

    private class DLLIterator implements Iterator<Item> {
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
        DoublyLinkList<Integer> l = new DoublyLinkList<>();
        l.pushRight(3);
        l.pushRight(4);
        l.pushLeft(2);
        l.pushLeft(1);
        for (int i : l) {
            StdOut.print(i + " ");
        }
        StdOut.println();
        l.InsertBefore(1, 0);
        l.InsertBefore(2, 1);
        l.InsertAfter(6, 5);
        l.InsertAfter(3, 1);
        for (int i : l) {
            StdOut.print(i + " ");
        }
        StdOut.println();

        l.remove(1);
        for (int i : l) {
            StdOut.print(i + " ");
        }
        StdOut.println();

        l.remove(3);
        for (int i : l) {
            StdOut.print(i + " ");
        }
        StdOut.println();

        l.remove(6);
        for (int i : l) {
            StdOut.print(i + " ");
        }
        StdOut.println();
    }
}
