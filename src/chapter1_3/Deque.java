package ch1;

public class Deque<Item> implements Iterable<Item>{
    private Node first, last;
    private class Node {
        public Item item;
        public Node next;
        public Node prev;
    }
    public void pushLeft(Item item){
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        first.prev = null;
        if (oldfirst == null) last = first;
        else oldfirst.prev = first;
        sz ++;
    }
    public void pushRight(Item item){
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        last.prev = oldlast;
        if (oldlast == null) first = last;
        else oldlast.prev = last;
        sz ++;
    }
    public Item popLeft(){
        Item item = first.item;
        first = first.next;
        if (first == null) last = null;
        else first.prev = null;
        sz --;
        return item;
    }
    public Item popRight(){
        Item item = last.item;
        last = last.prev;
        if (last == null) first = null;
        else last.next = null;
        sz --;
        return item;
    }
    public boolean isEmpty(){
        return first == null;
    }
    public int size(){
        return sz;
    }
    public Iterator<Item> iterator(){
        return new DequeueIterator();
    }
    private class DequeueIterator implements Iterator<Item> {
        private Node current = first;
        public boolean hasNext() {
            return current != null;
        }
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}