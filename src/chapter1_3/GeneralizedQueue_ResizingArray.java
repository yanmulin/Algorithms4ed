package chapter1_3;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class GeneralizedQueue_ResizingArray<Item> implements Iterable<Item> {
    private Item[] items = (Item[]) new Object[1];
    private int head, tail;
    private int sz;

    private void resize(int capacity) {
        Item[] old = items;
        items = (Item[]) new Object[capacity];
        for (int i = 0; i < sz; i++)
            items[i] = old[(head + i) % old.length];
        head = 0;
        tail = sz;
    }

    public boolean isEmpty() {
        return sz == 0;
    }

    public void insert(Item item) {
        if (sz == items.length)
            resize(items.length * 2);
        items[tail] = item;
        tail = (tail + 1) % items.length;
        sz++;
    }

    public Item delete(int x) {
        if (isEmpty()) return null;
        Item item = items[(head + x) % items.length];
        while (--x > 0) {
            items[(head + x) % items.length] =
                    items[(head + x - 1) % items.length];
        }
        head = (head + 1) % items.length;
        sz--;
        return item;
    }

    public Iterator iterator() {
        return new GeneralizedQueueIterator();
    }

    private class GeneralizedQueueIterator implements Iterator<Item> {
        private int current = head;

        public boolean hasNext() {
            return current == tail;
        }

        public Item next() {
            Item item = items[current];
            current = (current + 1) % items.length;
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
