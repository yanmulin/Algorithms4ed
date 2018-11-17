package chapter2_2;

import edu.princeton.cs.algs4.StdOut;

public class NaturalMergeSort {
    public static void Sort(Comparable[] a) {
        int n = a.length;
        Comparable[] aux = new Comparable[n];
        int lo = 0, mid = 0, hi = 0;
        boolean doneFlg = false;
        while (!doneFlg) {
            lo = 0;
            doneFlg = true;
            while ((mid = findSorted(a, aux, lo)) != n - 1) {
                hi = findSorted(a, aux, mid + 1);
                merge(a, aux, lo, mid, hi);
                lo = hi + 1;
                doneFlg = false;
            }
        }
    }

    private static int findSorted(Comparable[] a, Comparable[] aux, int start) {
        for (int i = start; i < a.length - 1; i++) {
            aux[i] = a[i];
            if (less(a[i], a[i + 1]) < 0) return i;
        }
        aux[a.length - 1] = a[a.length - 1];
        return a.length - 1;
    }

    private static int less(Comparable a, Comparable b) {
        return b.compareTo(a);
    }

    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        // merge
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (less(aux[i], aux[j]) >= 0) a[k] = aux[i++];
            else a[k] = aux[j++];
        }
    }

    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i - 1]) > 0) return false;
        return true;
    }

    public static void main(String[] args) {
        Integer[] a = {4, 5, 6, 1, 2, 3};
        Sort(a);
        for (int x : a)
            StdOut.println(x);
    }
}
