package chapter2_5.Scheduling;

import chapter2_4.MinPQ;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

// longest process time first
public class LPT {
    public static void main(String[] args) {
        int m = Integer.parseInt(args[0]);
        In in = new In(args[1]);
        int n = in.readInt();
        MinPQ<Processor> pq = new MinPQ<>(1000);
        Job[] jobs = new Job[n];
        for (int i = 0; i < n; i++) {
            String name = in.readString();
            String data = in.readString();
            double t = in.readDouble();
            jobs[i] = new Job(name, t);
        }
        for (int i = 0; i < m; i++) {
            pq.insert(new Processor());
        }
        Arrays.sort(jobs);
        double timer = 0.0;
        for (int i = 0; i < n; i++) {
            Processor p = pq.delMin();
            p.add(jobs[i]);
            pq.insert(p);
        }
        double maxtime = 0.0;
        while (!pq.isEmpty()) {
            Processor p = pq.delMin();
            double time = p.time();
            if (time > maxtime) maxtime = time;
        }
        StdOut.println(maxtime);
    }
}
