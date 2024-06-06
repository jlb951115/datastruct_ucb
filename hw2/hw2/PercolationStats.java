package hw2;

import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {
    private Percolation[] Experiments;
    private int T;
    private int N;
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("Illegal Argument");
        }
        this.N = N;
        this.T = T;
        Experiments = new Percolation[T];
        for (int i = 0; i < T; i++) {
            Experiments[i] = pf.make(N);
        }
        for (int i = 0; i < T; i++) {
            while (!Experiments[i].percolates()) {
                int row = StdRandom.uniform(N);
                int col = StdRandom.uniform(N);
                if (!Experiments[i].isOpen(row, col)) {
                    Experiments[i].open(row, col);
                }
            }
        }
    }

    public double mean() {
        double mean = 0.0;
        for (int i = 0; i < T; i++) {
            mean += (double)(Experiments[i].numberOfOpenSites() / N / N);
        }
        return mean / T;
    }

    public double stddev() {
        double m = this.mean();
        double std = 0.0;
        for (int i = 0; i < T; i++) {
            double temp = (double)(Experiments[i].numberOfOpenSites() / N / N)- m;
            std = std + temp * temp;
        }
        return std / (T - 1);
    }

    public double confidenceLow() {
        return this.mean() - (1.96 * Math.sqrt(this.stddev()) / Math.sqrt(T));
    }

    public double confidenceHigh() {
        return this.mean() + (1.96 * Math.sqrt(this.stddev()) / Math.sqrt(T));
    }
}
