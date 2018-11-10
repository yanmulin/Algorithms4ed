package ch1;

import edu.princeton.cs.algs4.StdOut;

public class Node {
    public int item;
    public Node next;

    // 1.3.19 删除链表的尾节点
    public static Node rmtail(Node first) {
        assert first != null;
        if (first.next == null) return null; // only first
        Node x = first;
        while (x.next.next != null) x = x.next;
        x.next = null;
        return first;
    }

    // 1.3.20 删除链表的第k个元素
    public static Node delete(Node first, int k) {
        assert first != null;
        assert k > 0;
        Node dummy = new Node();
        dummy.next = first;
        Node x = dummy;
        while (k-- > 1 && x.next != null) { // 前进k-1次
            x = x.next;
        }
        if (x.next != null) x.next = x.next.next;
        return dummy.next;
    }

    // 1.3.21 若存在item值为key的元素则返回true，若不存在则返回false
    public static boolean find(Node first, int key) {
        assert first != null;
        Node x = first;
        while (x.next != null) {
            if (x.item == key) return true;
        }
        return false;
    }

    // 1.3.24 删除该节点的后续节点
    public static void removeAfter(Node node) {
        assert node != null;
        if (node.next == null) return;
        node.next = node.next.next;
    }

    // 1.3.25 使第二个节点插入链表，使之成为第一个节点的后续节点
    public static void insertAfter(Node n1, Node n2) {
        assert n1 != null && n2 != null;
        n2.next = n1.next;
        n1.next = n2;
    }

    // 1.3.26 删除链表所有中item域为key的节点
    public static Node remove(Node first, int key) {
        assert first != null;
        while (first != null && first.item == key)
            first = first.next;
        if (first == null) return null;
        Node x = first;
        while (x.next != null) {
            if (x.next.item == key)
                x.next = x.next.next;
        }
        return first;
    }

    // 1.3.27 返回first开始的链表中的最大值(假设Item为int)
    public static int max(Node first) {
        assert first != null;
        int max_val = first.item;
        Node x = first.next;
        while (x != null) {
            if (x.item > max_val) max_val = x.item;
            x = x.next;
        }
        return max_val;
    }

    // 1.3.28 用递归的方法实现上一题
    public static int maxR(Node first) {
        assert first != null;
        if (first.next == null) return first.item;
        else {
            int val = maxR(first.next);
            return val > first.item ? val : first.item;
        }
    }

    // 1.3.30 接受链表的首节点，将链表反转并返回链表的首节点
    public static Node reverse(Node first) {
        // assert first != null;
        Node x = null, y = first;
        while (y != null) {
            first = y;
            y = first.next;
            first.next = x;
            x = first;
        }
        return first;
    }

    // 迭代头插法实现
    public static Node reverseH(Node first) {
        if (first == null) return null;
        Node x, oldfirst = first;
        while (oldfirst.next != null) {
            x = oldfirst.next;
            oldfirst.next = x.next;
            x.next = first;
            first = x;
        }
        return first;
    }

    // 用递归的方法实现上一题
    public static Node reverseR(Node first) {
        assert first != null;
        if (first.next == null) return first;
        Node second = first.next;
        Node reverse = reverseR(second);
        second.next = first;
        first.next = null;
        return reverse;
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5, 6};
        StdOut.println("Test reverse");
        Node node = buildList(a);
        printList(node);
        node = reverseH(node);
        printList(node);

        StdOut.println("Test rmtail, delete");
        node = buildList(a);
        printList(node);
        StdOut.println("rmtail");
        node = rmtail(node);
        printList(node);
        StdOut.println("delete 1");
        node = delete(node, 1);
        printList(node);
        StdOut.println("delete 4");
        node = delete(node, 4);
        printList(node);
        StdOut.println("delete 2");
        node = delete(node, 2);
        printList(node);
        StdOut.println("remove 4");
        node = remove(node, 4);
        printList(node);

    }

    public static Node buildList(int[] list) {
        Node dummy = new Node();
        Node x = dummy;
        for (int item : list) {
            x.next = new Node();
            x = x.next;
            x.item = item;
            x.next = null;
        }
        return dummy.next;
    }

    public static void printList(Node first) {
        assert first != null;
        Node x = first;
        while (x != null) {
            StdOut.print(x.item + " ");
            x = x.next;
        }
        StdOut.println();
    }
}
