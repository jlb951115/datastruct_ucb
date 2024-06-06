package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int Limation;
    private int[][] Array;
    private WeightedQuickUnionUF TopDownSet;
    private WeightedQuickUnionUF TopSet;
    private int count;
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("Illegal Argument");
        }
        Array = new int[N][N];
        Limation = N;
        TopDownSet = new WeightedQuickUnionUF(N * N + 2);
        TopSet = new WeightedQuickUnionUF(N * N + 1);
        for (int i = 0; i < N; i++) {
            TopSet.union(i, N * N);
            TopDownSet.union(i, N * N);
        }
        for (int i = 0; i < N; i++) {
            TopDownSet.union(N * (N - 1) + i, N * N + 1);
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
        return TopDownSet.connected(x, x + 1) && count > 0;
    }

    public boolean isFull(int row, int col) {
        if (row >= Limation || col >= Limation) {
            throw new IndexOutOfBoundsException("Out of Bounds");
        }
        int x = getIndex(row, col);
        return isOpen(row, col) && TopSet.connected(x, Limation * Limation);
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
                TopDownSet.union(getIndex(i, col), index);
            }
        }
        for (int i = col - 1; i <= col + 1; i += 2) {
            if (i < 0 || i >= Limation) {
                continue;
            }
            if (isOpen(row, i)) {
                TopSet.union(getIndex(row, i), index);
                TopDownSet.union(getIndex(row, i), index);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("finished");
    }
}
