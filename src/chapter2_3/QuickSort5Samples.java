package chapter2_3;

import chapter2_1.SelectionSort;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

public class QuickSort5Samples {
    private static final int CUTOFF = 6;

    public static void Sort(Comparable[] a) {
        StdRandom.shuffle(a);
        Sort(a, 0, a.length - 1);
    }

    public static void Sort(Comparable[] a, int lo, int hi) {
        if (hi - lo + 1 <= CUTOFF) {
            SelectionSort.Sort(a, lo, hi);
            return;
        }
        // randomly sample 5 elements
        int[] perm = StdRandom.permutation(hi - lo + 1, 5);
        assert perm.length == 5;
        int medium = Medium(a, perm, lo);
        exch(a, lo, medium);
        int mid = Partition(a, lo, hi);
        Sort(a, lo, mid - 1);
        Sort(a, mid + 1, hi);
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

    private static int Medium(Comparable[] a, int[] perm, int base) {
        Integer[] _perm = new Integer[perm.length];
        for (int i = 0; i < perm.length; i++)
            _perm[i] = perm[i];
        int k = perm.length / 2;
        int lo = 0, hi = perm.length - 1;
        while (lo <= hi) {
            int i = lo, j = hi + 1;
            while (true) {
                while (less(a[base + _perm[++i]], a[base + _perm[lo]]) > 0)
                    if (i >= hi) break;
                while (less(a[base + _perm[lo]], a[base + _perm[--j]]) > 0)
                    if (j <= lo) break;
                if (i >= j) break;
                exch(_perm, i, j);
            }
            exch(_perm, j, lo);
            int mid = j;
            if (mid == k) return base + _perm[mid];
            else if (mid > k) hi = mid - 1;
            else lo = mid + 1;
        }
        assert false;
        return -1;
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
//        Integer[] a = new Integer[100];
//        for (int i = 0; i < 100; i++)
//            a[i] = StdRandom.uniform(10);
//        Sort(a);
//        StdOut.println(isSorted(a));
//        for (int x : a)
//            StdOut.print(x + " ");
//        StdOut.println();
        Integer[] a = new Integer[n];
        int[] perm = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = StdRandom.uniform(n);
            perm[i] = i;
        }
        Stopwatch watch = new Stopwatch();
        StdOut.print(a[Medium(a, perm, 0)] + " ");
        double t = watch.elapsedTime();
        Sort(a);
        StdOut.println(a[a.length / 2]);
        StdOut.println(t + "s");
    }
}
