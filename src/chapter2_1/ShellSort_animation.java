package chapter2_1;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class ShellSort_animation {
    public static void Sort(Double[] a) {
        int n = a.length;
        int h = 1;
        SortAnimation.Initialize();
        // 计算递增序列
        while (h < n / 3) h = h * 3 + 1;
        while (h >= 1) {
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h; j -= h) {
                    SortAnimation.Draw(a, j, j - h);
                    if (less(a[j], a[j - h]) > 0)
                        exch(a, j, j - h);
                    else break;
                }
            }
            h /= 3;
        }
        SortAnimation.Draw(a);
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
        Double a[] = new Double[50];
        for (int i = 0; i < a.length; i++)
            a[i] = StdRandom.uniform();
        StdOut.println(isSorted(a));
        Sort(a);
        StdOut.println(isSorted(a));
    }
}
