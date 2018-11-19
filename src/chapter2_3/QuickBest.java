package chapter2_3;

import edu.princeton.cs.algs4.StdOut;

public class QuickBest {
    public static void Best(Comparable[] a) {
        QuickSort.Sort(a);
        Best(a, 0, a.length - 1);
    }

    public static void Best(Comparable[] a, int lo, int hi) {
        if (lo >= hi) return;
        int mid = (lo + hi) / 2;
        Best(a, lo, mid - 1);
        Best(a, mid + 1, hi);
        exch(a, lo, mid);
    }

    private static void exch(Comparable a[], int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {
        Integer[] a = new Integer[10];
        for (int i = 0; i < 10; i++)
            a[i] = i;
        Best(a);
        for (int x : a)
            StdOut.print(x + " ");
        StdOut.println();

    }
}
