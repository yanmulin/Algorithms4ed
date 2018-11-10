package chapter2_1;

import edu.princeton.cs.algs4.StdOut;

public class InsertionSortImproved {
    public static void Sort(Comparable[] a) {
        int n = a.length;
        int exchanges = 0;
        for (int i = n - 2; i >= 0; i--) {
            if (less(a[i + 1], a[i]) > 0) {
                exch(a, i + 1, i);
                exchanges++;
            }
        }
        if (exchanges == 0) return;
        for (int i = 1; i < n; i++) {
            for (int j = i; less(a[j], a[j - 1]) > 0; j--)
                exch(a, j, j - 1);
        }
        assert isSorted(a);
    }

    private static int less(Comparable a, Comparable b) {
        return b.compareTo(a);
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i - 1], a[i]) < 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Double[] a = {0.3, 0.1, 0.4, 0.6, 0.5, 0.2};
        Sort(a);
        for (double d : a)
            StdOut.print(d + " ");
        StdOut.println();
    }
}
