import edu.princeton.cs.algs4.StdOut;

import java.time.LocalDateTime;
import java.util.Random;

public class BitonicArray {
    private static int binarySearchMax(int[] a) {
        int n = a.length;
        int lo = 1, hi = n - 2;
        if (a[lo] < a[0]) return 0;
        if (a[hi] < a[n - 1]) return n - 1;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (a[mid] > a[mid - 1] && a[mid] > a[mid + 1]) return mid;
            else if (a[mid] > a[mid - 1] && a[mid] < a[mid + 1]) lo = mid + 1;
            else hi = mid - 1;
        }
        return -1;
    }

    private static int binarySearch(int[] a, int lo, int hi, int key) {
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (a[mid] < key) lo = mid + 1;
            else if (a[mid] > key) hi = mid - 1;
            else return mid;
        }
        return -1;
    }

    private static int binarySearchR(int[] a, int lo, int hi, int key) {
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (a[mid] < key) hi = mid - 1;
            else if (a[mid] > key) lo = mid + 1;
            else return mid;
        }
        return -1;
    }

    public static boolean searchKey(int[] a, int key) {
        if (a.length == 1) return true;
        int max_idx = binarySearchMax(a);

        return binarySearch(a, 0, max_idx, key) >= 0 ||
                binarySearchR(a, max_idx + 1, a.length - 1, key) >= 0;
    }

    public static void main(String[] args) {
        Random random = new Random(LocalDateTime.now().getSecond());
        for (int i = 0; i < 10000; i++) {
            //generate data
            int n1 = random.nextInt(100) + 1;
            int n2 = random.nextInt(100);
            int[] a = new int[n1 + n2];
            a[0] = random.nextInt(20);
            for (int j = 1; j < n1; j++) {
                a[j] = a[j - 1] + random.nextInt(20) + 1;
            }
            int maxval = a[n1 - 1];
            for (int j = 0; j < n2; j++) {
                a[n1 + j] = a[n1 + j - 1] - random.nextInt(20) - 1;
            }
            int selected = random.nextInt(n1 + n2);
            if (searchKey(a, a[selected]) == false) {
                StdOut.println("Failed: " + a[selected] + " Max: " + a[binarySearchMax(a)]);
                for (int j = 0; j < a.length; j++) {
                    StdOut.print(a[j] + " ");
                    if (j != 0 && j % 10 == 0) StdOut.println();
                }
                StdOut.println();
            }
        }
    }

    public static void test_searchmax(int[] a, int max) {
        if (binarySearchMax(a) != max) {
            StdOut.println("Failed:");
            for (int j = 0; j < a.length; j++) {
                StdOut.print(a[j] + " ");
                if (j != 0 && j % 10 == 0) StdOut.println();
            }
            StdOut.println();
        }
    }
}
