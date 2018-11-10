package chapter2_1;

import edu.princeton.cs.algs4.StdDraw;

public class SortAnimation {
    public static void Initialize() {
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(-0.005, 1.005);
        StdDraw.setYscale(-0.05, 1.1);
    }

    // assum 0.0 <= a[i] < 1.0
    public static void Draw(Double[] a, int idx1, int idx2) {
        StdDraw.clear();
        int n = a.length;
        double w = 1.0 / n;
        for (int i = 0; i < n; i++) {
            if (i == idx1 || i == idx2)
                StdDraw.setPenColor(StdDraw.BLACK);
            else
                StdDraw.setPenColor(StdDraw.GRAY);
            double x = 1.0 * i / n + w / 2;
            double y = a[i] / 2;
            StdDraw.filledRectangle(x, y, 0.95 * w / 2, a[i] / 2);
        }
        StdDraw.setPenColor(StdDraw.RED);
        double pg1_x[] = new double[3];
        double pg1_y[] = new double[3];
        pg1_x[0] = idx1 * 1.0 / n + 0.75 * w / 2;
        pg1_x[1] = idx1 * 1.0 / n;
        pg1_x[2] = idx1 * 1.0 / n + 0.75 * w;
        pg1_y[0] = -0.01;
        pg1_y[1] = -0.04;
        pg1_y[2] = -0.04;
        StdDraw.filledPolygon(pg1_x, pg1_y);

        StdDraw.setPenColor(StdDraw.GREEN);
        double pg2_x[] = new double[3];
        double pg2_y[] = new double[3];
        pg2_x[0] = idx2 * 1.0 / n + 0.75 * w / 2;
        pg2_x[1] = idx2 * 1.0 / n;
        pg2_x[2] = idx2 * 1.0 / n + 0.75 * w;
        pg2_y[0] = -0.01;
        pg2_y[1] = -0.04;
        pg2_y[2] = -0.04;
        StdDraw.filledPolygon(pg2_x, pg2_y);
        StdDraw.show();
        StdDraw.pause(50);
    }

    public static void Draw(Double[] a) {
        StdDraw.clear();
        int n = a.length;
        double w = 1.0 / n;
        StdDraw.setPenColor(StdDraw.BLACK);
        for (int i = 0; i < n; i++) {
            double x = 1.0 * i / n + w / 2;
            double y = a[i] / 2;
            StdDraw.filledRectangle(x, y, 0.95 * w / 2, a[i] / 2);
        }
        StdDraw.show();
    }

    public static void main(String[] args) {
        Double[] a = {0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.5, 0.4, 0.3, 0.2, 0.1};
        Initialize();
        Draw(a, 3, 6);
    }
}
