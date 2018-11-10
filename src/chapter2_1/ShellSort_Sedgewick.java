package chapter2_1;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class ShellSort_Sedgewick {
    public static void Sort(Comparable[] a) {
        int n = a.length;
        int[] seq = {0, 1, 5, 19, 41, 109, 209, 505, 929, 2161, 3905, 8929, 16001,
                36289, 64769, 146305, 260609,};
        int h = seq.length - 1;
        while (seq[h] > n / 3 && seq[h] > 1) h--;
        while (seq[h] >= 1) {
            int gap = seq[h];
            for (int i = gap; i < n; i++) {
                for (int j = i; j >= gap && less(a[j], a[j - gap]) > 0; j -= gap)
                    exch(a, j, j - gap);
            }
            h--;
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
        Double[] a = new Double[10000];
        for (int i = 0; i < 10000; i++)
            a[i] = StdRandom.uniform();
        StdOut.println(isSorted(a));
        Sort(a);
        StdOut.println(isSorted(a));
    }
}
