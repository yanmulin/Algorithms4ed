package chapter2_4.EightPuzzle;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class Board {
    private final int[][] blocks;
    private int hamming_val;
    private int manhattan_val;
    private int blankIdx;

    public Board(int[][] blocks) {           // construct a board from an n-by-n array of blocks
        this.blocks = blocks;
        hamming_val = manhattan_val = 0;
        int n = dimension();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (blocks[i][j] == 0) blankIdx = i * n + j;
                else if (blocks[i][j] != i * n + j + 1) {
                    hamming_val++;
                    int row = (blocks[i][j] - 1) / n, col = (blocks[i][j] - 1) % n;
                    manhattan_val += Math.abs(i - row) + Math.abs(j - col);
                }
            }
        }
    }

    // (where blocks[i][j] = block in row i, column j)
    public int dimension() {                 // board dimension n
        return blocks.length;
    }

    public int hamming() {                   // number of blocks out of place
        return hamming_val;
    }

    public int manhattan() {                 // sum of Manhattan distances between blocks and goal
        return manhattan_val;
    }

    public boolean isGoal() {                // is this board the goal board?
        return hamming() == 0;
    }

    public Board twin() {                    // a board that is obtained by exchanging any pair of blocks
        int[][] copy = copyBlocks(blocks);
        int n = dimension();
        int i = 0;
        while (copy[i / n][i % n] == 0) i++;
        int j = i + 1;
        while (copy[j / n][j % n] == 0) j++;
        exch(copy, i, j);
        return new Board(copy);
    }

    private static void exch(int[][] blocks, int i, int j) {
        assert (blocks.length == blocks[0].length);
        int n = blocks.length;
        int ri = i / n, rj = j / n;
        int ci = i % n, cj = j % n;
        int tmp = blocks[ri][ci];
        blocks[ri][ci] = blocks[rj][cj];
        blocks[rj][cj] = tmp;
    }

    public boolean equals(Object y) {        // does this board equal y?
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;
        Board that = (Board) y;
        int n = dimension();
        if (n != that.dimension()) return false;
        if (manhattan() != that.manhattan()) return false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (blocks[i][j] != that.blocks[i][j])
                    return false;
            }
        }
        return true;
    }

    public Iterable<Board> neighbors() {     // all neighboring boards
        return new NeighborBoard(this, blankIdx);
    }

    private class NeighborBoard implements Iterable<Board> {
        private Board board;
        private int blankIdx;

        public NeighborBoard(Board board, int blankIdx) {
            this.board = board;
            this.blankIdx = blankIdx;
        }

        public Iterator<Board> iterator() {
            return new NeighborsIterator(board, blankIdx);
        }
    }

    private int[][] copyBlocks(int[][] blocks) {
        int n = blocks.length;
        int[][] copy = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                copy[i][j] = blocks[i][j];
        }
        return copy;
    }

    private class NeighborsIterator implements Iterator<Board> {
        private Board[] boards;
        private int n;
        private int current;


        public NeighborsIterator(Board board, int blankIdx) {
            int dim = board.dimension();
            boards = new Board[4];
            n = 0;
            int bi = blankIdx / dim, bj = blankIdx % dim;
            if (bi > 0) {
                int[][] blocks = copyBlocks(board.blocks);
                exch(blocks, blankIdx, blankIdx - dim);
                boards[n++] = new Board(blocks);
            }
            if (bi < dimension() - 1) {
                int[][] blocks = copyBlocks(board.blocks);
                exch(blocks, blankIdx, blankIdx + dim);
                boards[n++] = new Board(blocks);
            }
            if (bj > 0) {
                int[][] blocks = copyBlocks(board.blocks);
                exch(blocks, blankIdx, blankIdx - 1);

                boards[n++] = new Board(blocks);
            }
            if (bj < dimension() - 1) {
                int[][] blocks = copyBlocks(board.blocks);
                exch(blocks, blankIdx, blankIdx + 1);
                boards[n++] = new Board(blocks);
            }
            current = 0;
        }

        public boolean hasNext() {
            return current < n;
        }

        public Board next() {
            return boards[current++];
        }
    }

    public String toString() {               // string representation of this board (in the output format specified below)
        int n = dimension();
        String ret = String.format("%d\n", n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ret += String.format(" %d ", blocks[i][j]);
            }
            ret += "\n";
        }
        return ret;
    }

    private static boolean testPriorityCaluation() {
        int[][] blocks1 = {{5, 1, 8}, {2, 7, 3}, {4, 0, 6}};
        Board board1 = new Board(blocks1);
        if (board1.hamming() != 8) {
            StdOut.println("Board1 hamming value expected 8, auctually " + board1.hamming());
            return false;
        }
        if (board1.manhattan() != 13) {
            StdOut.println("Board1 hmanhattan value expected 13, auctually " + board1.manhattan());
            return false;
        }
        int[][] blocks2 = {{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};
        Board board2 = new Board(blocks2);
        if (board2.hamming() != 5) {
            StdOut.println("Board2 hamming value expected 5, auctually " + board2.hamming());
            return false;
        }
        if (board2.manhattan() != 10) {
            StdOut.println("Board2 hmanhattan value expected 10, auctually " + board2.manhattan());
            return false;
        }
        return true;
    }

    private static boolean testEquals() {
        int[][] blocks1 = {{3, 5, 7}, {8, 2, 4}, {1, 6, 0}};
        int[][] blocks2 = {{3, 5, 7}, {8, 2, 4}, {1, 6, 0}};
        Board board1 = new Board(blocks1);
        Board board2 = new Board(blocks2);
        if (board1.equals(board2) == false) {
            StdOut.println("test equals() failes.");
            return false;
        }
        if (board1.equals("Hello") == true) {
            StdOut.println("test equals() failes.");
            return false;
        }
        return true;
    }

    private static boolean testIsGoal() {
        int[][] blocks1 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] blocks2 = {{3, 5, 7}, {8, 2, 4}, {1, 6, 0}};
        Board board1 = new Board(blocks1);
        Board board2 = new Board(blocks2);
        if (board1.isGoal() == false) {
            StdOut.println("isGoal() failed.");
            return false;
        }
        if (board2.isGoal() == true) {
            StdOut.println("isGoal() failed.");
            return false;
        }
        return true;
    }

    private static void testNeighbors() {
        int[][] blocks1 = {{3, 5, 7}, {8, 0, 4}, {1, 6, 2}};
        Board board = new Board(blocks1);
        StdOut.println("Origin:");
        StdOut.println(board);
        StdOut.println("Neighbors:");
        Iterable<Board> neighbors = board.neighbors();
        for (Board x : neighbors) {
            StdOut.print(x);
        }
    }

    public static void main(String[] args) { // unit tests (not graded)
        testPriorityCaluation();
        testEquals();
        testIsGoal();
        testNeighbors();
    }
}
