package chapter2_4;

import edu.princeton.cs.algs4.StdOut;

public class OrderedMaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int N;

    public OrderedMaxPQ(int capacity) {
        pq = (Key[]) new Comparable[capacity];
        N = 0;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void insert(Key v) {
        int i;
        for (i = N; i > 0 && less(v, pq[i - 1]); i--)
            exch(i - 1, i);
        pq[i] = v;
        N++;
    }

    public Key delMax() {
        Key v = pq[--N];
        pq[N] = null;
        return v;
    }

    private boolean less(Key v, Key w) {
        return w.compareTo(v) > 0;
    }

    private void exch(int i, int j) {
        Key temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    public static void main(String[] args) {
        OrderedMaxPQ<Integer> pq = new OrderedMaxPQ<>(10);
        pq.insert(1);
        pq.insert(5);
        pq.insert(3);
        StdOut.println(pq.delMax());
        pq.insert(2);
        StdOut.println(pq.delMax());
        pq.insert(4);
        StdOut.println(pq.delMax());
    }
}
