import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class TestVisualAccumulator {
    public static void main(String[] args) {
        int T = Integer.parseInt(args[0]);
        VisualAccumulator accumulator = new VisualAccumulator(T, 1.0);
        for (int i = 0; i < T; i++) {
            accumulator.addDataValue(StdRandom.uniform());
        }
        StdOut.println(accumulator);
    }
}
