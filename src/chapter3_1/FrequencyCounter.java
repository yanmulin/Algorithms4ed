package chapter3_1;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class FrequencyCounter {
    public static void main(String[] args) {
        In in = new In(args[0]);
        int min_len = 4;
        BinarySearchTreeST<String, Integer> st = new BinarySearchTreeST<>();
        while (!in.isEmpty()) {
            String word = in.readString();
            if (word.length() < min_len) continue;
            if (st.contains(word)) st.put(word, st.get(word) + 1);
            else st.put(word, 1);
        }
        //StdOut.println(st.min());
        //st.deleteMin();

        StdOut.println(st.rank("epoch"));
        StdOut.println(st.select(4));
        StdOut.println(st.floor("departure"));
        StdOut.println(st.ceiling("departure"));
        //st.delete("epoch");
        //st.delete("winter");
        //st.delete("despair");
//        st.delete("belief");
        StdOut.println(st.size());
        Iterable<String> keys = st.keys();
        for (String key : keys) {
            StdOut.println(key + ": " + st.get(key));
        }
    }
}
