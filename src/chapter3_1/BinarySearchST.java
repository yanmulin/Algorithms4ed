package chapter3_1;

import java.util.Iterator;

public class BinarySearchST<Key extends Comparable<Key>, Value> {
    Key[] keys = (Key[]) new Comparable[1];
    Value[] values = (Value[]) new Comparable[1];
    int N = 0;

    private void resize(int capacity) {
        Key[] oldkeys = keys;
        Value[] oldvals = values;
        keys = (Key[]) new Comparable[capacity];
        values = (Value[]) new Comparable[capacity];
        for (int i = 0; i < N; i++) {
            keys[i] = oldkeys[i];
            values[i] = oldvals[i];
        }
    }

    private boolean less(Comparable v, Comparable w) {
        return w.compareTo(v) > 0;
    }

    private int search(Key key) {
        int lo = 0, hi = N - 1;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (less(key, keys[mid])) hi = mid - 1;
            else if (less(keys[mid], key)) lo = mid + 1;
            else return mid;
        }
        return lo;
    }

    public void put(Key key, Value val) {
        int pos = search(key);
        if (pos == N || less(key, keys[pos])) {
            // new key
            if (keys.length == N) resize(N * 2);
            for (int i = N; i > pos; i--) {
                keys[i] = keys[i - 1];
                values[i] = values[i - 1];
            }
            keys[pos] = key;
            values[pos] = val;
            N++;
        } else {
            values[pos] = val;
        }
    }

    public Value get(Key key) {
        int pos = search(key);
        if (pos == N || less(key, keys[pos])) return null;
        else return values[pos];
    }

    public void delete(Key key) {
        int pos = search(key);
        if (pos == N || less(keys[pos], key)) return;
        for (int i = pos; i < N; i++) {
            keys[i] = keys[i + 1];
            values[i] = values[i + 1];
        }
        N--;
        keys[N] = null;
        values[N] = null;
        if (N < keys.length / 4) resize(keys.length / 2);
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
        return new KeysIterable();
    }

    private class KeysIterable implements Iterable<Key> {
        public Iterator<Key> iterator() {
            return new KeysIterator();
        }
    }

    private class KeysIterator implements Iterator<Key> {
        private int current = 0;

        public boolean hasNext() {
            return current < N;
        }

        public Key next() {
            return keys[current++];
        }
    }
}
