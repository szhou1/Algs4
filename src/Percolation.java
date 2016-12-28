import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Percolation {

    private int N;
    private int [] matrix;
    private int openCount;
    private WeightedQuickUnionUF uf;
    
    // create n-by-n grid, with all sites blocked
    public Percolation(int n) {
        System.out.println("instantiate Percolation with N: " + n);
        N = n;
        matrix = new int [n * n];
        openCount = 0;
        uf = new WeightedQuickUnionUF(n * n + 2);
    }

    // open site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col);
        int r = row - 1; 
        int c = col - 1;
        if(matrix[r * N + c]==0) {
            if(row == 1) {
//                uf.union(r * N + c, N * N);
                uf.union(N * N, r * N + c);
            } else if(row == N) {
//                uf.union(r * N + c, N * N + 1);
                uf.union(N * N + 1, r * N + c);
            } else {
            }
            // left
            checkSides(r, c, r, c - 1);
            // top
            checkSides(r, c, r - 1, c);
            // right
            checkSides(r, c, r, c + 1);
            // bottom
            checkSides(r, c, r + 1, c);
            matrix[r * N + c] = 1;
            openCount++;                
        }
    }
    
    private void checkSides(int r, int c, int i, int j) {
//        System.out.println(i + " " + j);
        if(i >= 0 && i < N && j >= 0 && j < N && matrix[i * N + j]==1) {
            uf.union(r * N + c, i * N + j);
        }
    }
    
    private void validate(int r, int c) {
        if(r < 1 || r > N || c < 1 || c > N) {
            throw new IndexOutOfBoundsException();
        }
    }

    // is site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);
        row--; col--;
        return matrix[row * N + col] == 1;
    }

    // is site (row, col) full?
    public boolean isFull(int row, int col) {
        validate(row, col);
        row--; col--;
        return matrix[row * N + col]==1 && uf.connected(row * N + col, N * N);
    }

    // number of open sites
    public int numberOfOpenSites() {
        return openCount;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.connected(N * N, N * N + 1);
    }
    
    public void printMatrix() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < matrix.length; i++) {
            sb.append(matrix[i] + " ");
            if(i % N == 4) {                
                sb.append("\n");
            }
        }
        System.out.println(sb.toString());
    }
    

    public static void main(String[] args) {
        int n = StdIn.readInt();
        Percolation perc = new Percolation(n);
        perc.printMatrix();
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            StdOut.println("open: " + p + " " + q);
            perc.open(p, q);
            System.out.println("Connected: " + perc.isFull(4, 2));
        }
        perc.printMatrix();
//        perc.uf.printParent();
        System.out.println("Percolates: " + perc.percolates());
    }

}
