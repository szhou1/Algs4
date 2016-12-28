import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {
    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials) {
        Percolation perc;
//        StdOut.println();
        for(int i = 0; i < trials; i++) {
            int [] rand = StdRandom.permutation(n*n, n*n);
            perc = new Percolation(n);
            for(int j = 0; j<rand.length; j++) {
                int row = (rand[j]/n) + 1;
                int col = (rand[j]%n) + 1;
//                StdOut.print("[" + row + "," + col + "]");
                perc.open(row, col);
//                StdOut.print(perc.percolates());
                if(perc.percolates()) {
                    StdOut.println(" Open: " + perc.numberOfOpenSites());
                    break;
                }
//                StdOut.println();
            }
            
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return 0;
    }
    // sample standard deviation of percolation threshold
    public double stddev() {
        return 0;

    }
    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        return 0;
    }
    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return 0;
    }

    // test client (described below)
    public static void main(String[] args) {
        PercolationStats stats = new PercolationStats(20, 10);
    }
}
