package chapter2_1;

import edu.princeton.cs.algs4.StdOut;

public class ShellSort {
    public static void Sort(Comparable[] a) {
        int n = a.length;
        int h = 1;
        // 计算递增序列
        while (h < n / 3) h = h * 3 + 1;
        while (h >= 1) {
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h && less(a[j], a[j - h]) >= 0; j -= h)
                    exch(a, j, j - h);
            }
            h /= 3;
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
        Integer[] a = {2, 5, 3, 4, 8, 6};
        StdOut.println(isSorted(a));
        Sort(a);
        StdOut.println(isSorted(a));
    }
}
