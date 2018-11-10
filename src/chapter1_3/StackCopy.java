package chapter1_3;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class StackCopy<Item> implements Iterable<Item> {
    private Node first;
    private int N;

    private class Node {
        Item item;
        Node next;
    }

    public StackCopy() {
    }

    public StackCopy(StackCopy<Item> s) {
        Node dummy = new Node();
        Node x = dummy;
        Node p = s.first;
        while (p != null) {
            x.next = new Node();
            x = x.next;
            x.item = p.item;
            p = p.next;
        }
        first = dummy.next;
    }

    public void push(Item item) {
        //头插法
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        N++;
    }

    public Item pop() {
        assert !isEmpty();
        Item item = first.item;
        first = first.next;
        N--;
        return item;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return N;
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
        StackCopy<Integer> q = new StackCopy<>();//自动装箱，拆箱
        q.push(1);
        q.push(2);
        q.push(3);
        q.push(4);
        StackCopy<Integer> r = new StackCopy<>(q);
        for (int x : r)
            StdOut.print(x + " ");
        StdOut.println();
    }
}
