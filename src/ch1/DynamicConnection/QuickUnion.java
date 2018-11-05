package ch1.DynamicConnection;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class QuickUnion {
    private int[] id;

    public QuickUnion(int n) {
        id = new int[n];
        for (int i = 0; i < n; i++)
            id[i] = i;
    }

    private int find(int a) {
        while (id[a] != a) {
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
        id[idb] = id[ida];
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        int n = in.readInt();
        QuickUnion qu = new QuickUnion(n);
        Stopwatch watch = new Stopwatch();
        for (int i = 0; i < n; i++) {
            int a = in.readInt();
            int b = in.readInt();
            if (qu.connected(a, b)) {
                ;//StdOut.println("(" + a + ", " + b + ") is connected.");
            } else {
                qu.union(a, b);
            }
        }
        StdOut.println(n + " data: " + watch.elapsedTime() + "s.");
    }
}
