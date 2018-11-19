package chapter2_3;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class QuickSort3wayPartition {
    public static void Sort(Comparable[] a) {
        StdRandom.shuffle(a);
        Sort(a, 0, a.length - 1);
    }

    public static void Sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi) return;
        int lt = lo, gt = hi, i = lo;
        Comparable v = a[lo];
        while (i <= gt) {
            int cmp = v.compareTo(a[i]);
            if (cmp == 0) i++;
            else if (cmp < 0) exch(a, i, gt--);
            else exch(a, i++, lt++);
        }
        Sort(a, lo, lt - 1);
        Sort(a, gt + 1, hi);
    }

    private static void exch(Comparable a[], int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static int less(Comparable a, Comparable b) {
        return b.compareTo(a);
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1]) > 0)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        Integer[] a = new Integer[n];
        for (int i = 0; i < n; i++)
            a[i] = StdRandom.uniform(10);
        Sort(a);
        for (int x : a)
            StdOut.print(x + " ");
        StdOut.println();
        StdOut.println(isSorted(a));
    }
}
