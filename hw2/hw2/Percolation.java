package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int Limation;
    private int[][] Array;
    private WeightedQuickUnionUF TopSet;
    private WeightedQuickUnionUF RowpSet;
    private int count;
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("Illegal Argument");
        }
        Array = new int[N][N];
        Limation = N;
        TopSet = new WeightedQuickUnionUF(N * N + 1);
        RowpSet = new WeightedQuickUnionUF(N);
        for (int i = 0; i < N; i++) {
            TopSet.union(i, N * N);
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
        return RowpSet.connected(0, Limation - 1) && count > 0;
    }

    public boolean isFull(int row, int col) {
        if (row >= Limation || col >= Limation) {
            throw new IndexOutOfBoundsException("Out of Bounds");
        }
        int x = getIndex(row, col);
        return TopSet.connected(x, Limation * Limation) && isOpen(row, col);
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
                TopSet.union(getIndex(i, col), index);
                RowpSet.union(row, i);
            }
        }
        for (int i = col - 1; i <= col + 1; i += 2) {
            if (i < 0 || i >= Limation) {
                continue;
            }
            if (isOpen(row, i)) {
                TopSet.union(getIndex(row, i), index);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("finished");
    }
}
