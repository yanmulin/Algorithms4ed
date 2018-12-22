package chapter2_5;

import edu.princeton.cs.algs4.StdOut;

public class StableMinPQ<Key extends Comparable<Key>> {
    private Key[] pq = (Key[]) new Comparable[2];
    private int N = 0;
    private long timestamp = 0;
    private long time[] = new long[2];

    private void resize(int capacity) {
        Key[] oldpq = pq;
        long[] oldtime = time;
        pq = (Key[]) new Comparable[capacity + 1];
        time = new long[capacity + 1];
        for (int i = 0; i <= N; i++) {
            pq[i] = oldpq[i];
            time[i] = oldtime[i];
        }
    }

    private void swim(int i) {
        while (i > 1 && greater(i / 2, i)) {
            exch(i / 2, i);
            i /= 2;
        }
    }

    private void sink(int i) {
        while (i * 2 <= N) {
            int j = i * 2;
            if (j < N && greater(j, j + 1)) j++;
            if (greater(j, i)) break;
            exch(j, i);
            i = j;
        }
    }

    private boolean greater(int i, int j) {
        int cmp = pq[i].compareTo(pq[j]);
        if (cmp > 0) return true;
        else if (cmp < 0) return false;
        //return false;
        return time[i] > time[j];
    }

    private void exch(int i, int j) {
        Key k = pq[i];
        pq[i] = pq[j];
        pq[j] = k;
        long t = time[i];
        time[i] = time[j];
        time[j] = t;
    }

    private boolean isMinHeap() {
        return isMinHeap(1);
    }

    private boolean isMinHeap(int i) {
        if (i * 2 > N) return true;
        int j = i * 2;
        if (j < N && greater(j, j + 1)) j++;
        if (greater(i, j)) return false;
        return isMinHeap(i * 2) && isMinHeap(i * 2 + 1);
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void insert(Key v) {
        if (N + 1 >= pq.length) resize(N * 2);
        pq[++N] = v;
        time[N] = timestamp++;
        swim(N);
        assert isMinHeap();
    }

    public Key delMin() {
        exch(1, N);
        Key k = pq[N];
        time[N] = 0;
        pq[N--] = null;
        sink(1);
        assert isMinHeap();
        if (N <= pq.length / 4) resize(pq.length / 2);
        return k;
    }

    public Key min() {
        return pq[1];
    }

    private static final class Tuple implements Comparable<Tuple> {
        private String name;
        private int id;

        private Tuple(String name, int id) {
            this.name = name;
            this.id = id;
        }

        public int compareTo(Tuple that) {
            return this.name.compareTo(that.name);
        }

        public String toString() {
            return name + " " + id;
        }
    }

    // test client
    public static void main(String[] args) {
        StableMinPQ<Tuple> pq = new StableMinPQ<Tuple>();

        // insert a bunch of strings
        String text = "it was the best of times it was the worst of times it was the "
                + "age of wisdom it was the age of foolishness it was the epoch "
                + "belief it was the epoch of incredulity it was the season of light "
                + "it was the season of darkness it was the spring of hope it was the "
                + "winter of despair";
        String[] strings = text.split(" ");
        for (int i = 0; i < strings.length; i++) {
            pq.insert(new Tuple(strings[i], i));
        }


        // delete and print each key
        while (!pq.isEmpty()) {
            StdOut.println(pq.delMin());
        }
        StdOut.println();

    }
}
