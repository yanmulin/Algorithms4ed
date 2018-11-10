package chapter2_1;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;


public class shuffle {
    public static void shuffle(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            int r = StdRandom.uniform(i + 1);
            exch(a, i, r);
        }
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {
        Integer[] a = {1, 2, 3, 4, 5, 6};
        shuffle(a);
        for (int i : a)
            StdOut.print(i + " ");
        StdOut.println();
    }
}
