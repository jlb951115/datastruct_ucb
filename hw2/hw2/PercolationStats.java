package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] experiments;
    private static final double CONSTANT = 1.96;
    private int T;
    private int N;
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("Illegal Argument");
        }
        this.N = N;
        this.T = T;
        experiments = new double[T];
        for (int i = 0; i < T; i++) {
            Percolation p = pf.make(N);
            while (!p.percolates()) {
                int row = StdRandom.uniform(N);
                int col = StdRandom.uniform(N);
                if (!p.isOpen(row, col)) {
                    p.open(row, col);
                }
            }
            experiments[i] = (double) (p.numberOfOpenSites()) / (double) (N * N);
        }
    }

    public double mean() {
        return StdStats.mean(experiments);
    }

    public double stddev() {
        return StdStats.stddev(experiments);
    }

    public double confidenceLow() {
        return this.mean() - (CONSTANT * this.stddev() / Math.sqrt(T));
    }

    public double confidenceHigh() {
        return this.mean() + (CONSTANT * this.stddev() / Math.sqrt(T));
    }

}
