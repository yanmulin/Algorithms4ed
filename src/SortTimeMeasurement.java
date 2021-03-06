import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

public class TimeMeasurement {
    public static double time(int n, String alg) {
        Point[] points = new Point[n];

        int[] perm = StdRandom.permutation(grid * grid, n);
        assert (perm.length == n);
        for (int i = 0; i < perm.length; i++) {
            
        }
        Stopwatch sw = new Stopwatch();
        if (alg.equals("FastCollinearPoint")) {
            FastCollinearPoints c = new FastCollinearPoints(points);
        } else if (alg.equals("BruteCollinearPoint")) {
            BruteCollinearPoints c = new BruteCollinearPoints(points);
        }
        return sw.elapsedTime();
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int grid = Integer.parseInt(args[1]);
        double t1 = time(n, grid, "FastCollinearPoint");
        double t2 = time(n, grid, "BruteCollinearPoint");
        StdOut.printf("FastCollinearPoint: %.1f\nBruteCollinearPoint: %.1f\n", t1, t2);
    }
}
