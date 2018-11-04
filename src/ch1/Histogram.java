import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;

public class Histogram {
    public static void main(String[] args) {
        if (args.length < 3) return;
        int N = Integer.parseInt(args[0]);
        double l = Double.parseDouble(args[1]);
        double r = Double.parseDouble(args[2]);

        //统计输入流的数据
        int max_val = 0;
        int[] stat = new int[N];//默认初始化为0
        double d = (r - l) / N;
        for (int i = 0; i < N; i++) {
            double num = StdIn.readDouble();
            if (num < l || num > r) continue;
            int k = (int) (Math.floor((num - l) / d));
            stat[k]++;
            if (max_val < stat[k]) max_val = stat[k];
        }

        //设置画布
        StdDraw.setXscale(0.0, 52.0);
        StdDraw.setYscale(0.0, 52.0);

        //画直方图
        for (int i = 0; i < N; i++) {
            double x = i * 50.0 / N + 25.0 / N + 1;
            double y = 0;
            double rw = 50.0 / N / 2.0;
            double rh = stat[i] * 50.0 / (max_val * 1.2);
            StdDraw.filledRectangle(x, y, rw * 0.8, rh);
        }
    }
}
