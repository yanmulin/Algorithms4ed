package chapter2_1;

import edu.princeton.cs.algs4.StdOut;

public class ShellSort_count {
    private static int cmpcnt;

    public static void Sort(Comparable[] a) {
        int n = a.length;
        cmpcnt = 0;
        int h = 1;
        while (h < n / 3) h = h * 3 + 1;
        while (h >= 1) {
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h; j -= h) {
                    cmpcnt++;
                    if (less(a[j], a[j - 1]) > 0)
                        exch(a, j, j - h);
                    else break;
                }
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
        int n = 10;
        for (int i = 0; i < 3; i++) {
            //SortCompare.timeRandomInput("Shell_count", n, 10000);
            StdOut.printf("%d random Doubles, compares %d times, ratio: %.1f\n", n, cmpcnt, cmpcnt * 1.0 / n);
            n *= 10;
        }
    }
}
