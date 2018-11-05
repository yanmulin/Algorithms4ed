package ch1;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class RandomQueue<Item> implements Iterable<Item> {
    private Item items[] = (Item[]) new Object[1];
    private int sz;
    private int head, tail;

    private void resize(int capacity) {
        Item[] old = items;
        items = (Item[]) new Object[capacity];
        for (int i = 0; i < sz; i++) {
            items[i] = old[(head + i) % old.length];
        }
        head = 0;
        tail = sz;
    }

    // 添加一个元素
    public void enqueue(Item item) {
        if ((tail + 1) % items.length == head)
            resize(items.length * 2);
        items[tail] = item;
        tail = (tail + 1) % items.length;
        sz++;
    }

    // 随机返回一个元素并删除
    public Item dequeue() {
        int r;
        if (sz == 1) r = 0;
        else r = StdRandom.uniform(1, sz);
        // 交换 items[(head + r) % items.length] 和 items[head]
        Item item = items[(head + r) % items.length];
        items[(head + r) % items.length] = items[head];
        items[head] = null;
        head = (head + 1) % items.length;

        if (sz <= items.length / 4)
            resize(items.length / 2);
        sz--;
        return item;
    }

    // 随机返回一个元素但不删除
    public Item sample() {
        int r = StdRandom.uniform(0, sz);
        return items[(head + r) % items.length];
    }

    // 判断队列是否为空
    public boolean isEmpty() {
        return head == tail;
    }

    // 返回迭代器
    public Iterator<Item> iterator() {
        return new RandomQueueIterator();
    }

    private class RandomQueueIterator implements Iterator<Item> {
        private int current;
        private Item[] data;

        public RandomQueueIterator() {
            data = (Item[]) new Object[1];
            for (int i = 0; i < sz; i++)
                data[i] = items[(head + i) % items.length];
            StdRandom.shuffle(data);
        }

        public boolean hasNext() {
            return current < sz;
        }

        public Item next() {
            return data[current++];
        }
    }

    public static void main(String[] args) {
        RandomQueue q = new RandomQueue();
        for (int i = 0; i < 52; i++)
            q.enqueue(i);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++)
                StdOut.print(q.dequeue() + "\t");
            StdOut.println();
        }
    }
}
