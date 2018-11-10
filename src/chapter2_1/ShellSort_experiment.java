package chapter2_1;

import edu.princeton.cs.algs4.StdOut;

public class ShellSort_experiment {
    private static int[] t;

    public static void Sort(Comparable[] a) {
        long n = a.length;
        int h = t.length - 1;
        while (t[h] > n / 3 && h > 1) h--;
        while (h > 0) {
            int gap = t[h];
            for (int i = gap; i < n; i++)
                for (int j = i; j >= h; j -= h)
                    exch(a, j, j - h);
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
        Double[] times = new Double[100];
        Double[] vals = new Double[100];
        for (int i = 0; i < 10; i++) {
            vals[i] = 1.1 + i * 0.4;
            t = new int[150];
            vals[0] = vals[i];
            for (int j = 1; j < 150; j++)
                t[j] = (int) Math.floor(t[j - 1] * vals[i]);
            times[i] = SortCompare.timeRandomInput("Shell_experiment", 1000000, 1);
            StdOut.printf("%.1f: %.1fs\n", vals[i], times[i]);
        }
    }
}
