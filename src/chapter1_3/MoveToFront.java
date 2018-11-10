package chapter1_3;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class MoveToFront implements Iterable<String> {
    private Node first;

    private class Node {
        String ch;
        Node next;
    }

    public MoveToFront(String[] s) {
        for (String ch : s) {
            isRepeat(ch);
            push(ch);
        }
    }

    public Iterator iterator() {
        return new MoveToFrontIterator();
    }

    public class MoveToFrontIterator implements Iterator<String> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public String next() {
            String ch = current.ch;
            current = current.next;
            return ch;
        }
    }

    private void isRepeat(String ch) {
        if (first == null) return;
        if (first.ch == ch) {
            first = null;
            return;
        }
        Node x = first;
        while (x.next != null && x.next.ch != ch)
            x = x.next;
        if (x.next != null && x.next.ch == ch) removeAfter(x);
    }

    private void push(String ch) {
        Node oldfirst = first;
        first = new Node();
        first.ch = ch;
        first.next = oldfirst;
    }

    private String removeAfter(Node x) {
        assert x.next != null;
        String ch = x.next.ch;
        x.next = x.next.next;
        return ch;
    }

    public static void main(String[] args) {
        String[] s = {"a", "a", "b", "c", "d", "c", "e", "a", "f", "d"};
        MoveToFront mf = new MoveToFront(s);
        for (String c : mf)
            StdOut.print(c + " ");
        StdOut.println();
    }
}
