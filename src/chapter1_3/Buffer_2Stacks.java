package chapter1_3;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class Buffer_2Stacks implements Iterable<String> {
    Stack<String> left = new Stack<>();
    Stack<String> right = new Stack<>();

    public void insert(String ch) {
        left.push(ch);
    }

    public String delete() {
        return left.pop();
    }

    public void left(int k) {
        for (int i = 0; i < k && !left.isEmpty(); i++)
            right.push(left.pop());
    }

    public void right(int k) {
        for (int i = 0; i < k && !right.isEmpty(); i++)
            left.push(right.pop());
    }

    public int size() {
        return left.size() + right.size();
    }

    public Iterator<String> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<String> {
        private Iterator<String> leftIt = left.iterator();
        private Iterator<String> rightIt = right.iterator();

        public boolean hasNext() {
            return leftIt.hasNext() || rightIt.hasNext();
        }

        public String next() {
            if (rightIt.hasNext()) return rightIt.next();
            else return leftIt.next();

        }
    }

    public static void main(String[] args) {
        Buffer_2Stacks buf = new Buffer_2Stacks();
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
