package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private Percolation[] experiments;
    private static final double CONSTANT = 1.96;
    private int T;
    private int N;
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("Illegal Argument");
        }
        this.N = N;
        this.T = T;
        experiments = new Percolation[T];
        for (int i = 0; i < T; i++) {
            experiments[i] = pf.make(N);
        }
        for (int i = 0; i < T; i++) {
            while (!experiments[i].percolates()) {
                int row = StdRandom.uniform(N);
                int col = StdRandom.uniform(N);
                if (!experiments[i].isOpen(row, col)) {
                    experiments[i].open(row, col);
                }
            }
        }
    }

    public double mean() {
        double[] x = new double[T];
        for (int i = 0; i < T; i++) {
            x[i] = (double) (experiments[i].numberOfOpenSites()) / (double) (N * N);
        }
        return StdStats.mean(x);
    }

    public double stddev() {
        double[] x = new double[T];
        for (int i = 0; i < T; i++) {
            x[i] = (double) (experiments[i].numberOfOpenSites()) / (double) (N * N);
        }
        return StdStats.stddev(x);
    }

    public double confidenceLow() {
        return this.mean() - (CONSTANT * Math.sqrt(this.stddev()) / Math.sqrt(T));
    }

    public double confidenceHigh() {
        return this.mean() + (CONSTANT * Math.sqrt(this.stddev()) / Math.sqrt(T));
    }

}
