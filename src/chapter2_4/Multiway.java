package chapter2_4;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Multiway {
    public static void merge(In[] in) {
        MinPQ<String> pq = new MinPQ<>(100);
        for (int i = 0; i < in.length; i++) {
            while (!in[i].isEmpty()) {
                pq.insert(in[i].readString());
//                String s = in[i].readString();
//                pq.insert(s);
//                StdOut.println("Test : " + s + ", size = " + pq.size());
            }
        }
        while (!pq.isEmpty()) {
            StdOut.print(pq.delMin() + " ");
//            String s = pq.delMin();
//            StdOut.println(s);
//            StdOut.println("Test : " + s + ", size = " + pq.size());
        }
    }

    public static void main(String[] args) {
        int n = args.length;
        In[] streams = new In[n];
        for (int i = 0; i < n; i++)
            streams[i] = new In(args[i]);
        merge(streams);
    }
}
