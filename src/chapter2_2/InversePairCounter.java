package chapter2_2;

import edu.princeton.cs.algs4.StdOut;

public class InversePairCounter {
    public static int Count(Comparable[] a) {
        int n = a.length;
        int count = 0;
        Comparable[] aux = new Comparable[n];
        for (int sz = 1; sz < n; sz = sz + sz) {
            for (int lo = 0; lo < n; lo += sz + sz) {
                count += merge(a, aux, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, n - 1));
            }
        }
        return count;
    }

    private static int merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        int count = 0;
        for (int i = lo; i <= hi; i++)
            aux[i] = a[i];
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (less(aux[j], aux[i]) > 0) {
                a[k] = aux[j++];
                count += mid - i + 1;
            } else a[k] = aux[i++];
        }
        return count;
    }

    private static int less(Comparable a, Comparable b) {
        return b.compareTo(a);
    }

    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i - 1]) > 0) return false;
        return true;
    }


    public static void main(String[] args) {
        Integer[] a = {4, 7, 3, 2, 1, 5, 6};
        StdOut.println(isSorted(a, 0, a.length - 1));
        StdOut.println(Count(a));
    }
}
