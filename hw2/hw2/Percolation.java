package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int Limation;
    private int[][] Array;
    private WeightedQuickUnionUF Set;
    private int count;
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("Illegal Argument");
        }
        Array = new int[N][N];
        Limation = N;
        Set = new WeightedQuickUnionUF(N * N + 2);
        for (int i = 0; i < N; i++) {
            Set.union(i, N * N);
        }
        for (int i = 0; i < N; i++) {
            Set.union(N * (N - 1) + i, N * N + 1);
        }
        count = 0;
    }

    private int getIndex(int row, int col) {
        return row * Limation + col;
    }

    public boolean isOpen(int row, int col) {
        if (row >= Limation || col >= Limation) {
            throw new IndexOutOfBoundsException("Out of Bounds");
        }
        return Array[row][col] == 1;
    }

    public int numberOfOpenSites() {
        return count;
    }

    public boolean percolates() {
        int x = Limation * Limation;
        return Set.connected(x, x + 1) && count > 0;
    }

    public boolean isFull(int row, int col) {
        if (row >= Limation || col >= Limation) {
            throw new IndexOutOfBoundsException("Out of Bounds");
        }
        return Set.connected(getIndex(row, col), Limation * Limation) && isOpen(row, col);
    }

    public void open(int row, int col) {
        if (row >= Limation || col >= Limation) {
            throw new IndexOutOfBoundsException("Out of Bounds");
        }
        if (isOpen(row, col)) {
            return;
        }
        Array[row][col] = 1;
        count += 1;
        int index = getIndex(row, col);
        for (int i = row - 1; i <= row + 1; i += 2) {
            if (i < 0 || i >= Limation) {
                continue;
            }
            if (isOpen(i, col)) {
                Set.union(getIndex(i, col), index);
            }
        }
        for (int i = col - 1; i <= col + 1; i += 2) {
            if (i < 0 || i >= Limation) {
                continue;
            }
            if (isOpen(row, i)) {
                Set.union(getIndex(row, i), index);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("finished");
    }
}
