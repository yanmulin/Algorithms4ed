package chapter3_1;

import edu.princeton.cs.algs4.StdOut;

public class PerfectBalance<Key extends Comparable<Key>> {
    private BinarySearchTreeST<Key, Key> st = new BinarySearchTreeST<>();

    public BinarySearchTreeST<Key, Key> build(Key[] seq) {
        build(seq, 0, seq.length - 1);
        return st;
    }

    private void build(Key[] seq, int lo, int hi) {
        if (lo > hi) return;
        int mid = (lo + hi) / 2;
        st.put(seq[mid], null);
        build(seq, lo, mid - 1);
        build(seq, mid + 1, hi);
    }

    public static void main(String[] args) {
        Integer[] a = {5, 3, 6, 8, 1, 2, 7, 4, 9};
        PerfectBalance<Integer> pb = new PerfectBalance<>();
        BinarySearchTreeST<Integer, Integer> st = pb.build(a);
        Iterable<Integer> keys = st.keys();
        for (Integer key : keys)
            StdOut.print(key + " ");
        StdOut.println();
    }
}
