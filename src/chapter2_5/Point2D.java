package chapter2_5;

import edu.princeton.cs.algs4.StdDraw;

import java.util.Comparator;

public class Point2D {
    private double px, py;
    public static XComparator ComparatorXCoord = new XComparator();
    public static YComparator ComparatorYCoord = new YComparator();
    public OriDistComparator ComparatorOriDist = new OriDistComparator();

    public Point2D(double x, double y) {
        px = x;
        py = y;
    }

    public double x() {
        return px;
    }

    public double y() {
        return py;
    }

    public double theta() {
        if (px == 0.0 && py == 0.0) return 0.0;
        return Math.atan2(py, px);
    }

    public double distTo(Point2D that) {
        double sx = px * px;
        double sy = py * py;
        return Math.sqrt(sx + sy);
    }

    public void draw() {
        StdDraw.point(px, py);
    }

    public String toString() {
        return String.format("(" + px + ", " + py + ")");
    }

    public DistComparator ComparatorDistFrom() {
        return new DistComparator(this);
    }

    public ThetaComparator ComparatorThetaFrom() {
        return new ThetaComparator(this);
    }

    public static class XComparator implements Comparator<Point2D> {
        public int compare(Point2D x, Point2D y) {
            if (x.px > y.px) return 1;
            else if (x.px < y.py) return -1;
            else return 0;
        }
    }

    public static class YComparator implements Comparator<Point2D> {
        public int compare(Point2D x, Point2D y) {
            if (x.py > y.py) return 1;
            else if (x.py < y.py) return -1;
            else return 0;
        }
    }

    public class OriDistComparator implements Comparator<Point2D> {
        public int compare(Point2D x, Point2D y) {
            Point2D origin = new Point2D(0.0, 0.0);
            double dist1 = x.distTo(origin);
            double dist2 = y.distTo(origin);
            if (dist1 > dist2) return 1;
            else if (dist1 < dist2) return -1;
            else return 0;
        }
    }

    public class DistComparator implements Comparator<Point2D> {
        private Point2D p;

        public DistComparator(Point2D p) {
            this.p = p;
        }

        public int compare(Point2D x, Point2D y) {
            double dist1 = x.distTo(p);
            double dist2 = y.distTo(p);
            if (dist1 > dist2) return 1;
            else if (dist1 < dist2) return -1;
            else return 0;
        }
    }

    public class ThetaComparator implements Comparator<Point2D> {
        private Point2D p;

        public ThetaComparator(Point2D p) {
            this.p = p;
        }

        public int compare(Point2D x, Point2D y) {
            Point2D nx = new Point2D(x.x() - p.x(), x.y() - p.y());
            Point2D ny = new Point2D(y.x() - p.x(), y.y() - p.y());
            double theta1 = nx.theta();
            double theta2 = ny.theta();
            if (theta1 > theta2) return 1;
            else if (theta1 < theta2) return -1;
            else return 0;
        }
    }

}
