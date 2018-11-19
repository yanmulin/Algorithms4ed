package chapter2_2.PatternRecognition;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {
    private Node first;
    private int lineNumber;

    private class Node {
        Point[] data;
        Node next;
    }

    public BruteCollinearPoints(Point[] points) {    // finds all line segments containing 4 points
        if (points == null)
            throw new java.lang.IllegalArgumentException();
        int n = points.length;
        for (int i = 0; i < n; i++) {
            if (points[i] == null)
                throw new java.lang.IllegalArgumentException();
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (points[i].compareTo(points[j]) == 0)
                    throw new java.lang.IllegalArgumentException();
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
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
                for (Node p = first; p != null; p = p.next) {
                    double k2 = p.data[0].slopeTo(p.data[1]);
                    if (k1 == k2 &&
                            (points[j].slopeTo(p.data[0]) == k2 ||
                                    points[j].slopeTo(p.data[1]) == k2)) {
                        if (p.data[0].compareTo(points[i]) > 0)
                            p.data[0] = points[i];
                        else if (p.data[1].compareTo(points[i]) < 0)
                            p.data[1] = points[i];

                        if (p.data[0].compareTo(points[j]) > 0)
                            p.data[0] = points[j];
                        else if (p.data[1].compareTo(points[j]) < 0)
                            p.data[1] = points[j];

                        findnew = false;
//                        StdOut.println();
//                        StdOut.print("Hello " + k1 + p.data[0] + " " + p.data[1]);
                        break;
                    }
                }

                if (findnew) {
//                    StdOut.p rintln(points[i] + " " + points[j]);
                    if (points[i].compareTo(points[j]) < 0) {
                        addLine(points[i], points[j]);
                    } else {
                        addLine(points[j], points[i]);
                    }
                    lineNumber++;
                }
//                StdOut.println(sz);
            }
        }

    }

    private void addLine(Point a, Point b) {
        Node oldfirst = first;
        first = new Node();
        first.data = new Point[2];
        first.data[0] = a;
        first.data[1] = b;
        first.next = oldfirst;
    }

    public int numberOfSegments() {        // the number of line segments
        return lineNumber;
    }

    public LineSegment[] segments() {                // the line segments
        LineSegment[] lines = new LineSegment[lineNumber];
        Node p = first;
        for (int i = 0; i < lineNumber; i++) {
            lines[i] = new LineSegment(p.data[0], p.data[1]);
            p = p.next;
        }
        return lines;
    }

//     public static void main(String[] args) {
//         In in = new In(args[0]);
//         int n = in.readInt();
//         Point[] points = new Point[n];
// //        StdDraw.setXscale(0, 32767);
// //        StdDraw.setYscale(0, 32767);
//         for (int i = 0; i < n; i++) {
//             int x = in.readInt();
//             int y = in.readInt();
//             points[i] = new Point(x, y);
// //            points[i].draw();
//         }
//         BruteCollinearPoints col = new BruteCollinearPoints(points);
//         StdOut.println(col.numberOfSegments());
//         LineSegment[] lines = col.segments();
//         for (LineSegment l : lines) {
//             StdOut.println(l);
// //            l.draw();
//         }
//     }

    public static void main(String[] args) {
        In file = new In(args[0]);
        int n = file.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = file.readInt();
            int y = file.readInt();
            points[i] = new Point(x, y);
        }
        BruteCollinearPoints c = new BruteCollinearPoints(points);
        StdOut.println(c.numberOfSegments());
        LineSegment[] lines = c.segments();
        for (LineSegment line : lines) {
            StdOut.println(line);
        }
    }
}
