package chapter2_2;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class MergeSortPerm {
    public static int[] Sort(Comparable[] a) {
        int n = a.length;
        int[] perm = new int[n];
        int[] aux = new int[n];
        for (int sz = 1; sz < n; sz = sz + sz) {
            for (int lo = 0; lo < n; lo += sz + sz) {
                merge(a, perm, aux, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, n - 1));
            }
        }
        return perm;
    }

    public static void merge(Comparable[] a, int[] perm, int[] aux, int lo, int mid, int hi) {
        for (int i = lo; i <= hi; i++)
            aux[i] = perm[i];
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) perm[k] = aux[j++];
            else if (j > hi) perm[k] = aux[i++];
            else if (less(a[aux[j]], a[aux[i]]) > 0)
                perm[k] = aux[j++];
            else perm[k] = aux[i++];
        }
    }

    public static int less(Comparable a, Comparable b) {
        return b.compareTo(a);
    }

    public static boolean isSorted(Comparable[] a, int[] perm) {
        assert (a.length == perm.length);
        for (int i = 1; i < a.length; i++) {
            if (less(a[perm[i]], a[perm[i - 1]]) > 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Double[] a = new Double[100];
        for (int i = 0; i < 100; i++)
            a[i] = StdRandom.uniform();
        int[] perm = Sort(a);
        StdOut.println(isSorted(a, perm));
    }
}
