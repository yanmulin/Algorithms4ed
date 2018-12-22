package chapter2_4;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

public class DynamicMedianFinding {
    private MaxPQ<Integer> maxPQ;
    private MinPQ<Integer> minPQ;
    private int N;
    private double med;

    public DynamicMedianFinding() {
        maxPQ = new MaxPQ<>(5);
        minPQ = new MinPQ<>(5);
        N = 0;
    }

    public void insert(int v) {
        if (N == 0) maxPQ.insert(v);
        else if (v > med) minPQ.insert(v);
        else maxPQ.insert(v);
        N++;
        if (minPQ.size() > maxPQ.size())
            maxPQ.insert(minPQ.delMin());
        else if (maxPQ.size() > minPQ.size() + 1)
            minPQ.insert(maxPQ.delMax());
//        StdOut.println("Max-size: " + maxPQ.size() + ", Min-size: " + minPQ.size());
        if (N % 2 == 0)
            med = (maxPQ.max() + minPQ.min()) / 2.0;
        else
            med = maxPQ.max();
//        StdOut.println(N + ": " + med);
    }

    public double delMedian() {
        double v = med;
        if (N % 2 == 0) {
            maxPQ.delMax();
            minPQ.delMin();
            N -= 2;
        } else {
            maxPQ.delMax();
            N--;
        }
        med = (maxPQ.max() + minPQ.min()) / 2.0;
        return v;
    }

    public double median() {
        return med;
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        for (int i = 1; i < n; i++) {
            int[] a = new int[i];
            DynamicMedianFinding pq = new DynamicMedianFinding();
            for (int j = 0; j < i; j++) {
                int r = StdRandom.uniform(100);
                a[j] = r;
                pq.insert(r);
            }
            Arrays.sort(a);
            double ret = pq.median();
            double median = a.length % 2 == 0 ? (a[i / 2] + a[i / 2 - 1]) / 2.0 : (double) a[i / 2];
            if (ret - median > 1.0e-6) {
                StdOut.printf("when n=%d failed. actual = %f, expected = %f\n", i, ret, median);
            }
        }
    }
}
