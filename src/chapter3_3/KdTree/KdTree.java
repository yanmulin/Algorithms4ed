package chapter3_1.KdTree;

import edu.princeton.cs.algs4.*;


public class KdTree {
    private Node root;
    private final boolean RED = true;
    private final boolean BLACK = false;

    private class Node {
        private Point2D p;
        private int size;
        private Node left, right;

        public Node(Point2D p, int size) {
            this.p = p;
            this.size = size;
        }
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) return 0;
        else return x.size;
    }

    public void insert(Point2D p) {
        if (p == null) throw new java.lang.IllegalArgumentException();
        root = insert(root, p, 0);
    }

    private Node insert(Node h, Point2D p, int level) {
        if (h == null) return new Node(p, 1);
        if (Math.abs(h.p.x() - p.x()) < 1.0e-6 && Math.abs(h.p.y() - p.y()) < 1.0e-6) return h;

        if (level % 2 == 0) {
            if (h.p.x() >= p.x()) h.left = insert(h.left, p, level + 1);
            else h.right = insert(h.right, p, level + 1);
        } else {
            if (h.p.y() >= p.y()) h.left = insert(h.left, p, level + 1);
            else h.right = insert(h.right, p, level + 1);
        }

        h.size = size(h.left) + size(h.right) + 1;

        return h;
    }

    public boolean contains(Point2D p) {
        if (p == null) throw new java.lang.IllegalArgumentException();
        return search(p) != null;
    }

    private Node search(Point2D p) {
        Node h = root;
        int level = 0;
        while (h != null) {
            if (Math.abs(h.p.x() - p.x()) < 1.0e-6 && Math.abs(h.p.y() - p.y()) < 1.0e-6) return h;
            if (level++ % 2 == 0) {
                if (h.p.x() >= p.x()) h = h.left;
                else h = h.right;
            } else {
                if (h.p.y() >= p.y()) h = h.left;
                else h = h.right;
            }
        }
        return h;
    }

    public void draw() {
        StdDraw.setScale(0.0, 1.0);
        StdDraw.setPenRadius(0.01);
        InOrderDraw(root);
    }

    private void InOrderDraw(Node x) {
        if (x == null) return;
        InOrderDraw(x.left);
        x.p.draw();
//        StdOut.println(x.p);
        InOrderDraw(x.right);
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new java.lang.IllegalArgumentException();
        Queue<Point2D> queue = new Queue<>();
        range(root, 0, rect, queue);
        return queue;
    }

    private void range(Node h, int level, RectHV rect, Queue<Point2D> queue) {
        if (h == null) return;
        if (rect.contains(h.p)) {
            queue.enqueue(h.p);
        }

        if (level % 2 == 0) {
            if (rect.xmin() > h.p.x()) range(h.right, level + 1, rect, queue);
            else if (rect.xmax() < h.p.x()) range(h.left, level + 1, rect, queue);
            else {
                range(h.left, level + 1, rect, queue);
                range(h.right, level + 1, rect, queue);
            }
        } else {
            if (rect.ymin() > h.p.y()) range(h.right, level + 1, rect, queue);
            else if (rect.ymax() < h.p.y()) range(h.left, level + 1, rect, queue);
            else {
                range(h.left, level + 1, rect, queue);
                range(h.right, level + 1, rect, queue);
            }
        }
    }

    public Point2D nearest(Point2D p) {
        if (p == null) throw new java.lang.IllegalArgumentException();
        return nearest(root, p, null, 0);
    }

    private Point2D nearest(Node h, Point2D p, Point2D found, int level) {
        if (h == null) return found;
//        StdOut.println("NEAREST: " + h.p);
        if (found == null || p.distanceSquaredTo(h.p)
                < p.distanceSquaredTo(found))
            found = h.p;

        if (level % 2 == 0) {
            if (p.x() >= h.p.x()) {
                //if (p.x() - h.p.x() < p.distanceTo(found))
                found = nearest(h.right, p, found, level + 1);
                if (p.x() - h.p.x() < p.distanceTo(found))
                    found = nearest(h.left, p, found, level + 1);
            } else {
                // if (h.p.x() - p.x() < p.distanceTo(found))
                found = nearest(h.left, p, found, level + 1);
                if (h.p.x() - p.x() < p.distanceTo(found))
                    found = nearest(h.right, p, found, level + 1);
            }
        } else {
            if (p.y() >= h.p.y()) {
                //if (p.y() - h.p.y() < p.distanceTo(found))
                found = nearest(h.right, p, found, level + 1);
                if (p.y() - h.p.y() < p.distanceTo(found))
                    found = nearest(h.left, p, found, level + 1);
            } else {
                //if (h.p.y() - p.y() < p.distanceTo(found))
                found = nearest(h.left, p, found, level + 1);
                if (h.p.y() - p.y() < p.distanceTo(found))
                    found = nearest(h.right, p, found, level + 1);
            }
        }

        return found;
    }

    public static void main(String[] args) {
        KdTree kdt = new KdTree();

        StdOut.println("TEST insert()");
        StdOut.println("--------------------------------");
        kdt.insert(new Point2D(0.7, 0.2));
        kdt.insert(new Point2D(0.5, 0.4));
        kdt.insert(new Point2D(0.2, 0.3));
        kdt.insert(new Point2D(0.4, 0.7));
        kdt.insert(new Point2D(0.9, 0.6));
        StdOut.println("size: " + kdt.size());
        StdOut.println();

//        kdt.draw();

        StdOut.println("TEST range()");
        StdOut.println("--------------------------------");
        RectHV rect = new RectHV(0.378, 0.55, 0.805, 0.962);
//        rect.draw();
        Iterable<Point2D> points = kdt.range(rect);
        for (Point2D point : points)
            StdOut.print(point + " ");
        StdOut.println();
        StdOut.println();

        StdOut.println("TEST nearest()");
        StdOut.println("--------------------------------");
        StdOut.println("nearest: " + kdt.nearest(new Point2D(0.5, 0.854)));
        StdOut.println();

        StdOut.println("TEST random insert()");
        StdOut.println("--------------------------------");
        kdt = new KdTree();
        int N = 10;
        Point2D[] pointSet = new Point2D[N];
        for (int i = 0; i < N; i++) {
            pointSet[i] = new Point2D(StdRandom.uniform(20) / 20.0, StdRandom.uniform(20) / 20.0);
            StdOut.println(pointSet[i]);
            kdt.insert(pointSet[i]);
            if (kdt.size() != i + 1) {
                StdOut.println("Failed at trail #" + i + ", " + pointSet[i]);
                break;
            }
        }
        for (int i = 1; i <= 10000; i++) {
            kdt.insert(pointSet[i % N]);
            if (kdt.size() != N) {
                StdOut.println("Failed at trail i = " + i + ", size = " + kdt.size() + ", " + pointSet[i % N]);
                break;
            }
        }

    }
}
