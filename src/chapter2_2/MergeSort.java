package chapter2_2;

import edu.princeton.cs.algs4.StdOut;

public class MergeSort {
    public static void Sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        Sort(a, aux, 0, a.length - 1);
    }

    private static void Sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (hi <= lo) return;
        // if (hi - lo + 1 < 7){            // improvement 2
        //     InsertionSort(a, lo, hi);
        //     return ;
        // }
        int mid = (lo + hi) / 2;
        Sort(a, aux, lo, mid);
        Sort(a, aux, mid + 1, hi);
        // if (less(a[mid], a[mid+1]) > 0) return; // improvement 1
        Merge(a, aux, lo, hi);
    }

    private static void BottomUpSort(Comparable[] a, Comparable[] aux) {
        int n = a.length;
        for (int sz = 1; sz < n; sz = sz + sz) {
            for (int i = 0; i < n - sz; i += sz + sz) {
                Merge(a, aux, i, Math.min(i + sz + sz - 1, n - 1));
            }
        }
    }

    private static void Merge(Comparable[] a, Comparable[] aux, int lo, int hi) {
        int mid = (hi + lo) / 2;
        assert isSorted(a, lo, mid);
        assert isSorted(a, mid + 1, hi);

        // copy
        for (int i = lo; i <= hi; i++)
            aux[i] = a[i];

        // merge
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (less(aux[i], aux[j]) < 0) a[k] = aux[j++];
            else a[k] = aux[i++];
        }

        assert isSorted(a, lo, hi);

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
        Integer[] a = {4, 2, 3, 1, 6, 5};
        Sort(a);
        for (int x : a) {
            StdOut.print(x);
        }
        StdOut.println();
    }
}
