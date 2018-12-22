package chapter2_3;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

public class FindKth {
    public static int find(int[] a, int[] b, int k) {
        int m = a.length, n = b.length;
        if (m > n) return find(b, a, k);
        int iMax = m, iMin = 0, klen = m + n - k;
        while (iMax >= iMin) {
            int i = (iMin + iMax) / 2;
            int j = klen - i;
//            StdOut.println(i + " " + j);
            if (j > n) iMin = i + 1;
            else if (j < 0) iMax = i - 1;
            else if (i > iMin && j < n && a[i - 1] > b[j]) iMax = i - 1;
            else if (j > 0 && i < iMax && a[i] < b[j - 1]) iMin = i + 1;
            else {
                if (j >= n || j < 0) return a[i];
                else if (i >= m || i < 0) return b[j];
                else return a[i] > b[j] ? b[j] : a[i];
            }
        }
        return 0;
    }

    public static int merge(int[] a, int[] b, int k) {
        int[] aux = new int[k];
        int i = a.length - 1, j = b.length - 1;
        while (--k >= 0) {
            if (i < 0) aux[k] = b[j--];
            else if (j < 0) aux[k] = a[i--];
            else if (a[i] > b[j]) aux[k] = a[i--];
            else aux[k] = b[j--];
        }
        return aux[0];
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        boolean failed = false;
        for (int t = 0; !failed && t < 10000; t++) {
            int n1 = StdRandom.uniform(n / 2);
            int n2 = n - n1;
            int[] a = new int[n1];
            for (int i = 0; i < n1; i++)
                a[i] = StdRandom.uniform(100);
            int[] b = new int[n2];
            for (int i = 0; i < n2; i++)
                b[i] = StdRandom.uniform(100);
            Arrays.sort(a);
            Arrays.sort(b);
//            for (int x : a)
//                StdOut.print(x + " ");
//            StdOut.println();
//            for (int x : b)
//                StdOut.print(x + " ");
//            StdOut.println();

            for (int i = 1; i <= n; i++) {
//                StdOut.println("Find " + i);
                if (merge(a, b, i) != find(a, b, i)) {
                    StdOut.println("Failed.");
                    failed = true;
                    break;
                }
            }
        }
    }
}
