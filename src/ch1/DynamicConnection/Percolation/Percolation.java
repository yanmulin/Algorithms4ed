package ch1.DynamicConnection.Percolation;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] sites;
    private int num;
    private final WeightedQuickUnionUF uf;

    public Percolation(int n) {
        if (n <= 0)
            throw new java.lang.IllegalArgumentException();
        sites = new boolean[n][n];
        uf = new WeightedQuickUnionUF(n * n + 2); // n*n: virtual top, n*n+1: virtual bottom
    }

    public void open(int row, int col) {
        int n = sites.length;
        if (!indiceCheck(row, col))
            throw new java.lang.IllegalArgumentException();
        if (sites[row - 1][col - 1]) return;

        sites[row - 1][col - 1] = true;

        int idx = getIndex(row, col);

        if (row == 1) uf.union(idx, n * n);
        else if (isOpen(row - 1, col))
            uf.union(idx, idx - n);

        if (row < n && isOpen(row + 1, col))
            uf.union(idx, idx + n);

        if (col != 1 && isOpen(row, col - 1)) uf.union(idx, idx - 1);

        if (col != n && isOpen(row, col + 1)) uf.union(idx, idx + 1);

        num++;
    }

    public boolean isOpen(int row, int col) {
        if (!indiceCheck(row, col))
            throw new java.lang.IllegalArgumentException();
        return sites[row - 1][col - 1];
    }

    public boolean isFull(int row, int col) {
        if (!indiceCheck(row, col))
            throw new java.lang.IllegalArgumentException();
        int idx = getIndex(row, col);
        int n = sites.length;
        //这种方法有问题，有些方块通过virtual-bottom跟顶部相连
        return isOpen(row, col) && uf.connected(idx, n * n);
    }

    public int numberOfOpenSites() {
        return num;
    }

    public boolean percolates() {
        int n = sites.length;
        for (int i = 1; i <= n; i++) {
            if (!isOpen(n - 1, i)) continue;
            int idx = getIndex(n - 1, i);
            if (uf.connected(idx, n * n)) return true;
        }
        return false;
    }

    private boolean indiceCheck(int row, int col) {
        if (row <= 0 || row > sites.length) return false;
        if (col <= 0 || col > sites.length) return false;
        return true;
    }

    private int getIndex(int row, int col) {
        return (row - 1) * sites.length + (col - 1);
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
//        In in = new In(args[0]);
//        int n = in.readInt();
        Percolation p = new Percolation(n);
//        while (!in.isEmpty()) {
//            int a = in.readInt();
//            int b = in.readInt();
//            p.open(a, b);
//        }
        int[] notOpen = new int[n * n];
        for (int i = n * n - 1; i >= 0; i--) {
            notOpen[i] = i;
        }
        StdRandom.shuffle(notOpen);
        for (int i = n * n - 1; i >= 0; i--) {
            int row = notOpen[i] / n + 1;
            int col = notOpen[i] % n + 1;
            assert p.isFull(row, col);
            p.open(row, col);
            if (p.percolates()) break;
        }
        //画图
        StdDraw.setXscale(0.0, 1.0);
        StdDraw.setYscale(0.0, 1.0);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!p.isOpen(i + 1, j + 1)) {
                    StdDraw.setPenColor(StdDraw.BLACK);
                    StdDraw.filledRectangle(((double) j) / n + 1.0 / n / 2.0,
                            1.0 - (((double) i) / n + 1.0 / n / 2.0),
                            1.0 / n / 2.0, 1.0 / n / 2.0);
                } else if (p.isFull(i + 1, j + 1)) {
                    StdDraw.setPenColor(StdDraw.BOOK_BLUE);
                    StdDraw.filledRectangle(((double) j) / n + 1.0 / n / 2.0,
                            1.0 - (((double) i) / n + 1.0 / n / 2.0),
                            1.0 / n / 2.0, 1.0 / n / 2.0);
                }
            }
        }

        StdDraw.setPenColor(StdDraw.DARK_GRAY);
        for (int i = 0; i < n; i++) {
            double val = ((double) i) / n;
            StdDraw.line(0.0, val, 1.0, val);//x
            StdDraw.line(val, 0.0, val, 1.0);
        }
    }
}
