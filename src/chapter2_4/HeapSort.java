package chapter2_4;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class HeapSort {
    public static void Sort(Comparable[] a) {
        int N = a.length - 1;
        for (int i = N / 2; i >= 1; i--)
            sink(a, N, i);
        while (N > 1) {
            exch(a, N--, 1);
            sink(a, N, 1);
        }
    }

    private static void sink(Comparable[] a, int N, int i) {
//        int N = a.length - 1;
        while (i * 2 <= N) {
            int j = i * 2;
            if (j + 1 <= N && less(a[j], a[j + 1])) j++;
            if (less(a[j], a[i])) break;
            exch(a, i, j);
            i = j;
        }
    }

    private static boolean less(Comparable v, Comparable w) {
        return w.compareTo(v) > 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static boolean isSorted(Comparable[] a) {
        int n = a.length;
        for (int i = 2; i < n; i++)
            if (less(a[i], a[i - 1])) return false;
        return true;
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        Integer[] a = new Integer[n];
        for (int i = 1; i < n; i++) {
            a[i] = StdRandom.uniform(100);
//            StdOut.print(a[i] + " ");
        }
//        StdOut.println();
        Sort(a);
//        for (int i = 1; i < n; i++)
//            StdOut.print(a[i] + " ");
//        StdOut.println();
        StdOut.println(isSorted(a));
    }
}
