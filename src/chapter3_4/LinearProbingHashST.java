package chapter3_4;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

public class LinearProbingHashST<Key, Value> {
    private final int INIT_CAPACITY = 4;
    private int m = INIT_CAPACITY, n;
    private Key[] keys = (Key[]) new Object[INIT_CAPACITY];
    private Value[] values = (Value[]) new Object[INIT_CAPACITY];

    private void resize(int capacity) {
        Key[] oldkeys = keys;
        Value[] oldvalues = values;
        int oldn = n;
        keys = (Key[]) new Object[capacity];
        values = (Value[]) new Object[capacity];
        m = capacity;
        for (int i = 0; i < oldkeys.length; i++)
            if (oldkeys[i] != null) put(oldkeys[i], oldvalues[i]);
        n = oldn;
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % m;
    }

    public void put(Key key, Value value) {
        if (key == null) throw new java.lang.IllegalArgumentException();
        int i = hash(key);
        for (; keys[i] != null; i = (i + 1) % m)
            if (key.equals(keys[i])) break;
        if (keys[i] == null) n++;
        keys[i] = key;
        values[i] = value;
        if (n > m / 2) resize(m * 2);
    }

    public Value get(Key key) {
        if (key == null) throw new java.lang.IllegalArgumentException();
        for (int i = hash(key); keys[i] != null; i = (i + 1) % m)
            if (key.equals(keys[i])) return values[i];
        return null;
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException();
        return get(key) != null;
    }

    public void delete(Key key) {
        if (key == null) throw new java.lang.IllegalArgumentException();
        int i = hash(key);
        for (; keys[i] != null; i = (i + 1) % m)
            if (key.equals(keys[i])) break;
        if (keys[i] != null) n--;
        for (; keys[i] != null; i = (i + 1) % m) {
            keys[i] = keys[(i + 1) % m];
            values[i] = values[(i + 1) % m];
        }
        if (n > 0 && n <= m / 8) resize(m / 2);
    }

    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<>();
        for (int i = 0; i < m; i++)
            if (keys[i] != null) queue.enqueue(keys[i]);
        return queue;
    }

    public static void main(String[] args) {
        LinearProbingHashST<String, Integer> st = new LinearProbingHashST<>();
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
