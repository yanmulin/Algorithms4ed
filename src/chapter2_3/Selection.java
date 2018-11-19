package chapter2_3;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Selection {
    public static Comparable Select(Comparable[] a, int k) {
        StdRandom.shuffle(a);
        int lo = 0, hi = a.length - 1;
        while (lo < hi) {
            int mid = Partition(a, lo, hi);
            if (mid < k - 1) lo = mid + 1;
            else if (mid > k - 1) hi = mid - 1;
            else return a[mid];
        }
        return a[lo];
    }

    private static int Partition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        while (true) {
            while (less(a[++i], a[lo]) > 0)
                if (i >= hi) break;
            while (less(a[lo], a[--j]) > 0)
                if (j <= lo) break;
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
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
        Integer[] a = {1, 2, 4, 5, 6, 7, 8, 9};
        StdOut.println(Select(a, 3));
    }
}
