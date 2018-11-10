package chapter1_3;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class Stack<Item> implements Iterable<Item> {
    private Node first;
    private int N;

    private class Node {
        Item item;
        Node next;
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
        Stack<Integer> stk = new Stack<>();//自动装箱，拆箱
        if (!stk.isEmpty()) StdOut.println("init not empty!");
        In in = new In("algs4-data/1Kints.txt");
        int[] data = in.readAllInts();
        for (int i = 0; i < data.length; i++)
            stk.push(data[i]);
        StdOut.println("size: " + stk.size());

        int cnt = 0;
        for (int item : stk) {
            cnt++;
        }
        if (cnt != stk.size()) StdOut.println("iterator size not match");

        for (int i = data.length - 1; i >= 0; i--) {
            if (stk.pop() != data[i]) StdOut.println("#" + i + "not match");
        }
        if (!stk.isEmpty()) StdOut.println("last not empty!");
    }
}
