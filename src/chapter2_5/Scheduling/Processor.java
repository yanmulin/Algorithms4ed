package chapter2_5.Scheduling;

import chapter1_3.Queue;

public class Processor implements Comparable<Processor> {
    private Queue<Job> jobs = new Queue<>();
    private double time = 0.0;

    public void add(Job job) {
        jobs.enqueue(job);
        time += job.time();
    }

    public String toString() {
        String ret = new String();
        for (Job x : jobs) {
            ret += x.toString() + "\n";
        }
        return ret;
    }

    public double time() {
        return time;
    }

    public int compareTo(Processor that) {
        if (time > that.time) return 1;
        else if (time < that.time) return -1;
        else return 0;
    }
}
