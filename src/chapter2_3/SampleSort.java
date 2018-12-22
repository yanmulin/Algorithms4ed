package chapter2_3;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

public class SampleSort {
    private static final int k = 8;

    public static void Sort(Comparable[] a) {
        int n = a.length;
        if (n < Math.pow(2, k + 1)) {
            QuickSort.Sort(a);
            return;
        }
        StdRandom.shuffle(a);
        int samplelo = 0, samplehi = (int) Math.pow(2, k) - 2;
        QuickSort.Sort(a, 0, samplehi);
        int mid = (samplelo + samplehi) / 2;
        int hi = n - 1, lo = mid + 1;
        for (int i = samplehi; i > mid; i--)
            exch(a, i, hi--);
        Sort(a, samplelo, lo, hi, samplehi);
        assert isSorted(a);
    }

    private static void Sort(Comparable[] a, int samplelo, int lo, int hi, int samplehi) {
        if (lo >= hi) return;
        int mid = Partition(a, lo, hi);
        if (lo - samplelo > 1) {
            int p = lo - 1, v = mid - 1;
            for (int i = (samplelo - lo) / 2; i > 0; i--)
                exch(a, p--, v--);
            Sort(a, samplelo, p, v, mid - 1);
        } else {
            QuickSort.Sort(a, samplelo, mid - 1);
        }

        if (samplehi - hi > 1) {
            int p = hi + 1, v = mid + 1;
            for (int i = (samplehi - hi) / 2; i > 0; i--)
                exch(a, p++, v++);
            Sort(a, mid + 1, v, p, samplehi);
        } else {
            QuickSort.Sort(a, mid + 1, samplehi);
        }
    }

    private static int Partition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        while (true) {
            while (less(a[++i], a[lo]))
                if (i >= hi) break;
            while (less(a[lo], a[--j]))
                if (j <= lo) break;
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, j, lo);
        return j;
    }

    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
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
