package chapter1_3;

import java.util.Iterator;

public class ResizingArrayDeque<Item> implements Iterable<Item> {
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

    public void pushLeft(Item item) {
        if ((tail + 1) % items.length == head)
            resize(items.length * 2);
        head = (head + items.length - 1) % items.length;
        items[head] = item;
        sz++;
    }

    public void pushRight(Item item) {
        if ((tail + 1) % items.length == head)
            resize(items.length * 2);
        items[tail] = item;
        tail = (tail + 1) % items.length;
    }

    public Item popLeft() {
        assert tail != head;
        Item item = items[head];
        items[head] = null; // 避免游离
        head = (head + 1) % items.length;
        if (sz <= items.length / 4)
            resize(items.length / 2);
        return item;
    }

    public Item popRight() {
        assert tail != head;
        tail = (tail + items.length - 1) % items.length;
        Item item = items[tail];
        items[tail] = null; // 避免游离
        if (sz <= items.length / 4)
            resize(items.length / 2);
        return item;
    }

    public boolean isEmpty() {
        return tail == head;
    }

    public int size() {
        return sz;
    }

    public Iterator<Item> iterator() {
        return new DequeueIterator();
    }

    private class DequeueIterator implements Iterator<Item> {
        private int current = 0;

        public boolean hasNext() {
            return current < sz;
        }

        public Item next() {
            return items[current++];
        }
    }
}
