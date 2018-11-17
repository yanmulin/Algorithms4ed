package chapter2_2.PatternRecognition;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {
    private LineSegment[] lines;

    public BruteCollinearPoints(Point[] points) {    // finds all line segments containing 4 points
        if (points == null)
            throw new java.lang.IllegalArgumentException();
        int n = points.length;
        for (int i = 0; i < n; i++) {
            if (points[i] == null)
                throw new java.lang.IllegalArgumentException();
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (points[i].compareTo(points[j]) == 0)
                    throw new java.lang.IllegalArgumentException();
            }
        }
        Point[][] record = new Point[n][2];
        int sz = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
//                StdOut.print(points[i] + " " + points[j] + " ");
                boolean findnew = false;
                double k1 = points[i].slopeTo(points[j]);

                // need 2 more collinear points
                for (int k = j + 1; k < n; k++) {
                    double k2 = points[i].slopeTo(points[k]);
                    if (k1 != k2) continue;
                    for (int l = k + 1; l < n; l++) {
                        double k3 = points[i].slopeTo(points[l]);
                        if (k3 == k2) {
                            findnew = true;
                            // StdOut.print(points[k] + " " + points[l] + " ");
                        }
                    }
                    if (findnew) break;
                }

                // check duplicate slope
                for (int k = 0; k < sz; k++) {
                    double k2 = record[k][0].slopeTo(record[k][1]);
                    if (points[i].slopeTo(points[j]) == k2 &&
                            (points[j].slopeTo(record[k][0]) == k2 ||
                                    points[j].slopeTo(record[k][1]) == k2)) {
                        if (record[k][0].compareTo(points[i]) > 0)
                            record[k][0] = points[i];
                        else if (record[k][1].compareTo(points[i]) < 0)
                            record[k][1] = points[i];

                        if (record[k][0].compareTo(points[j]) > 0)
                            record[k][0] = points[j];
                        else if (record[k][1].compareTo(points[j]) < 0)
                            record[k][1] = points[j];

                        findnew = false;
//                        StdOut.println();
//                        StdOut.print("Hello " + k1 + record[k][0] + " " + record[k][1]);
                        break;
                    }
                }

                if (findnew) {
//                    StdOut.p rintln(points[i] + " " + points[j]);
                    if (points[i].compareTo(points[j]) < 0) {
                        record[sz][0] = points[i];
                        record[sz][1] = points[j];
                    } else {
                        record[sz][0] = points[j];
                        record[sz][1] = points[i];
                    }
                    sz++;
                }
//                StdOut.println(sz);
            }
        }

        lines = new LineSegment[sz];
        for (int i = 0; i < sz; i++) {
            lines[i] = new LineSegment(record[i][0], record[i][1]);
        }

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
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
//        StdDraw.setXscale(0, 32767);
//        StdDraw.setYscale(0, 32767);
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
//            points[i].draw();
        }
        BruteCollinearPoints col = new BruteCollinearPoints(points);
        StdOut.println(col.numberOfSegments());
        LineSegment[] lines = col.segments();
        for (LineSegment l : lines) {
            StdOut.println(l);
//            l.draw();
        }
    }
}
