package chapter2_4.EightPuzzle;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class Solver {
    private Node lastNode;
    private boolean solvable = true;

    public Solver(Board initial) {           // find a solution to the initial board (using the A* algorithm)
        if (initial == null)
            throw new java.lang.IllegalArgumentException();
        MinPQ<Node> pq = new MinPQ<>();
        MinPQ<Node> pqModified = new MinPQ<>();
        Node first = new Node(null, 0, initial);
        Node firsttwin = new Node(null, 0, initial.twin());
        pq.insert(first);
        pqModified.insert(firsttwin);
//        StdOut.println(firsttwin.board);
        while (!pq.isEmpty() && !pqModified.isEmpty()) {
//            StdOut.print("Origin-");
            if (stepForward(pq)) break;
//            StdOut.print("Twin-");
            if (stepForward(pqModified)) {
                solvable = false;
                break;
            }
        }
    }

    private boolean stepForward(MinPQ<Node> pq) {
        Node node = pq.delMin();
        if (node.board.isGoal()) {
            lastNode = node;
            return true;
        }
//        StdOut.println("Step:");
//        StdOut.println(node.board);
        Iterable<Board> neighbors = node.board.neighbors();
        for (Board board : neighbors) {
//            StdOut.println(board);
            if (node.predecessor != null && board.equals(node.predecessor.board)) continue;
            Node next = new Node(node, node.moves + 1, board);
            pq.insert(next);
        }
        return false;
    }

    public boolean isSolvable() {            // is the initial board solvable?
        return solvable;
    }

    public int moves() {                     // min number of moves to solve initial board; -1 if unsolvable
        return isSolvable() ? this.lastNode.moves : -1;
    }

    public Iterable<Board> solution() {      // sequence of boards in a shortest solution; null if unsolvable
        return isSolvable() ? new Solution(lastNode) : null;
    }

    private class Solution implements Iterable<Board> {
        Board[] steps;

        public Solution(Node lastStep) {
            int stepNum = lastStep.moves + 1;
            steps = new Board[stepNum];
            Node step = lastStep;
            int i;
            for (i = stepNum - 1; i >= 0 && step != null; i--, step = step.predecessor) {
                steps[i] = step.board;
            }
            assert (i < 0 && step == null);
        }

        public Iterator<Board> iterator() {
            return new SolutionIterator(steps);
        }
    }

    private class SolutionIterator implements Iterator<Board> {
        private Board[] steps;
        private int current = 0;

        public SolutionIterator(Board[] steps) {
            this.steps = steps;
        }

        public boolean hasNext() {
            return current < steps.length;
        }

        public Board next() {
            return steps[current++];
        }
    }

    public static void main(String[] args) {

        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }

    private class Node implements Comparable<Node> {
        public Node predecessor;
        public int moves;
        public Board board;
        private int manhattan;

        public Node(Node predecessor, int moves, Board board) {
            this.predecessor = predecessor;
            this.moves = moves;
            this.board = board;
            this.manhattan = board.manhattan();
        }

        public int compareTo(Node that) {
            int prior1 = manhattan + moves;
            int prior2 = that.manhattan + that.moves;
            return prior1 - prior2;
        }
    }
}
