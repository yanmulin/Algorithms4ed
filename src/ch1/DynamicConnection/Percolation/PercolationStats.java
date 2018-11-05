package ch1.DynamicConnection.Percolation;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {
    private final int num;
    private static final double CONFIDENCE_95 = 1.96;
    private final double stat_mean;
    private final double stat_stddev;

    public PercolationStats(int n, int trials) {
        double[] data = new double[trials];
        for (int t = 0; t < trials; t++) {
            data[t] = trial(n);
        }
        stat_mean = StdStats.mean(data);
        stat_stddev = StdStats.stddev(data);
        num = trials;
    }

    private double trial(int n) {
        Percolation p = new Percolation(n);
        int[] notOpen = new int[n * n];
        for (int i = n * n - 1; i >= 0; i--) {
            notOpen[i] = i;
        }
        StdRandom.shuffle(notOpen);
        for (int i = n * n - 1; i >= 0; i--) {
            int row = notOpen[i] / n + 1;
            int col = notOpen[i] % n + 1;
            assert p.isFull(row, col);
            p.open(row, col);
            if (p.percolates()) break;
        }
        return (double) p.numberOfOpenSites() / (n * n);
    }

    public double mean() {
        return stat_mean;
    }

    public double stddev() {
        return stat_stddev;
    }

    public double confidenceLo() {
        return stat_mean - stat_stddev * CONFIDENCE_95 / Math.sqrt(num);
    }

    public double confidenceHi() {
        return stat_mean + stat_stddev * CONFIDENCE_95 / Math.sqrt(num);
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        Stopwatch w = new Stopwatch();
        PercolationStats stat = new PercolationStats(n, trials);
        double t = w.elapsedTime();
        StdOut.println("mean                   = " + stat.mean());
        StdOut.println("stddev                 = " + stat.stddev());
        StdOut.println("95% confidence inteval = [" + stat.confidenceLo()
                + ", " + stat.confidenceHi() + "]");
        StdOut.println("elapsed time           = " + t + "s");
    }
}
