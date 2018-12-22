package chapter2_3;

import chapter1_3.Stack;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

public class QuickSortNonRecursive {
    public static void Sort(Comparable[] a) {
        StdRandom.shuffle(a);
        Stack<Integer> stk = new Stack<Integer>();
        stk.push(a.length - 1);
        stk.push(0);
        while (!stk.isEmpty()) {
            int lo = stk.pop(), hi = stk.pop();
            if (lo >= hi) continue;
            int mid = Partition(a, lo, hi);
            if (mid - lo > hi - mid + 1) {
                stk.push(mid - 1);
                stk.push(lo);
                stk.push(hi);
                stk.push(mid + 1);
            } else {
                stk.push(hi);
                stk.push(mid + 1);
                stk.push(mid - 1);
                stk.push(lo);
            }
        }
    }

    private static int Partition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        while (true) {
            while (less(a[++i], a[lo]) > 0)
                if (i >= hi) break;
            while (less(a[lo], a[--j]) > 0)
                if (j <= lo) break;
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    private static int less(Comparable a, Comparable b) {
        return b.compareTo(a);
    }

    private static void exch(Comparable a[], int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
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
    }
}
