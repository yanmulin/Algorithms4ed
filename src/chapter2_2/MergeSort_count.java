package chapter2_2;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.awt.*;

public class MergeSort_count {
    private static int access = 0;
    private static int cmp = 0;

    public static void UBSort(Comparable[] a) {
        access = 0;
        cmp = 0;
        Comparable[] aux = new Comparable[a.length];
        UBSort(a, aux, 0, a.length - 1);
    }

    public static void UBSortPlus(Comparable[] a) {
        access = 0;
        cmp = 0;
        Comparable[] aux = new Comparable[a.length];
        UBSort(a, aux, 0, a.length - 1);
    }

    public static void BUSort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        int n = a.length;
        access = 0;
        cmp = 0;
        for (int sz = 1; sz < n; sz += sz) {
            for (int lo = 0; lo < n - sz; lo += sz + sz)
                merge(a, aux, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, n - 1));
        }
    }

    private static void UBSort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (lo >= hi) return;
        int mid = (lo + hi) / 2;
        UBSort(a, aux, lo, mid);
        UBSort(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid, hi);
    }

    private static void UBSortPlus(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (lo >= hi) return;
        int mid = (lo + hi) / 2;
        UBSort(a, aux, lo, mid);
        UBSort(a, aux, mid + 1, hi);
        if (less(a[mid], a[mid + 1]) >= 0) return;
        merge(a, aux, lo, mid, hi);
    }

    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        assert isSorted(a, lo, mid);
        assert isSorted(a, mid + 1, hi);
        // copy
        for (int i = lo; i <= hi; i++) {
            aux[i] = a[i];
            access += 2;
        }

        // merge
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                a[k] = aux[j++];
                access += 2;
            } else if (j > hi) {
                a[k] = aux[i++];
                access += 2;
            } else if (less(aux[j], aux[i]) > 0) {
                a[k] = aux[j++];
                access += 4;
            } else {
                a[k] = aux[i++];
                access += 4;
            }
            cmp++;
        }
        assert isSorted(a, lo, hi);
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
        StdDraw.setXscale(0.0, 1.0);
        StdDraw.setYscale(0.0, 20.0);
        StdDraw.setPenRadius(0.01);
        for (int n = 100; n <= 4000; n *= 2) {
            Double[] a = new Double[n];
            double x = Math.log(n) / Math.log(10.0) / 5.0 - 0.2;
            double th = 6 * n * Math.log(n) / Math.log(2.0);
            double yth = Math.log(th);
            StdOut.printf("%d size, theoratically %.1f accesses, ", n, th);
            StdDraw.setPenColor(Color.BLACK);
            StdDraw.point(x, yth);

            for (int i = 0; i < n; i++)
                a[i] = StdRandom.uniform();
            BUSort(a);
            StdOut.printf("Bottom-up %d, ", access);
            double ybu = Math.log(access);
            StdDraw.setPenColor(Color.RED);
            StdDraw.point(x, ybu);

            for (int i = 0; i < n; i++)
                a[i] = StdRandom.uniform();
            UBSort(a);
            StdOut.printf("Up-Bottom %d, ", access);
            double yub = Math.log(access);
            StdDraw.setPenColor(Color.GREEN);
            StdDraw.point(x, yub);

            UBSortPlus(a);
            StdOut.printf("Up-Bottom-Plus %d.\n", access);

        }
        StdDraw.show();
    }
}
