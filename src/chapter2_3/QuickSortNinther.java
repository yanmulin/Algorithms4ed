package chapter2_3;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

public class QuickSortNinther {
    private static final int INSERTION_CUTOFF = 8;
    private static final int NINTHER_CUTOFF = 40;

    public static void Sort(Comparable[] a) {
        StdRandom.shuffle(a);
        Sort(a, 0, a.length - 1);
    }

    private static void Sort(Comparable[] a, int lo, int hi) {
        int n = hi - lo + 1;
        if (n < INSERTION_CUTOFF) {
            insertionSort(a);
            return;
        } else if (n < NINTHER_CUTOFF) {
            int m = medium3(a, lo, lo + n / 2, hi);
            exch(a, lo, m);
        } else {
            int esp = n / 8;
            int mid = lo + n / 2;
            int m1 = medium3(a, lo, lo + esp, lo + esp + esp);
            int m2 = medium3(a, mid - esp, mid, mid + esp);
            int m3 = medium3(a, hi - esp - esp, hi - esp, hi);
            int m = medium3(a, m1, m2, m3);
            exch(a, lo, m);
        }
        int i = lo, j = hi + 1;
        int p = lo, q = hi;
        while (true) {
            while (less(a[++i], a[lo]))
                if (i >= hi) break;
            while (less(a[lo], a[--j]))
                if (j <= lo) break;
            if (i == j && eq(a[i], a[lo]))
                exch(a, i, p++);
            if (i >= j) break;
            exch(a, i, j);
            if (eq(a[i], a[lo])) exch(a, i, p++);
            if (eq(a[j], a[lo])) exch(a, j, q--);
        }
        i = j + 1;
        for (int k = lo; k < p; k++) exch(a, j--, k);
        for (int k = hi; k > q; k--) exch(a, i++, k);
        Sort(a, lo, j);
        Sort(a, i, hi);
    }

    private static void insertionSort(Comparable[] a) {
        int n = a.length;
        for (int i = 1; i < n; i++) {
            for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) {
                exch(a, j, j - 1);
            }
        }
    }

    private static int medium3(Comparable[] a, int i, int j, int k) {
        return less(a[i], a[j]) ?
                (less(a[i], a[k]) ? k : (less(a[j], a[k]) ? j : k)) :
                (less(a[j], a[k]) ? j : (less(a[i], a[k]) ? k : i));
    }

    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    private static boolean eq(Comparable a, Comparable b) {
        return a.compareTo(b) == 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1]))
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        Integer[] a = new Integer[n];
        for (int i = 0; i < n; i++)
            a[i] = StdRandom.uniform(10);
        Stopwatch watch = new Stopwatch();
        Sort(a);
        double t = watch.elapsedTime();
        StdOut.println(isSorted(a));
        StdOut.println(t + "s");
    }
}
