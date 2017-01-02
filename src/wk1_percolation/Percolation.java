package wk1_percolation;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private int n;
    private boolean[] matrix;
    private int openCount;
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF ufbw;

    // create n-by-n grid, with all sites blocked
    public Percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException();
        this.n = n;
        matrix = new boolean[n * n];
        openCount = 0;
        uf = new WeightedQuickUnionUF(n * n + 2);
        ufbw = new WeightedQuickUnionUF(n * n + 2);
    }

    // open site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col);
        int r = row - 1;
        int c = col - 1;
        if (!matrix[r * n + c]) {
            if (row == 1) {
                uf.union(n * n, r * n + c);
                ufbw.union(n * n, r * n + c);
            }
            if (row == n) {
                uf.union(n * n + 1, r * n + c);
            }
            checkSides(r, c, r, c - 1); // left
            checkSides(r, c, r - 1, c); // top
            checkSides(r, c, r, c + 1); // right
            checkSides(r, c, r + 1, c); // bottom
            matrix[r * n + c] = true;
            openCount++;
        }
    }

    private void checkSides(int r, int c, int i, int j) {
        if (i >= 0 && i < n && j >= 0 && j < n && matrix[i * n + j]) {
            uf.union(r * n + c, i * n + j);
            ufbw.union(r * n + c, i * n + j);
        }
    }

    private void validate(int r, int c) {
        if (r < 1 || r > n || c < 1 || c > n) {
            throw new IndexOutOfBoundsException();
        }
    }

    // is site (row, col) open?
    public boolean isOpen(int r, int c) {
        validate(r, c);
        r--;
        c--;
        return matrix[r * n + c];
    }

    // is site (row, col) connected to the top row via open sites?
    public boolean isFull(int r, int c) {
        validate(r, c);
        r--;
        c--;
        return matrix[r * n + c] && ufbw.connected(r * n + c, n * n);
    }

    // number of open sites
    public int numberOfOpenSites() {
        return openCount;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.connected(n * n, n * n + 1);
    }

    private void printMatrix() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < matrix.length; i++) {
            sb.append(matrix[i] + " ");
            if (i % n == n - 1) {
                sb.append("\n");
            }
        }
        StdOut.println(sb.toString());
    }

    public static void main(String[] args) {
        int n = StdIn.readInt();
        Percolation perc = new Percolation(n);
        perc.printMatrix();
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            perc.open(p, q);
            if (perc.percolates()) {
                break;
            }
        }
        perc.printMatrix();
        StdOut.println("Percolates: " + perc.percolates() + ", open sites count: " + perc.openCount);
    }

}
