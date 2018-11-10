package chapter1_3;

import edu.princeton.cs.algs4.StdOut;

import java.io.File;

public class ListFiles {
    private Queue<String> q;

    public ListFiles(String path) {
        q = new Queue<>();
        q.enqueue(path);
        while (!q.isEmpty()) {
            String name = q.dequeue();
            File f = new File(name);
            if (f.isDirectory()) {
                String[] entries = f.list();
                for (String entry : entries)
                    q.enqueue(entry);
            }
            StdOut.println(name);
        }
    }

    public static void main(String[] args) {
        ListFiles l = new ListFiles(args[0]);
    }
}
