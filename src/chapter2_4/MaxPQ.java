package chapter2_4;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

public class MaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int N;

    private void resize(int capacity) {
        Key[] old = pq;
        pq = (Key[]) new Comparable[capacity + 1];
        for (int i = 1; i <= N; i++)
            pq[i] = old[i];
    }

    public MaxPQ(int capacity) {
        pq = (Key[]) new Comparable[capacity + 1];
        N = 0;
    }

    public void insert(Key v) {
        if (N == pq.length - 1) resize(N * 2);
        pq[++N] = v;
        swim(N);
    }

    public Key delMax() {
        exch(1, N--);
        Key v = pq[N + 1];
        pq[N + 1] = null;
        sink(1);
        if (N == pq.length / 4) resize(pq.length / 2);
        return v;
    }

    private void swim(int i) {
        while (i > 1 && less(pq[i / 2], pq[i])) {
            exch(i, i / 2);
            i /= 2;
        }
    }

    private void sink(int i) {
        while (i * 2 <= N) {
            int j = i * 2;
            if (j + 1 <= N && less(pq[j], pq[j + 1])) j++;
//            StdOut.println(i + " " + j);
            if (less(pq[j], pq[i])) break;
            exch(i, j);
            i = j;
        }
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public Key max() {
        return pq[1];
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
        int n = Integer.parseInt(args[0]);
        int[] a = new int[n];
        MaxPQ<Integer> pq = new MaxPQ<>(5);
        for (int i = 0; i < n; i++) {
            a[i] = StdRandom.uniform(100);
            pq.insert(a[i]);
        }
        Arrays.sort(a);
        for (int i = a.length - 1; i >= 0; i--) {
            if (pq.delMax() != a[i]) {
                StdOut.println("failed at " + i + "th delMax");
            }
        }
    }
}
