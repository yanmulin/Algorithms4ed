package chapter3_1;

import java.util.Iterator;

public class LinkedListST<Key, Value> {
    private int N = 0;
    private Node first = null;

    private class Node {
        private Key key;
        private Value value;
        private Node next;

        public Node(Key key, Value val, Node next) {
            this.key = key;
            this.value = val;
            this.next = next;
        }
    }

    public void put(Key key, Value val) {
        Node p;
        for (p = first; p != null && !p.key.equals(key); p = p.next) ;
        if (p != null) {
//            StdOut.println(p.key + ": " + p.value + ", " + key + ": " + val);
            p.value = val;
        } else {
            Node oldfirst = first;
            first = new Node(key, val, oldfirst);
            N++;
        }
    }

    public Value get(Key key) {
        Node p;
        for (p = first; p != null && !p.key.equals(key); p = p.next) ;
        if (p == null) return null;
        else return p.value;
    }

    public void delete(Key key) {
        if (first == null) return;
        if (first.key.equals(key)) {
            first = first.next;
            N--;
            return;
        }
        Node p;
        for (p = first; p.next != null && !p.next.key.equals(key); p = p.next) ;
        if (p.next != null) {
            p.next = p.next.next;
            N--;
        }
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public Iterable<Key> keys() {
        return new KeyIterable();
    }

    private class KeyIterable implements Iterable<Key> {
        public Iterator<Key> iterator() {
            return new KeyIterator();
        }
    }

    private class KeyIterator implements Iterator<Key> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Key next() {
            Key key = current.key;
            current = current.next;
            return key;
        }
    }
}
