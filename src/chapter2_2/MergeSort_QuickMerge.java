package chapter2_2;

import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;


public class MergeSort_QuickMerge {


    public static void Sort(Object[] a, Comparator cmp) {
        Object[] aux = new Object[a.length];
        Sort(a, aux, 0, a.length - 1, cmp);
    }

    private static void Sort(Object[] a, Object[] aux, int lo, int hi, Comparator cmp) {
        if (lo >= hi) return;
        int mid = (lo + hi) / 2;
        Sort(a, aux, lo, mid, cmp);
        Sort(a, aux, mid + 1, hi, cmp);
        merge(a, aux, lo, mid, hi, cmp);
    }

    private static void merge(Object[] a, Object[] aux, int lo, int mid, int hi, Comparator cmp) {
        assert isSorted(a, cmp, lo, mid);
        assert isSorted(a, cmp, mid + 1, hi);

        // a前半部分正序插入aux，后半部分逆序插入aux
        for (int i = lo; i <= mid; i++)
            aux[i] = a[i];
        for (int i = hi; i >= mid + 1; i--)
            aux[hi - i + mid + 1] = a[i];

        // 可以减少i，j的边界条件
        int i = lo, j = hi;
        for (int k = lo; k <= hi; k++) {
            if (less(cmp, aux[i], aux[j]) >= 0) a[k] = aux[i++];
            else a[k] = aux[j--];
        }

        assert isSorted(a, cmp, lo, hi);
    }

    private static boolean isSorted(Object[] arr, Comparator c, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(c, arr[i - 1], arr[i]) < 0) return false;
        return true;
    }

    private static int less(Comparator c, Object a, Object b) {
        return c.compare(b, a);
    }

    public static void main(String[] args) {
        MyPoint[] ps = new MyPoint[5];
        ps[0] = new MyPoint();
        ps[1] = new MyPoint();
        ps[2] = new MyPoint();
        ps[3] = new MyPoint();
        ps[4] = new MyPoint();
        ps[0].set(3, 1);
        ps[1].set(1, 2);
        ps[2].set(2, 3);
        ps[3].set(3, 2);
        ps[4].set(3, 3);
        Sort(ps, MyPoint.X_ORDER);
        for (MyPoint p : ps)
            StdOut.println("(" + p.x + ", " + p.y + ")");
    }
}


final class MyPoint {
    public static final Comparator<MyPoint> X_ORDER = new XOrder();
    public static final Comparator<MyPoint> Y_ORDER = new YOrder();

    public double x;
    public double y;

    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }

    private static class XOrder implements Comparator<MyPoint> {
        public int compare(MyPoint p1, MyPoint p2) {
            if (p1.x < p2.x) return -1;
            else if (p1.x == p2.x) return 0;
            else return 1;
        }
    }

    private static class YOrder implements Comparator<MyPoint> {
        public int compare(MyPoint p1, MyPoint p2) {
            if (p1.y < p2.y) return -1;
            else if (p1.y == p2.y) return 0;
            else return 1;
        }
    }
}
