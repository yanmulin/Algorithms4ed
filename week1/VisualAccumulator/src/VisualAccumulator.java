import edu.princeton.cs.algs4.StdDraw;

public class VisualAccumulator {
    private int N;
    private double sum;

    public VisualAccumulator(int trials, double max) {
        //设置画布
        StdDraw.setXscale(0.0, trials);
        StdDraw.setYscale(0.0, max);
        StdDraw.setPenRadius(0.005);
    }

    public void addDataValue(double val) {
        N++;
        sum += val;
        StdDraw.setPenColor(StdDraw.DARK_GRAY);
        StdDraw.point(N, val);
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.point(N, mean());
    }

    public double mean() {
        return sum / N;
    }

    public String toString() {
        return "mean (" + N + " values): " + mean();
    }
}
