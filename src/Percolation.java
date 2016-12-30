import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private int N;
    private int[] matrix;
    private int openCount;
    private WeightedQuickUnionUF uf;

    // create n-by-n grid, with all sites blocked
    public Percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException();
        N = n;
        matrix = new int[n * n];
        openCount = 0;
        uf = new WeightedQuickUnionUF(n * n + 2);
    }

    // open site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col);
        int r = row - 1;
        int c = col - 1;
        if (matrix[r * N + c] == 0) {
            if (row == 1) {
                uf.union(N * N, r * N + c);
            } else if (row == N) {
                uf.union(N * N + 1, r * N + c);
            }
            checkSides(r, c, r, c - 1); // left
            checkSides(r, c, r - 1, c); // top
            checkSides(r, c, r, c + 1); // right
            checkSides(r, c, r + 1, c); // bottom
            matrix[r * N + c] = 1;
            openCount++;
        }
    }

    private void checkSides(int r, int c, int i, int j) {
        if (i >= 0 && i < N && j >= 0 && j < N && matrix[i * N + j] == 1) {
            uf.union(r * N + c, i * N + j);
        }
    }

    private void validate(int r, int c) {
        if (r < 1 || r > N || c < 1 || c > N) {
            throw new IndexOutOfBoundsException();
        }
    }

    // is site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);
        row--;
        col--;
        return matrix[row * N + col] == 1;
    }

    // is site (row, col) full?
    public boolean isFull(int row, int col) {
        validate(row, col);
        row--;
        col--;
        return matrix[row * N + col] == 1 && uf.connected(row * N + col, N * N);
    }

    // number of open sites
    public int numberOfOpenSites() {
        return openCount;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.connected(N * N, N * N + 1);
    }

    private void printMatrix() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < matrix.length; i++) {
            sb.append(matrix[i] + " ");
            if (i % N == N - 1) {
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
        StdOut.println("Percolates: " + perc.percolates() + ", count: " + perc.openCount);
    }

}
