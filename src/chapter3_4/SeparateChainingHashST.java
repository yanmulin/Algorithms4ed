package chapter3_4;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

public class SeparateChainingHashST<Key, Value> {
    private final int INIT_CAPACITY = 4;
    private int m = INIT_CAPACITY;
    private int n;
    private Node[] st = new Node[INIT_CAPACITY];

    private static class Node {
        private Object key;
        private Object value;
        private Node next;

        public Node(Object key, Object value) {
            this.key = key;
            this.value = value;
        }
    }

    private void resize(int capacity) {
        if (capacity <= INIT_CAPACITY) throw new java.lang.IllegalArgumentException();
        Node[] old = st;
        st = (Node[]) new Object[capacity];
        m = capacity;
        for (int i = 0; i < old.length; i++) {
            for (Node x = old[i]; x != null; x = x.next)
                put((Key) x.key, (Value) x.value);
        }
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % m;
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public boolean contains(Key key) {
        if (key == null) throw new java.lang.IllegalArgumentException();
        return get(key) != null;
    }

    public void put(Key key, Value value) {
        if (key == null) throw new java.lang.IllegalArgumentException();
        if (value == null) {
            delete(key);
            return;
        }
        int i = hash(key);
        for (Node x = st[i]; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.value = value;
                return;
            }
        }
        Node old = st[i];
        st[i] = new Node(key, value);
        st[i].next = old;
        n++;
        if (n > 10 * m) resize(m * 2);
    }

    public Value get(Key key) {
        int i = hash(key);
        for (Node x = st[i]; x != null; x = x.next)
            if (key.equals(x.key)) return (Value) x.value;
        return null;
    }

    public void delete(Key key) {
        if (key == null) throw new java.lang.IllegalArgumentException();
        int i = hash(key);
        Node dummy = new Node(null, null);
        dummy.next = st[i];
        for (Node x = dummy; x.next != null; x = x.next) {
            if (key.equals(x.next.key)) {
                x.next = x.next.next;
                n--;
                break;
            }
        }
        st[i] = dummy.next;
        if (m > INIT_CAPACITY && n < 2 * m) resize(m / 2);
    }

    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<>();
        for (int i = 0; i < m; i++) {
            for (Node x = st[i]; x != null; x = x.next)
                queue.enqueue((Key) x.key);
        }
        return queue;
    }

    public static void main(String[] args) {
        SeparateChainingHashST<String, Integer> st = new SeparateChainingHashST<>();
        String str = "S E A R C H E X A M P L E";
        String[] keys = str.split(" ");

        StdOut.println("TEST put()");
        StdOut.println("----------------------------------");
        for (int i = 0; i < keys.length; i++)
            st.put(keys[i], i);
        StdOut.println();

        StdOut.println("TEST keys(), get()");
        StdOut.println("----------------------------------");
        Iterable<String> allkeys = st.keys();
        for (String key : allkeys)
            StdOut.print(key + ": " + st.get(key) + ", ");
        StdOut.println();
        StdOut.println();

        StdOut.println("TEST delete()");
        StdOut.println("----------------------------------");
        st.delete("R");
        StdOut.print("After deleting 'R', size = " + st.size() + ", ");
        allkeys = st.keys();
        for (String key : allkeys)
            StdOut.print(key + ", ");
        StdOut.println();
        st.delete("A");
        StdOut.print("After deleting 'A', size = " + st.size() + ", ");
        allkeys = st.keys();
        for (String key : allkeys)
            StdOut.print(key + ", ");
        StdOut.println();
    }
}
