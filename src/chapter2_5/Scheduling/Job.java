package chapter2_5.Scheduling;

public class Job implements Comparable<Job> {
    private String name;
    private double time;

    public Job(String name, double time) {
        this.name = name;
        this.time = time;
    }

    public double time() {
        return time;
    }

    public int compareTo(Job that) {
        if (time > that.time) return 1;
        else if (time < that.time) return -1;
        else return 0;
    }

    public String toString() {
        return String.format("%s\t\t\t%f", name, time);
    }
}
