import edu.princeton.cs.algs4.StdDraw;

public class testAnimation {
    public static void main(String[] args) {
        StdDraw.setScale(-2, 2);
        StdDraw.enableDoubleBuffering();
        for (double t = 0.0; true; t += 0.02) {
            double x = Math.cos(t);
            double y = Math.sin(t);
            StdDraw.clear();                            // Step1. clear canvas
            StdDraw.filledCircle(x, y, 0.05);           // Step2. draw on offscreen canvas
            StdDraw.filledCircle(-x, -y, 0.05);
            StdDraw.show();                             // Step3. copy offscreen canvas to onscreen canvas
            StdDraw.pause(20);                          // Step4. wait for a while
        }
    }
}
