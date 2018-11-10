package chapter1_3;


import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class Buffer implements Iterable<String> {
    private Node first;
    private Node cursor;
    private int sz;

    private class Node {
        String data;
        Node next, prev;
    }

    public void insert(String c) {
        Node oldcursor = cursor;
        cursor = new Node();
        cursor.data = c;
        cursor.prev = oldcursor;
        if (oldcursor != null) {
            cursor.next = oldcursor.next;
            if (oldcursor.next != null) {
                oldcursor.next.prev = cursor;
            }
            oldcursor.next = cursor;
        } else first = cursor;
        sz++;
    }

    public String delete() {
        String s = cursor.data;
        cursor = cursor.prev;
        if (cursor != null) {
            cursor.next = cursor.next.next;
            cursor.next.prev = cursor;
        } else first = null;
        sz--;
        return s;
    }

    public void left(int k) {
        while (k-- > 0 && cursor.prev != null)
            cursor = cursor.prev;
    }

    public void right(int k) {
        while (k-- > 0 && cursor.next != null)
            cursor = cursor.next;
    }

    public int size() {
        return sz;
    }


    public Iterator<String> iterator() {
        return new BufferIterator();
    }

    private class BufferIterator implements Iterator<String> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public String next() {
            String item = current.data;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        Buffer buf = new Buffer();
        buf.insert("A");
        buf.insert("B");
        buf.insert("C");
        buf.insert("D");
        for (String s : buf)
            StdOut.print(s);
        StdOut.println();
        buf.left(2);
        buf.insert("E");
        buf.insert("F");
        for (String s : buf)
            StdOut.print(s);
        StdOut.println();
        buf.right(1);
        buf.insert("H");
        buf.insert("I");
        for (String s : buf)
            StdOut.print(s);
        StdOut.println();
        buf.left(2);
        buf.delete();
        for (String s : buf)
            StdOut.print(s);
        StdOut.println();
    }
}
