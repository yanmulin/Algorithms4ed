package ch1;

import edu.princeton.cs.algs4.StdOut;

public class Josephus {
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int M = Integer.parseInt(args[1]);
        Queue<Integer> q = new Queue<>();
        for (int i = 0; i < N; i++)
            q.enqueue(i);
        while (!q.isEmpty()) {
            for (int i = 0; i < M - 1; i++)
                q.enqueue(q.dequeue());
            StdOut.print(q.dequeue() + " ");
        }
        StdOut.println();
    }
}
