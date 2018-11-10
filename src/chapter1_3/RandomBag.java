package ch1;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class RandomBag<Item> implements Iterable<Item> {
    private Node first;
    private int sz;

    private class Node {
        private Item item;
        private Node next;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return sz;
    }

    public void add(Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = null;
        if (oldfirst != null) oldfirst.next = first;
        sz++;
    }

    public Iterator<Item> iterator() {
        return new RandomBagIterator();
    }

    private class RandomBagIterator implements Iterator<Item> {
        private int current;
        private Item[] data;

        public RandomBagIterator() {
            Node x = first;
            data = (Item[]) new Object[sz];
            for (int i = 0; i < sz; i++) {
                data[i] = x.item;
                x = x.next;
            }
            StdRandom.shuffle(data);
        }

        public boolean hasNext() {
            return current < sz;
        }

        public Item next() {
            return data[current++];
        }
    }
}
