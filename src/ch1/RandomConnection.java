import edu.princeton.cs.algs4.StdDraw;

import java.time.LocalDateTime;
import java.util.Random;

public class RandomConnection {
    public static void main(String[] args) {
        if (args.length < 2) return;
        int N = Integer.parseInt(args[0]);
        double p = Double.parseDouble(args[1]);

        //设置画布
        StdDraw.setPenRadius(0.01);
        StdDraw.setXscale(-0.1, 0.1);
        StdDraw.setYscale(-0.1, 0.1);

        //画在圆上的N个点
        double r = 0.08;
        double[][] pts = new double[N][2];
        for (int i = 0; i < N; i++) {
            pts[i][0] = r * Math.cos(2 * Math.PI * i / N);
            pts[i][1] = r * Math.sin(2 * Math.PI * i / N);
            StdDraw.point(pts[i][0], pts[i][1]);
        }

        //连接圆上的点
        Random random = new Random(LocalDateTime.now().getSecond());
        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                if (random.nextInt(10) < p * 10)
                    StdDraw.line(pts[i][0], pts[i][1], pts[j][0], pts[j][1]);
            }
        }

        StdDraw.show();
    }
}
