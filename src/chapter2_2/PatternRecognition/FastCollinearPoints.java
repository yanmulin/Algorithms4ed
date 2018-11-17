package chapter2_2.PatternRecognition;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;
import java.util.Comparator;

public class FastCollinearPoints {
    private LineSegment[] lines;

    public FastCollinearPoints(Point[] points) {     // finds all line segments containing 4 or more points
        if (points == null)
            throw new java.lang.IllegalArgumentException();
        int n = points.length;
        for (int i = 0; i < n; i++) {
            if (points[i] == null)
                throw new java.lang.IllegalArgumentException();
        }
        Point[][] record = new Point[n * n][2];
        int sz = 0;

        Arrays.sort(points);
        for (int i = 1; i < n; i++) {
            if (points[i].compareTo(points[i - 1]) == 0) {
                throw new java.lang.IllegalArgumentException();
            }
        }
        for (int i = 0; i < n; i++) {
            Comparator<Point> c = points[i].slopeOrder();
//            StdOut.print(points[i] + ": ");
            Point[] slopes = new Point[n - i - 1];
            for (int j = i + 1; j < n; j++) {
                slopes[j - i - 1] = points[j];
            }
            Arrays.sort(slopes, 0, n - i - 1, c);
//            for (Point x : slopes) {
//                StdOut.print(x + " ");
//            }
//            StdOut.println();
            int k = 0, m = 0;
            while (k < n - i - 2 && m < n - i - 2) {
                for (; k < n - i - 2 && c.compare(slopes[k], slopes[k + 1]) != 0; k++) ;
                for (m = k; m < n - i - 2 && c.compare(slopes[m], slopes[m + 1]) == 0; m++) ;
//                StdOut.println(k + " " + m);
                if (m - k + 1 >= 3) {
                    // new collinear Points
                    int j;
                    for (j = 0; j < sz; j++) {
                        double s = record[j][0].slopeTo(record[j][1]);
                        if (s == points[i].slopeTo(slopes[m]) &&
                                (points[i].slopeTo(record[j][0]) == s ||
                                        points[i].slopeTo(record[j][1]) == s)) {
//                            StdOut.print(points[i] + " " + slopes[m] + " " + record[j][0] + " " + record[j][1]);
//                            StdOut.println(s + " " + points[i].slopeTo(slopes[m]) + " " + points[i].slopeTo(record[j][0]) + " " + points[i].slopeTo(record[j][1]));
                            break;
                        }
                    }
                    if (j == sz) {
                        StdOut.println(sz + " " + i + " " + m);
                        record[sz][0] = points[i];
                        record[sz][1] = slopes[m];
                        sz++;
                    }
                }
                k = m + 1;
            }
        }
        lines = new LineSegment[sz];
        for (int i = 0; i < sz; i++)
            lines[i] = new LineSegment(record[i][0], record[i][1]);
    }

    public int numberOfSegments() {        // the number of line segments
        return lines.length;
    }

    public LineSegment[] segments() {                // the line segments
        LineSegment[] result = new LineSegment[lines.length];
        for (int i = 0; i < lines.length; i++)
            result[i] = lines[i];
        return result;
    }

    public static void main(String[] args) {
//        In in = new In(args[0]);
//        int n = in.readInt();
        int n = 0;
        int m = 200;
        Point[] points = new Point[m];
        StdDraw.setXscale(0, 50);
        StdDraw.setYscale(0, 50);
//        for (int i = 0; i < n; i++) {
//            int x = in.readInt();
//            int y = in.readInt();
//            points[i] = new Point(x, y);
////            points[i].draw();
//        }
        for (int i = 0; i < 50.0; i++) {
            for (int j = 0; j < 50.0; j++) {
                if (StdRandom.bernoulli(m / (50.0 * 50.0))) {
                    points[n] = new Point(i, j);
                    points[n].draw();
                    n++;
                }
                if (n >= m) break;
            }
            if (n >= m) break;
        }
        FastCollinearPoints col = new FastCollinearPoints(points);
        StdOut.println(col.numberOfSegments());
        LineSegment[] lines = col.segments();
        for (LineSegment l : lines) {
            StdOut.println(l);
            l.draw();
        }
    }
}
