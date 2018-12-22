package chapter2_5.Scheduling;

public class SPT {
    public static void main(String[] args) {
        In in = new In(args[0]);
        int n = in.readInt();
        Job[] jobs = new Job[n];
        for (int i = 0; i < n; i++) {
            String name = in.readString();
            String data = in.readString();
            double t = in.readDouble();
            jobs[i] = new Job(name, t);
        }
        Arrays.sort(jobs);
        for (int i=0;i<n;i++)
            StdOut.println(jobs[i]);
    }
}