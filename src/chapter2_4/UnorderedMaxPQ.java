package chapter2_4;

import edu.princeton.cs.algs4.StdOut;

public class UnorderedMaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int N;

    public UnorderedMaxPQ(int capacity) {
        pq = (Key[]) new Comparable[capacity];
        N = 0;
    }

    public void insert(Key v) {
        pq[N++] = v;
    }

    public Key delMax() {
        int max = 0;
        for (int i = 1; i < N; i++)
            max = less(pq[max], pq[i]) ? i : max;
        exch(N - 1, max);
        Key val = pq[N - 1];
        pq[--N] = null;
        return val;
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    private void exch(int i, int j) {
        Key temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    private boolean less(Key v, Key w) {
        return w.compareTo(v) > 0;
    }

    public static void main(String[] args) {
        UnorderedMaxPQ<Integer> pq = new UnorderedMaxPQ<>(10);
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
