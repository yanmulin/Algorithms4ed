package chapter2_1;

import edu.princeton.cs.algs4.StdOut;

public class InsertionSortImprove2 {
    public static void Sort(Comparable[] a) {
        int n = a.length;
        for (int i = 1; i < n; i++) {
            Comparable x = a[i];
            int j;
            for (j = i; j > 0 && less(x, a[j - 1]) > 0; j--)
                a[j] = a[j - 1];
            a[j] = x;
        }
        assert isSorted(a);
    }

    private static int less(Comparable a, Comparable b) {
        return b.compareTo(a);
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i - 1], a[i]) < 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Double[] a = {0.4, 0.2, 0.3, 0.6, 0.1, 0.8};
        Sort(a);
        for (double d : a)
            StdOut.print(d + " ");
        StdOut.println();
    }
}
