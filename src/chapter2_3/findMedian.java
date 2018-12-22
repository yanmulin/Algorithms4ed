package chapter2_3;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

public class findMedian {
    public static double find(int[] a, int[] b) {
        int m = a.length, n = b.length;
        if (m > n) return find(b, a);
        int iMin = 0, iMax = m;
        int halfLen = (m + n + 1) / 2;
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            int j = halfLen - i;
            if (i > iMin && a[i - 1] > b[j]) iMax = i - 1;
            else if (i < iMax && b[j - 1] > a[i]) iMin = i + 1;
            else {
                int leftMax = 0;
                if (j == 0) leftMax = a[i - 1];
                else if (i == 0) leftMax = b[j - 1];
                else leftMax = a[i - 1] < b[j - 1] ? b[j - 1] : a[i - 1];
                if ((m + n) % 2 == 1) return leftMax;

                int rightMin = 0;
                if (j == n) rightMin = a[i];
                else if (i == m) rightMin = b[j];
                else rightMin = a[i] > b[j] ? b[j] : a[i];

                return (leftMax + rightMin) / 2.0;
            }
        }
        return 0.0;
    }

    public static double merge(int[] a, int[] b) {
        int m = a.length, n = b.length;
        int k = (m + n) % 2 == 0 ? (m + n + 1) / 2 + 1 : (m + n + 1) / 2;
        int[] aux = new int[k];
        int v = m - 1, w = n - 1;
        for (int i = k - 1; i >= 0; i--) {
            if (w < 0) aux[i] = a[v--];
            else if (v < 0) aux[i] = b[w--];
            else if (a[v] >= b[w]) aux[i] = a[v--];
            else aux[i] = b[w--];
        }
//        for (int x : aux)
//            StdOut.println(x);
        if ((m + n) % 2 == 0) return (aux[0] + aux[1]) / 2.0;
        else return (double) aux[0];
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int n1 = StdRandom.uniform(n / 2, n);
        int n2 = n - n1;
        int[] a = new int[n1];
        int[] b = new int[n2];
        for (int i = 0; i < n1; i++)
            a[i] = StdRandom.uniform(100);
        for (int i = 0; i < n2; i++)
            b[i] = StdRandom.uniform(100);
        Arrays.sort(a);
        StdOut.print("a:");
        for (int x : a)
            StdOut.print(x + " ");
        StdOut.println();
        Arrays.sort(b);
        StdOut.print("b:");
        for (int x : b)
            StdOut.print(x + " ");
        StdOut.println();
        StdOut.println(find(a, b));
        StdOut.println(merge(a, b));

    }
}
