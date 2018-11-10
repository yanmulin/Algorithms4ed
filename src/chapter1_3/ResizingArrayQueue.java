package ch1;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class ResizingArrayQueue<Item> implements Iterable<Item> {
    private Item[] items = (Item[]) new Object[1];
    private int head, tail;
    private int N;

    private void resize(int capacity) {
        Item[] old = items;
        items = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            items[i] = old[(head + i) % old.length];
        }
        head = 0;
        tail = N;
    }

    public void enqueue(Item item) {
        if ((tail + 1) % items.length == head)
            resize(items.length * 2);
        items[tail] = item;
        tail = (tail + 1) % items.length;
        N++;
    }

    public Item dequeue() {
        assert !isEmpty();
        Item item = items[head];
        items[head] = null; //避免游离
        head = (head + 1) % items.length;
        N--;
        if (N <= items.length / 4)
            resize(items.length / 2);
        return item;
    }

    public int size() {
        return N;
    }

    public int capacity() {
        return items.length;
    }

    public boolean isEmpty() {
        return head == tail;
    }

    public Iterator<Item> iterator() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<Item> {
        private int cnt;

        public boolean hasNext() {
            return cnt != N;
        }

        public Item next() {
            return items[(head + cnt) % items.length];
        }
    }

    public static void main(String[] args) {
        ResizingArrayQueue<Integer> q = new ResizingArrayQueue<>();//自动装箱，拆箱
        if (!q.isEmpty()) StdOut.println("init not empty!");
        In in = new In("algs4-data/1Kints.txt");
        int[] data = in.readAllInts();
        StdDraw.setXscale(0.0, 2.0);
        StdDraw.setYscale(0.0, 2.0);
        for (int i = 0; i < data.length; i++) {
            q.enqueue(data[i]);
            StdDraw.setPenColor(StdDraw.DARK_GRAY);
            StdDraw.point(i / 1000.0, q.size() / 1000.0);
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.point(i / 1000.0, q.capacity() / 1000.0);
        }
        StdOut.println("size: " + q.size());
        int cnt = 0;
        for (int item : q) {
            cnt++;
        }
        if (cnt != q.size()) StdOut.println("iterator size not match");

        for (int i = 0; i < data.length; i++) {
            if (q.dequeue() != data[i]) StdOut.println("#" + i + "not match");
            StdDraw.setPenColor(StdDraw.DARK_GRAY);
            StdDraw.point(i / 1000.0 + 1, q.size() / 1000.0);
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.point(i / 1000.0 + 1, q.capacity() / 1000.0);
        }
        if (!q.isEmpty()) StdOut.println("last not empty!");
    }
}
