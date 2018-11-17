package chapter2_2;

import edu.princeton.cs.algs4.StdRandom;

public class MergeSort_SWRole {
    public static void Sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        for (int i = 0; i < a.length; i++)
            aux[i] = a[i];
        Sort(a, aux, 0, a.length - 1);
    }

    private static void Sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (lo >= hi) return;
        int mid = (lo + hi) / 2;
        Sort(aux, a, lo, mid);
        Sort(aux, a, mid + 1, hi);
        merge(aux, a, lo, mid, hi);
    }

    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) aux[k] = a[j++];
            else if (j > hi) aux[k] = a[i++];
            else if (less(a[i], a[j]) >= 0) aux[k] = a[i++];
            else aux[k] = a[j++];
        }
    }

    private static boolean isSorted(Comparable[] arr, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(arr[i - 1], arr[i]) < 0) return false;
        return true;
    }

    private static int less(Comparable a, Comparable b) {
        return b.compareTo(a);
    }

    public static void main(String[] args) {
        for (int n = 8; n < 1000; n++) {
            Double[] a = new Double[n];
            for (int i = 0; i < n; i++)
                a[i] = StdRandom.uniform();
            Sort(a);
            assert isSorted(a, 0, n - 1);
        }
    }
}
