package chapter2_3;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

public class QuickSortQuick3Way {
    public static void Sort(Comparable[] a) {
        //StdRandom.shuffle(a);
        Sort(a, 0, a.length - 1);
    }

    private static void Sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi) return;
        int i = lo, j = hi;
        int p = lo, q = hi;
        while (i <= j) {
            int cmp1 = a[i].compareTo(a[lo]);
            int cmp2 = a[j].compareTo(a[lo]);
            if (cmp1 == 0) exch(a, i++, p++);
            else if (cmp1 < 0) i++;
            else if (cmp2 == 0) exch(a, j--, q--);
            else if (cmp2 > 0) j--;
            else if (cmp1 > 0 && cmp2 < 0) exch(a, i++, j--);
        }
        while (--p >= lo) exch(a, p, j--);
        while (++q <= hi) exch(a, q, i++);
        Sort(a, lo, j);
        Sort(a, i, hi);
    }

    private static void Partition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi;
        int p = lo, q = hi;
        while (i <= j) {
            int cmp1 = a[i].compareTo(a[lo]);
            int cmp2 = a[j].compareTo(a[lo]);

            if (cmp1 == 0) exch(a, i++, p++);
            else if (cmp1 < 0) i++;
            else if (cmp2 == 0) exch(a, j--, q--);
            else if (cmp2 > 0) j--;
            else if (cmp1 > 0 && cmp2 < 0) exch(a, i++, j--);
        }
        for (Comparable x : a)
            StdOut.print(x + " ");
        StdOut.println();
        while (--p >= lo) exch(a, p, j--);
        while (++q <= hi) exch(a, q, i++);
    }

    private static void exch(Comparable a[], int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static int less(Comparable a, Comparable b) {
        return b.compareTo(a);
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1]) > 0)
                return false;
        }
        return true;
    }


    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        Integer[] a = new Integer[n];
        for (int i = 0; i < n; i++)
            a[i] = StdRandom.uniform(10);

        Stopwatch watch = new Stopwatch();
        Sort(a);
        double t = watch.elapsedTime();
        StdOut.println(isSorted(a));
        StdOut.println(t + "s");
//
//        Partition(a, 0, a.length - 1);
//        for (Integer x : a)
//            StdOut.print(x + " ");
//        StdOut.println();
    }
}
