package chapter2_5;

import edu.princeton.cs.algs4.StdOut;

public class KendallTauDistance {
    public static int Count(int[] a, int[] b) {
        assert (a.length == b.length);
        int n = a.length;
        int[] ainv = new int[n];
        for (int i = 0; i < n; i++)
            ainv[a[i]] = i;
        int[] bnew = new int[n];
        for (int i = 0; i < n; i++)
            bnew[i] = ainv[b[i]];
        return inversionCount(bnew);
    }

    private static int inversionCount(int[] a) {
        int n = a.length;
        int[] aux = new int[n];
        int count = 0;
        for (int sz = 1; sz < n; sz += sz) {
            for (int lo = 0; lo < n; lo += sz + sz) {
                count += merge(a, aux, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, n - 1));
            }
        }
        assert isSorted(a);
        return count;
    }

    private static boolean isSorted(int[] a) {
        int n = a.length;
        for (int i = 1; i < n; i++)
            if (a[i] < a[i - 1]) return false;
        return true;
    }

    private static int merge(int[] a, int[] aux, int lo, int mid, int hi) {
        for (int i = lo; i <= hi; i++)
            aux[i] = a[i];
        int count = 0;
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (aux[i] <= aux[j]) a[k] = aux[i++];
            else {
                a[k] = aux[j++];
                count += mid - i + 1;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] a = {0, 3, 1, 6, 2, 5, 4};
        int[] b = {1, 0, 3, 6, 4, 2, 5};
        StdOut.println(Count(a, b));
    }
}
