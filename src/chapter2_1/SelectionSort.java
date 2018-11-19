package chapter2_1;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class SelectionSort {
    public static void Sort(Comparable[] a, int lo, int hi) {
        for (int i = lo; i <= hi; i++) {
            int min = i;
            for (int j = i + 1; j <= hi; j++)
                min = less(a[j], a[min]) > 0 ? j : min;
            exch(a, min, i);
        }
    }

    public static void Sort(Comparable[] arr) {
        Sort(arr, 0, arr.length - 1);
    }

    private static int less(Comparable c1, Comparable c2) {
        return c2.compareTo(c1);
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static boolean isSorted(Comparable[] arr) {
        for (int i = 1; i < arr.length; i++)
            if (less(arr[i - 1], arr[i]) < 0) return false;
        return true;
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        Double[] a = new Double[n];
        for (int i = 0; i < n; i++)
            a[i] = StdRandom.uniform();
        Sort(a);
        for (double x : a)
            StdOut.print(x + " ");
        StdOut.println();
        StdOut.println(isSorted(a));
    }
}
