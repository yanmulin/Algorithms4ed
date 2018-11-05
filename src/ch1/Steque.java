package ch1;

public class Steque<Item> implements Iterable<Item> {
    private Node first, last;
    private int sz;
    private class Node {
        public Item item;
        public Node next;
    }
    // 尾部插入
    public void enqueue(Item item){
        Node oldlast = last;
        last = new Node();
        last.item = item;
        if (oldlast == null) first = last;
        else oldlast.next = last;
        sz ++;
    }
    // 头部删除
    public Item dequeue(){
        return pop();
    }
    // 头部插入
    public void push(Item item){
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        if (oldfirst == null) last = first;
        sz ++;
    }
    // 头部删除
    public Item pop(){
        assert first != null;
        Item item = first.item;
        first = first.next;
        if (first == null) last = null;
        return item;
    }
    // 由于效率问题，单向链表一般不实现尾部删除
    
    public int size(){
        return sz;
    }
    public Iterator<Item> iterator(){
        return new StequeIterator();
    }
    private class StequeIterator implements Iterator<Item> {
        private Node current = first;
        public boolean hasNext(){
            return current != null;
        }
        public Item next(){
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}