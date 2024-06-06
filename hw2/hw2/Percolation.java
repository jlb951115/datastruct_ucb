package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int limation;
    private int[][] array;
    private WeightedQuickUnionUF topDownSet;
    private WeightedQuickUnionUF topSet;
    private int count;
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("Illegal Argument");
        }
        array = new int[N][N];
        limation = N;
        topDownSet = new WeightedQuickUnionUF(N * N + 2);
        topSet = new WeightedQuickUnionUF(N * N + 1);
        for (int i = 0; i < N; i++) {
            topSet.union(i, N * N);
            topDownSet.union(i, N * N);
        }
        for (int i = 0; i < N; i++) {
            topDownSet.union(N * (N - 1) + i, N * N + 1);
        }
        count = 0;
    }

    private int getIndex(int row, int col) {
        return row * limation + col;
    }

    public boolean isOpen(int row, int col) {
        if (row >= limation || col >= limation) {
            throw new IndexOutOfBoundsException("Out of Bounds");
        }
        return array[row][col] == 1;
    }

    public int numberOfOpenSites() {
        return count;
    }

    public boolean percolates() {
        int x = limation * limation;
        return topDownSet.connected(x, x + 1) && count > 0;
    }

    public boolean isFull(int row, int col) {
        if (row >= limation || col >= limation) {
            throw new IndexOutOfBoundsException("Out of Bounds");
        }
        int x = getIndex(row, col);
        return isOpen(row, col) && topSet.connected(x, limation * limation);
    }

    public void open(int row, int col) {
        if (row >= limation || col >= limation) {
            throw new IndexOutOfBoundsException("Out of Bounds");
        }
        if (isOpen(row, col)) {
            return;
        }
        array[row][col] = 1;
        count += 1;
        int index = getIndex(row, col);
        for (int i = row - 1; i <= row + 1; i += 2) {
            if (i < 0 || i >= limation) {
                continue;
            }
            if (isOpen(i, col)) {
                topSet.union(getIndex(i, col), index);
                topDownSet.union(getIndex(i, col), index);
            }
        }
        for (int i = col - 1; i <= col + 1; i += 2) {
            if (i < 0 || i >= limation) {
                continue;
            }
            if (isOpen(row, i)) {
                topSet.union(getIndex(row, i), index);
                topDownSet.union(getIndex(row, i), index);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("finished");
    }
}
