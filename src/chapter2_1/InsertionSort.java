package chapter2_1;

import edu.princeton.cs.algs4.StdOut;

public class InsertionSort {
    public static void Sort(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            for (int j = i; j > 0; j--) {
                if (less(a[j], a[j - 1]) > 0)
                    exch(a, j - 1, j);
                else break;
            }
        }
    }

    private static int less(Comparable a, Comparable b) {
        return b.compareTo(a);
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i - 1], a[i]) < 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Integer[] a = {4, 2, 5, 1, 7, 3};
        StdOut.println(isSorted(a));
        Sort(a);
        StdOut.println(isSorted(a));
    }
}
