package ch1.DynamicConnection;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class WeightedQU {
    private int[] id;
    private int[] sz;

    public WeightedQU(int n) {
        id = new int[n];
        sz = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
            sz[i] = 0;
        }
    }

    private int find(int a) {
        while (a != id[a]) {
            a = id[a];
        }
        return a;
    }

    public boolean connected(int a, int b) {
        return find(a) == find(b);
    }

    public void union(int a, int b) {
        int ida = find(a);
        int idb = find(b);
        if (ida == idb) return;
        if (sz[ida] > sz[idb]) {
            id[idb] = ida;
            sz[ida] += sz[idb];
        } else {
            id[ida] = idb;
            sz[idb] += sz[ida];
        }
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        int n = in.readInt();
        WeightedQU uf = new WeightedQU(n);
        Stopwatch watch = new Stopwatch();
        for (int i = 0; i < n; i++) {
            int a = in.readInt();
            int b = in.readInt();
            if (uf.connected(a, b)) {
                ;//StdOut.println("(" + a + ", " + b + ") is connected.");
            } else {
                uf.union(a, b);
            }
        }
        StdOut.println(n + " data: " + watch.elapsedTime() + "s.");
    }
}
