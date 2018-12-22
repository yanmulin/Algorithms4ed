package chapter2_4;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

public class IndexMinPQ<Key extends Comparable<Key>> {
    private int[] pq;
    private int[] qp;
    private Key[] keys;
    private int N;

    private void swim(int k) {
        while (k > 1 && less(k, k / 2)) {
            exch(k, k / 2);
            k /= 2;
        }
    }

    private void sink(int k) {
        while (k * 2 <= N) {
            int j = k * 2;
            if (j < N && less(j + 1, j)) j++;
            if (less(k, j)) break;
            exch(j, k);
            k = j;
        }
    }

    private boolean less(int i, int j) {
        return keys[pq[i]].compareTo(keys[pq[j]]) < 0;
    }

    private void exch(int i, int j) {
        int temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    public IndexMinPQ(int maxN) {
        pq = new int[maxN + 1];
        qp = new int[maxN + 1];
        keys = (Key[]) new Comparable[maxN];
        for (int i = 0; i <= maxN; i++)
            qp[i] = -1;
        N = 0;
    }

    public void insert(int k, Key key) {
        keys[k] = key;
        pq[++N] = k;
        swim(N);
        qp[k] = N;
    }

    public int delMin() {
        int indexMin = pq[1];
        exch(1, N--);
        keys[indexMin] = null;
        qp[indexMin] = -1;
        sink(1);
        return indexMin;
    }

    public int minIndex() {
        return pq[1];
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public boolean contains(int k) {
        return qp[k] != -1;
    }

    public void change(int k, Key key) {
        keys[k] = key;
        swim(qp[k]);
        sink(qp[k]);
    }

    public void delete(int k) {
        int index = qp[k];
        exch(index, N--);
        swim(index);
        sink(index);
        keys[k] = null;
        qp[k] = -1;
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int[] a = new int[n];
        int[] b = new int[n];
        IndexMinPQ pq = new IndexMinPQ(n);
        for (int i = 0; i < n; i++) {
            a[i] = b[i] = StdRandom.uniform(100);
            pq.insert(i, a[i]);
        }
        Arrays.sort(a);
        for (int i = 0; i < n; i++) {
            int ret = pq.delMin();
            if (a[i] != b[ret]) {
                StdOut.println("Failed at #" + i + ", expected " + a[i] + ", actual " + ret);
            }
        }
        int[] c = {0, 1, 2, 3, 4, 5, 6, 7};
        IndexMinPQ pq2 = new IndexMinPQ(c.length);
        for (int i = 0; i < c.length; i++)
            pq2.insert(i, c[i]);
        pq2.delete(3);
        pq2.change(2, -1);
        c[2] = -1;
        while (!pq2.isEmpty()) {
            StdOut.println(c[pq2.delMin()]);
        }
    }
}
