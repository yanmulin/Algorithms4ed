package chapter2_1;

import edu.princeton.cs.algs4.StdOut;

public class SelectionSort {
    public static void Sort(Comparable[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (less(arr[j], arr[min]) > 0)
                    min = j;
            }
            exch(arr, min, i);
        }
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
        String[] a = {"ccc", "abb", "bbb", "aab", "bba"};
        for (String i : a)
            StdOut.print(i + " ");
        StdOut.println();
        StdOut.println(isSorted(a));
        Sort(a);
        StdOut.println(isSorted(a));

        Integer[] b = {4, 6, 1, 7, 3, 5};
        for (int i : b)
            StdOut.print(i + " ");
        StdOut.println();
        StdOut.println(isSorted(b));
        Sort(b);
        StdOut.println(isSorted(b));
    }
}
