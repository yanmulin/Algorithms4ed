package chapter2_4;

import edu.princeton.cs.algs4.StdOut;

public class CubeSum {
    private static class Data implements Comparable<Data> {
        public int a;
        public int b;
        public int val;

        public int compareTo(Data d) {
            if (val < d.val) return -1;
            else if (val == d.val) return 0;
            else return 1;
        }
    }

    public static void find(int N) {
        MinPQ<Data> pq = new MinPQ<>(100);
        // init
        for (int i = 0; i <= N; i++) {
            Data data = new Data();
            data.a = i;
            data.b = 0;
            data.val = i * i * i;
            pq.insert(data);
        }
        while (!pq.isEmpty()) {
            Data d = pq.delMin();
            StdOut.println(d.a + "^3 + " + d.b + "^3 = " + d.val);
            d.b++;
            d.val = d.a * d.a * d.a + d.b * d.b * d.b;
            if (d.b <= N) pq.insert(d);
        }
    }

    public static void main(String[] args) {
        find(10);
    }
}
