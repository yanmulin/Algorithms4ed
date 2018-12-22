package chapter3_1.KdTree;

import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Point2D;

// Brute-force
public class PointSET {
    private SET<Point2D> pointSet = new SET<>();

    public boolean isEmpty() {
        return pointSet.isEmpty();
    }

    public int size() {
        return pointSet.size();
    }

    public void insert(Point2D p) {
        if (p == null) throw new java.lang.IllegalArgumentException();
        pointSet.add(p);
    }

    public boolean contains(Point2D p) {
        if (p == null) throw new java.lang.IllegalArgumentException();
        return pointSet.contains(p);
    }

    public void draw() {
        StdDraw.setScale(0, 1.0);
        for (Point2D point : pointSet)
            StdDraw.point(point.x(), point.y());
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new java.lang.IllegalArgumentException();
        SET<Point2D> set = new SET<>();
        for (Point2D point : pointSet)
            if (rect.contains(point)) set.add(point);
        return set;
    }

    public Point2D nearest(Point2D p) {
        if (p == null) throw new java.lang.IllegalArgumentException();
        if (isEmpty()) return null;
        Point2D target = pointSet.min();
        double minDist = p.distanceTo(target);
        for (Point2D point : pointSet) {
            double dist = p.distanceTo(point);
            if (dist < minDist) {
                minDist = dist;
                target = point;
            }
        }
        return target;
    }

    public static void main(String[] args) {
        PointSET ps = new PointSET();
        ps.insert(new Point2D(0.7, 0.2));
        ps.insert(new Point2D(0.5, 0.4));
        ps.insert(new Point2D(0.2, 0.3));
        ps.insert(new Point2D(0.4, 0.7));
        ps.insert(new Point2D(0.9, 0.6));
        StdOut.println(ps.contains(new Point2D(0.7, 0.2)));
        StdOut.println(ps.contains(new Point2D(0.5, 0.4)));
        StdOut.println(ps.contains(new Point2D(0.2, 0.3)));
        StdOut.println(ps.contains(new Point2D(0.4, 0.7)));
        StdOut.println(ps.contains(new Point2D(0.9, 0.6)));
    }
}
