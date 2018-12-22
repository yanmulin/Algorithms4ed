package chapter2_5;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class DrawPolygon {
    public static void draw(Point2D[] points) {
        StdDraw.setScale(-2.0, 8.0);
        int n = points.length;
        Point2D p = points[0];
        for (int i = 1; i < n; i++) {
            if (Point2D.ComparatorYCoord.compare(p, points[i]) > 0)
                p = points[i];
        }
        StdOut.println(p);
        Arrays.sort(points, p.ComparatorThetaFrom());
        for (Point2D x : points)
            StdOut.print(x + " ");
        StdOut.println();
        assert (points[0] == p);
        for (int i = 0; i < n; i++)
            points[i].draw();
        for (int i = 1; i < n; i++) {
            StdDraw.line(points[i - 1].x(), points[i - 1].y(), points[i].x(), points[i].y());
        }
        StdDraw.line(points[0].x(), points[0].y(), points[n - 1].x(), points[n - 1].y());
    }

    public static void main(String[] args) {
        Point2D[] points = new Point2D[6];
        points[0] = new Point2D(2, 3);
        points[1] = new Point2D(0, 4);
        points[2] = new Point2D(3, 2);
        points[3] = new Point2D(3, 5);
        points[4] = new Point2D(1, 1);
        points[5] = new Point2D(0, 3);
        draw(points);
    }
}
