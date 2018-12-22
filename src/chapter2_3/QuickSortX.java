package chapter2_3;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class QuickSortX {
    public static void Sort(Comparable[] a) {
        StdRandom.shuffle(a);
        int max = 0;
        for (int i = 1; i < a.length; i++)
            max = less(a[max], a[i]) > 0 ? i : max;
        exch(a, max, a.length - 1);
        Sort(a, 0, a.length - 1);
    }

    public static void Sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi) return;
        int mid = Partition(a, lo, hi);
        Sort(a, lo, mid - 1);
        Sort(a, mid + 1, hi);
    }

    private static int Partition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        while (true) {
            while (less(a[++i], a[lo]) > 0) ;
            while (less(a[lo], a[--j]) > 0) ;
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, j, lo);
        return j;
    }

    private static int less(Comparable a, Comparable b) {
        return b.compareTo(a);
    }

    private static void exch(Comparable a[], int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        Integer[] a = new Integer[n];
        for (int i = 0; i < n; i++)
            a[i] = StdRandom.uniform(n);
        Sort(a);
        for (int x : a)
            StdOut.print(x + " ");
        StdOut.println();
    }
}
