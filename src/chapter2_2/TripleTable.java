package chapter2_2;

import edu.princeton.cs.algs4.StdOut;

public class TripleTable {
    public static String FindDuplicate(String[] t1, String[] t2, String[] t3) {
        MergeSort.Sort(t1);
        MergeSort.Sort(t2);
        MergeSort.Sort(t3);
        int i = 0, j = 0, k = 0;
        while (i < t1.length && j < t2.length && k < t3.length) {
            int cmp = t1[i].compareTo(t2[j]);
            if (cmp > 0) j++;
            else if (cmp < 0) i++;
            else {
                int cmp2 = t3[k].compareTo(t2[j]);
                if (cmp2 == 0)
                    return t3[k];
                else if (cmp2 < 0) k++;
                else {
                    i++;
                    j++;
                }
            }
        }
        return "";
    }

    public static void main(String[] args) {
        String[] a = {"ggg", "eee", "aaa", "ccc", "ddd"};
        String[] b = {"fff", "iii", "bbb", "eee", "ggg"};
        String[] c = {"kkk", "fff", "aaa", "eee", "ccc"};
        StdOut.println(FindDuplicate(a, b, c));
    }
}
