package chapter2_2.PatternRecognition;

import java.util.Arrays;
import java.util.Comparator;

public class FastCollinearPoints {
    private Node first;
    private int lineNumber;

    private class Node {
        Point[] data;
        Node next;
    }

    public FastCollinearPoints(Point[] points) {     // finds all line segments containing 4 or more points
        if (points == null)
            throw new java.lang.IllegalArgumentException();
        int n = points.length;
        Point[] copy = new Point[n];
        for (int i = 0; i < n; i++) {
            if (points[i] == null)
                throw new java.lang.IllegalArgumentException();
            for (int j = 0; j < i; j++) {
                if (points[j].compareTo(points[i]) == 0)
                    throw new java.lang.IllegalArgumentException();
            }
            copy[i] = points[i];
        }

        Arrays.sort(copy);

        for (int i = 0; i < n; i++) {
            Comparator<Point> c = copy[i].slopeOrder();
            Point[] slopes = new Point[n - i - 1];
            for (int j = i + 1; j < n; j++) {
                slopes[j - i - 1] = copy[j];
            }
            Arrays.sort(slopes, 0, n - i - 1, c);
            int k = 0, m = 0;
            while (k < n - i - 2 && m < n - i - 2) {
                for (; k < n - i - 2 && c.compare(slopes[k], slopes[k + 1]) != 0; k++) ;
                for (m = k; m < n - i - 2 && c.compare(slopes[m], slopes[m + 1]) == 0; m++) ;
                if (m - k + 1 >= 3) {
                    // new collinear copy
                    Node p = first;
                    while (p != null) {
                        if (slopes[m] == p.data[1] && slopes[m].slopeTo(p.data[0]) == slopes[m].slopeTo(copy[i]))
                            break;
                        p = p.next;
                    }
                    if (p == null) {
                        addLine(copy[i], slopes[m]);
                        lineNumber++;
                    }
                }
                k = m + 1;
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

    // public static void main(String[] args) {
    //     In in = new In(args[0]);
    //     int n = in.readInt();
    //     Point[] points = new Point[n];
    //     StdDraw.setXscale(0, 32767);
    //     StdDraw.setYscale(0, 32767);
    //     for (int i = 0; i < n; i++) {
    //         int x = in.readInt();
    //         int y = in.readInt();
    //         points[i] = new Point(x, y);
    //         points[i].draw();
    //     }
    //     FastCollinearPoints col = new FastCollinearPoints(points);
    //     StdOut.println(col.numberOfSegments());
    //     LineSegment[] lines = col.segments();
    //     for (LineSegment l : lines) {
    //         StdOut.println(l);
    //         l.draw();
    //     }
    // }

//    public static void main(String[] args) {
//        StdDraw.setXscale(0, 32767);
//        StdDraw.setYscale(0, 32767);
//        In file = new In(args[0]);
//        int n = file.readInt();
//        Point[] points = new Point[n];
//        for (int i = 0; i < n; i++) {
//            int x = file.readInt();
//            int y = file.readInt();
//            points[i] = new Point(x, y);
//            points[i].draw();
//        }
//        FastCollinearPoints c = new FastCollinearPoints(points);
//        StdOut.println(c.numberOfSegments());
//        LineSegment[] lines = c.segments();
//        for (LineSegment line : lines) {
//            StdOut.println(line);
//            line.draw();
//        }
//    }
}
