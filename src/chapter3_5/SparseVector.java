package chapter3_5;

import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdOut;

public class SparseVector {
    private int d;
    private ST<Integer, Double> st;

    public SparseVector(int d) {
        this.d = d;
        this.st = new ST<>();
    }

    public int nnz() {
        return st.size();
    }

    public int dimension() {
        return d;
    }

    public void put(int i, double value) {
        if (i < 0 || i >= d) throw new java.lang.IllegalArgumentException();
        if (value == 0.0) st.delete(i);
        else st.put(i, value);
    }

    public double get(int i) {
        if (i < 0 || i >= d) throw new java.lang.IllegalArgumentException();
        if (st.contains(i)) return st.get(i);
        else return 0.0;
    }

    public double dot(double[] that) {
        if (this.d != that.length) throw new java.lang.IllegalArgumentException();
        double sum = 0.0;
        for (Integer i : st.keys())
            sum += st.get(i) * that[i];
        return sum;
    }

    public double dot(SparseVector that) {
        if (this.d != that.d) throw new java.lang.IllegalArgumentException();
        double sum = 0.0;
        if (this.d > that.d) {
            for (Integer i : that.st.keys())
                if (this.st.contains(i)) sum += this.st.get(i) * that.st.get(i);
        } else {
            for (Integer i : this.st.keys())
                if (that.st.contains(i)) sum += this.st.get(i) * that.st.get(i);
        }
        return sum;
    }

    public double magnitude() {
        return Math.sqrt(this.dot(this));
    }

    public SparseVector scale(double alpha) {
        SparseVector v = new SparseVector(d);
        for (Integer i : st.keys())
            v.put(i, st.get(i) * alpha);
        return v;
    }

    public SparseVector plus(SparseVector that) {
        if (that.d != this.d) throw new java.lang.IllegalArgumentException();
        SparseVector v = new SparseVector(d);
        for (Integer i : st.keys())
            v.put(i, this.get(i));
        for (Integer i : that.st.keys())
            v.put(i, that.get(i) + v.get(i));
        return v;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i : st.keys())
            s.append("(" + i + ", " + st.get(i) + ") ");
        return s.toString();
    }

    public static void main(String[] args) {
        SparseVector a = new SparseVector(10);
        SparseVector b = new SparseVector(10);
        a.put(3, 0.50);
        a.put(9, 0.75);
        a.put(6, 0.11);
        a.put(6, 0.00);
        b.put(3, 0.60);
        b.put(4, 0.90);
        StdOut.println("a = " + a);
        StdOut.println("b = " + b);
        StdOut.println("a + b = " + a.plus(b));
        StdOut.println("a dot b = " + a.dot(b));
    }
}
