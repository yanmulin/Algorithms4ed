package chapter2_4;

import edu.princeton.cs.algs4.StdOut;

public class MinPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int N = 0;

    private void resize(int capacity) {
        Key[] old = pq;
        pq = (Key[]) new Comparable[capacity + 1];
        for (int i = 0; i <= N; i++)
            pq[i] = old[i];
    }

    public MinPQ(int capacity) {
        pq = (Key[]) new Comparable[capacity + 1];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void insert(Key v) {
        if (N == pq.length - 1) resize(pq.length);
        pq[++N] = v;
        swim(N);
    }

    public Key delMin() {
        Key v = pq[1];
        exch(1, N--);
        pq[N + 1] = null;
        sink(1);
        if (N == pq.length / 4) resize(pq.length / 2);
        return v;
    }

    public Key min() {
        return pq[1];
    }

    private void swim(int i) {
        while (i > 1 && less(pq[i], pq[i / 2])) {
            exch(i, i / 2);
            i /= 2;
        }
    }

    private void sink(int i) {
        while (i * 2 <= N) {
            int j = i * 2;
            if (j < N && less(pq[j + 1], pq[j])) j++;
            if (less(pq[i], pq[j])) break;
            exch(i, j);
            i = j;
        }
    }

    private boolean less(Key v, Key w) {
        return w.compareTo(v) > 0;
    }

    private void exch(int i, int j) {
        Key temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    public static boolean isMinHeap(Comparable[] a) {
        return isMinHeap(a, 1);
    }

    private static boolean isMinHeap(Comparable[] a, int root) {
        int n = a.length;
        if (root * 2 >= n) return true;
        int i = root * 2;
        if (a[i].compareTo(a[root]) < 0) return false;
        if (i < n - 1 && a[i + 1].compareTo(a[root]) < 0) return false;
        return isMinHeap(a, i) && isMinHeap(a, i + 1);
    }

    public static void main(String[] args) {
        Integer[] a = {1, 2, 3, 4, 5, 6, 7, 8};
        StdOut.println(isMinHeap(a));
    }
}
