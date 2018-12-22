package chapter3_5;

import edu.princeton.cs.algs4.StdOut;

public class SparseMatrix {
    private int n;
    private SparseVector[] rows;

    public SparseMatrix(int n) {
        this.n = n;
        rows = new SparseVector[n];
        for (int i = 0; i < n; i++)
            rows[i] = new SparseVector(n);
    }

    public void put(int i, int j, double value) {
        if (i < 0 || i >= n || j < 0 || j >= n)
            throw new java.lang.IllegalArgumentException();
        rows[i].put(j, value);
    }

    public double get(int i, int j) {
        if (i < 0 || i >= n || j < 0 || j >= n)
            throw new java.lang.IllegalArgumentException();
        return rows[i].get(j);
    }

    public int nnz() {
        int count = 0;
        for (int i = 0; i < n; i++)
            count += rows[i].nnz();
        return count;
    }

    public SparseVector times(SparseVector v) {
        if (v.dimension() != n) throw new java.lang.IllegalArgumentException();
        SparseVector c = new SparseVector(n);
        for (int i = 0; i < n; i++)
            c.put(i, v.dot(c));
        return c;
    }

    public SparseMatrix plus(SparseMatrix that) {
        if (this.n != that.n) throw new java.lang.IllegalArgumentException();
        SparseMatrix m = new SparseMatrix(n);
        for (int i = 0; i < n; i++)
            m.rows[i] = rows[i].plus(that.rows[i]);
        return m;
    }

    public String toString() {
        String s = "n = " + n + ", nonzeros = " + nnz() + "\n";
        for (int i = 0; i < n; i++) {
            s += i + ": " + rows[i] + "\n";
        }
        return s;
    }

    public static void main(String[] args) {
        SparseMatrix A = new SparseMatrix(5);
        SparseVector x = new SparseVector(5);
        A.put(0, 0, 1.0);
        A.put(1, 1, 1.0);
        A.put(2, 2, 1.0);
        A.put(3, 3, 1.0);
        A.put(4, 4, 1.0);
        A.put(2, 4, 0.3);
        x.put(0, 0.75);
        x.put(2, 0.11);
        StdOut.println("x     : " + x);
        StdOut.println("A     : " + A);
        StdOut.println("Ax    : " + A.times(x));
        StdOut.println("A + A : " + A.plus(A));
    }
}
