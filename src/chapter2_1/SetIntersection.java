package chapter2_1;

import edu.princeton.cs.algs4.StdOut;

public class SetIntersection {
    private class Point implements Comparable<Point> {
        public double x;
        public double y;

        public Point() {
        }

        public void setXY(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public int compareTo(Point p) {
            if (x > p.x) return 1;
            else if (x == p.x) return 0;
            else return -1;
        }
    }

    public static Point[] Intersection(Point[] a, Point[] b) {
        Point[] ans = (Point[]) new Object[a.length];
        int n = 0;
        ShellSort.Sort(a);
        ShellSort.Sort(b);
        int i = 0, j = 0;
        while (i < a.length && j < b.length) {
            if (a[i].x == b[j].x) {
                if (a[i].y == b[j].y)
                    ans[n++] = a[i];
                i++;
                j++; // 假设点集中没有重复点
            } else if (a[i].x < b[j].x) {
                i++;
            } else {
                j++;
            }
        }
        Point[] ret = (Point[]) new Object[n];
        for (int k = 0; k < n; k++)
            ret[k] = ans[k];
        return ret;
    }

    public static void main(String[] args) {
        Point[] s1 = (Point[]) new Object[5];
        Point[] s2 = (Point[]) new Object[5];
        s1[0].setXY(1, 2);
        s1[1].setXY(2, 1);
        s1[2].setXY(1, 0);
        s1[3].setXY(3, 4);
        s1[4].setXY(4, 3);
        s2[0].setXY(3, 2);
        s2[1].setXY(1, 2);
        s2[2].setXY(1, 3);
        s2[3].setXY(3, 4);
        s2[4].setXY(4, 3);
        Point[] ans = Intersection(s1, s2);
        for (int i = 0; i < ans.length; i++)
            StdOut.println("(" + ans[i].x + ", " + ans[i].y + ")");
    }
}
