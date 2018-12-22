package chapter3_1;

import chapter1_3.Queue;
import chapter1_3.Stack;
import edu.princeton.cs.algs4.StdOut;

public class NonrecursiveTraverse {
    public static class Node {
        public Integer value;
        public Node left, right;

        public Node(Integer value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    public static void handle(Node x) {
        StdOut.print(x.value + " ");
    }

    public static void preOrder(Node root) {
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node x = stack.pop();
            handle(x);
            if (x.right != null) stack.push(x.right);
            if (x.left != null) stack.push(x.left);
        }
    }

    public static void inOrder(Node root) {
        Stack<Node> stack = new Stack<>();
        Node x = root;
        while (x != null || !stack.isEmpty()) {
            if (x != null) {
                stack.push(x);
                x = x.left;
            } else {
                x = stack.pop();
                handle(x);
                x = x.right;
            }
        }
    }

    public static void postOrder1(Node root) {
        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();
        stack1.push(root);
        while (!stack1.isEmpty()) {
            Node x = stack1.pop();
            stack2.push(x);
            if (x.left != null) stack1.push(x.left);
            if (x.right != null) stack1.push(x.right);
        }
        while (!stack2.isEmpty()) handle(stack2.pop());
    }

    public static void postOrder2(Node root) {
        Stack<Node> stack = new Stack<>();
        Node x = root;
        while (x != null || !stack.isEmpty()) {
            while (x != null) {
                if (x.right != null) stack.push(x.right);
                stack.push(x);
                x = x.left;
            }
            x = stack.pop();
            if (!stack.isEmpty() && stack.top() == x.right) {
                stack.pop();
                stack.push(x);
                x = x.right;
            } else {
                handle(x);
                x = null;
            }
        }
    }

    public static void levelOrder(Node root) {
        Queue<Node> queue = new Queue<>();
        queue.enqueue(root);
        while (!queue.isEmpty()) {
            Node x = queue.dequeue();
            handle(x);
            if (x.left != null) queue.enqueue(x.left);
            if (x.right != null) queue.enqueue(x.right);
        }
    }

    public static void main(String[] args) {
        Node n4 = new Node(4, null, null);
        Node n5 = new Node(5, null, null);
        Node n6 = new Node(6, null, null);
        Node n7 = new Node(7, null, null);
        Node n2 = new Node(2, n4, n5);
        Node n3 = new Node(3, n6, n7);
        Node n1 = new Node(1, n2, n3);
        preOrder(n1);
        StdOut.println();
        inOrder(n1);
        StdOut.println();
        postOrder1(n1);
        StdOut.println();
        postOrder2(n1);
        StdOut.println();
        levelOrder(n1);
        StdOut.println();
    }
}
