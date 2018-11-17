package chapter2_1;

import edu.princeton.cs.algs4.StdOut;

public class DutchNationalFlags {
    // 假设a[]只有0, 1, 2
    public static void Sort(int[] a) {
        int lo = 0, hi = a.length - 1;
        int mid = 0;
        while (mid < hi) {
            switch (a[mid]) {
                case 0:
                    exch(a, lo, mid);
                    lo++;
                    mid++;
                    break;
                case 1:
                    mid++;
                    break;
                case 2:
                    exch(a, mid, hi);
                    hi--;
                    break;
                default:
                    assert false;
                    break;
            }
        }
    }

    private static void exch(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public static void main(String[] args) {
        int[] a = {0, 0, 2, 1, 0, 1, 1, 2, 0};
        Sort(a);
        for (int x : a)
            StdOut.print(x + " ");
        StdOut.println();
    }
}
