package chapter2_1;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

public class SortCompare {
    public static double time(String alg, Comparable[] a) {
        Stopwatch timer = new Stopwatch();
        if (alg.equals("Insertion")) InsertionSort.Sort(a);
        else if (alg.equals("Selection")) SelectionSort.Sort(a);
        else if (alg.equals("Shell")) ShellSort.Sort(a);
        else if (alg.equals("Shell_experiment")) ShellSort_experiment.Sort(a);
        else if (alg.equals("Shell_count")) ShellSort_count.Sort(a);
        return timer.elapsedTime();
    }

    public static double timeRandomInput(String alg, int N, int T) {
        double total = 0.0;
        for (int i = 0; i < T; i++) {
            Double[] a = new Double[N];
            for (int j = 0; j < N; j++)
                a[j] = StdRandom.uniform();
            total += time(alg, a);
            assert InsertionSort.isSorted(a);
        }
        return total;
    }

    public static void main(String[] args) {
        String alg1 = args[0];
        String alg2 = args[1];
        int N = Integer.parseInt(args[2]);
        int T = Integer.parseInt(args[3]);
        double t1 = timeRandomInput(alg1, N, T);
        double t2 = timeRandomInput(alg2, N, T);
        StdOut.printf("For %d random Doubles\n\t%s is", N, alg1);
        StdOut.printf(" %.1f times faster than %s\n", t2 / t1, alg2);
    }
}
