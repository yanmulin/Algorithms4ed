package chapter2_2;

import chapter1_3.Queue;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Comparator;

public class MergeSort_Queue {
    public static void Sort(Comparable[] a, Comparator c) {
        Queue<Queue> qq = new Queue<>();
        for (int i = 0; i < a.length; i++) {
            Queue<Comparable> q = new Queue<>();
            q.enqueue(a[i]);
            assert !q.isEmpty();
            qq.enqueue(q);
        }
        while (qq.size() != 1) {
            Queue<Comparable> q1 = qq.dequeue();
            Queue<Comparable> q2 = qq.dequeue();

            assert !q1.isEmpty();
            assert !q2.isEmpty();
            q1.Merge(q2, c);
            qq.enqueue(q1);
        }
        Queue<Comparable> q = qq.dequeue();
        for (int i = 0; !q.isEmpty(); i++) {
            a[i] = q.dequeue();
        }
    }

    private static int less(Comparable a, Comparable b) {
        return b.compareTo(a);
    }

    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i - 1]) > 0)
                return false;
        return true;
    }

    private static class DoubleComparator implements Comparator<Double> {
        public int compare(Double x, Double y) {
            if (x > y) return 1;
            else if (x == y) return 0;
            else return -1;
        }
    }

    public static void main(String[] args) {
        DoubleComparator c = new DoubleComparator();
        Double[] a = new Double[100];
        for (int i = 0; i < 100; i++)
            a[i] = StdRandom.uniform();
        Sort(a, c);
        StdOut.println(isSorted(a, 0, a.length - 1));
    }
}
