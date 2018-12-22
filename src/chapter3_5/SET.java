package chapter3_4;

public class SET<Key extends Comparable<Key>> implements Iterable<Key> {
    public void add(Key key) {}
    public void remove(Key key) {}
    public boolean contain(Key key) {}
    public boolean isEmpty() {}
    public int size() {}
    public Iterator<Key> iterator() {}
}