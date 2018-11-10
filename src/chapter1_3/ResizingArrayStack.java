package ch1;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class ResizingArrayStack<Item> implements Iterable<Item> {
    private Item[] items = (Item[]) new Object[1];
    ;
    private int N;

    private void resize(int capacity) {
        Item[] old = items;
        items = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++)
            items[i] = old[i];
    }

    public void push(Item item) {
        if (N >= items.length)
            resize(items.length * 2);
        items[N++] = item;
    }

    public Item pop() {
        assert !isEmpty();
        Item item = items[--N];
        items[N] = null;//避免容器里对象游离
        if (N > 0 && N <= items.length / 4)
            resize(items.length / 2);
        return item;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public int capacity() {
        return items.length;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private int idx;

        public boolean hasNext() {
            return idx < N;
        }

        public Item next() {
            return items[idx++];
        }
    }

    public static void main(String[] args) {
        ResizingArrayStack<Integer> stk = new ResizingArrayStack<>();//自动装箱，拆箱
        if (!stk.isEmpty()) StdOut.println("init not empty!");
        In in = new In("algs4-data/1Kints.txt");
        int[] data = in.readAllInts();
        StdDraw.setXscale(0.0, 2.0);
        StdDraw.setYscale(0.0, 2.0);
        for (int i = 0; i < data.length; i++) {
            stk.push(data[i]);
            StdDraw.setPenColor(StdDraw.DARK_GRAY);
            StdDraw.point(i / 1000.0, stk.size() / 1000.0);
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.point(i / 1000.0, stk.capacity() / 1000.0);
        }
        StdOut.println("size: " + stk.size());
        int cnt = 0;
        for (int item : stk) {
            cnt++;
        }
        if (cnt != stk.size()) StdOut.println("iterator size not match");

        for (int i = data.length - 1; i >= 0; i--) {
            if (stk.pop() != data[i]) StdOut.println("#" + i + "not match");
            StdDraw.setPenColor(StdDraw.DARK_GRAY);
            StdDraw.point((data.length - i - 1) / 1000.0 + 1, stk.size() / 1000.0);
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.point((data.length - i - 1) / 1000.0 + 1, stk.capacity() / 1000.0);
        }
        if (!stk.isEmpty()) StdOut.println("last not empty!");
    }
}
