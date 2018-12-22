package chapter2_4;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class MinPQ_noexchange<Key extends Comparable<Key>> {
    private Key[] pq;
    private int N;

    public MinPQ_noexchange(int capacity) {
        pq = (Key[]) new Comparable[capacity + 1];
        N = 0;
    }

    public void insert(Key v) {
        pq[++N] = v;
        swim(N);
    }

    public Key delMin() {
        Key v = pq[1];
        exch(1, N);
        pq[N--] = null;
        sink(1);
        return v;
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    private void swim(int i) {
        Key v = pq[i];
        while (i > 1 && less(v, pq[i / 2])) {
            pq[i] = pq[i / 2];
            i /= 2;
        }
        pq[i] = v;
    }

    private void sink(int i) {
        Key v = pq[i];
        while (i * 2 <= N) {
            int j = i * 2;
            if (j < N && less(pq[j + 1], pq[j])) j++;
            if (less(v, pq[j])) break;
            pq[i] = pq[j];
            i = j;
        }
        pq[i] = v;
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
        String[] a;
        if (args.length == 0)
            a = StdIn.readAllStrings();
        else {
            In in = new In(args[0]);
            a = in.readAllStrings();
        }
        LinkedMaxPQ<String> pq = new LinkedMaxPQ<>();
        for (int i = 0; i < a.length; i++) {
            if (a[i].equals("*")) StdOut.print(pq.delMax() + " ");
            else pq.insert(a[i]);
        }
    }
}
