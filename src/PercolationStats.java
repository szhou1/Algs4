import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    
    private double [] stats;
    
    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if(n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }
        
        Percolation perc;
        stats = new double [trials];
        for(int i = 0; i < trials; i++) {
            perc = new Percolation(n);
            for(int j = 0; j < n*n; j++) {
                int rand = (int) (Math.random() * n * n);
                int row = (rand / n) + 1;
                int col = (rand % n) + 1;
                
                // try to find a closed one
                while(perc.isOpen(row, col)) {
                    rand = (int) (Math.random() * n * n);
                    row = (rand / n) + 1;
                    col = (rand % n) + 1;    
                }
//                System.out.println("opening: " + row + " "+ col);
                perc.open(row, col);
                if(perc.percolates()) {
//                    System.out.println("open: " + perc.numberOfOpenSites());
                    stats[i] = (double) perc.numberOfOpenSites() / (double)(n * n);
//                    System.out.println(stats[i]);
                    break;
                }
            }
            
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(stats);
    }
    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(stats);
    }
    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - (stddev() * 1.96) / Math.sqrt(stats.length);
    }
    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + (stddev() * 1.96) / Math.sqrt(stats.length);
    }

    // test client (described below)
    public static void main(String[] args) {
        PercolationStats stats = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        StdOut.println("mean  : " + stats.mean());
        StdOut.println("stddev: " + stats.stddev());
        StdOut.println("ci    : " + stats.confidenceLo() + ", " + stats.confidenceHi());
    }
}
