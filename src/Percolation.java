import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Percolation {

    private int N;
    private int [] matrix;
    private int openCount;
    private WeightedQuickUnionUF uf;
    
    // create n-by-n grid, with all sites blocked
    public Percolation(int n) {
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
                uf.union(N * N, r * N + c);
            } else if(row == N) {
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
        if(i >= 0 && i < N && j >= 0 && j < N && matrix[i * N + j]==1) {
            uf.union(r * N + c, i * N + j);
        }
    }
    
    private void validate(int r, int c) {
        if(r < 1 || r > N || c < 1 || c > N) {
            throw new IllegalArgumentException();
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
    
    private void printMatrix() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < matrix.length; i++) {
            sb.append(matrix[i] + " ");
            if(i % N == N-1) {                
                sb.append("\n");
            }
        }
        StdOut.println(sb.toString());
    }
    
    private class WeightedQuickUnionUF {
        private int[] parent;   // parent[i] = parent of i
        private int[] size;     // size[i] = number of sites in subtree rooted at i
        private int count;      // number of components

        /**
         * Initializes an empty union–find data structure with {@code n} sites
         * {@code 0} through {@code n-1}. Each site is initially in its own 
         * component.
         *
         * @param  n the number of sites
         * @throws IllegalArgumentException if {@code n < 0}
         */
        public WeightedQuickUnionUF(int n) {
            count = n;
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        /**
         * Returns the number of components.
         *
         * @return the number of components (between {@code 1} and {@code n})
         */
        public int count() {
            return count;
        }
      
        /**
         * Returns the component identifier for the component containing site {@code p}.
         *
         * @param  p the integer representing one object
         * @return the component identifier for the component containing site {@code p}
         * @throws IndexOutOfBoundsException unless {@code 0 <= p < n}
         */
        public int find(int p) {
            validate(p);
            while (p != parent[p])
                p = parent[p];
            return p;
        }

        // validate that p is a valid index
        private void validate(int p) {
            int n = parent.length;
            if (p < 0 || p >= n) {
                throw new IndexOutOfBoundsException("index " + p + " is not between 0 and " + (n-1));  
            }
        }

        /**
         * Returns true if the the two sites are in the same component.
         *
         * @param  p the integer representing one site
         * @param  q the integer representing the other site
         * @return {@code true} if the two sites {@code p} and {@code q} are in the same component;
         *         {@code false} otherwise
         * @throws IndexOutOfBoundsException unless
         *         both {@code 0 <= p < n} and {@code 0 <= q < n}
         */
        public boolean connected(int p, int q) {
            return find(p) == find(q);
        }

        /**
         * Merges the component containing site {@code p} with the 
         * the component containing site {@code q}.
         *
         * @param  p the integer representing one site
         * @param  q the integer representing the other site
         * @throws IndexOutOfBoundsException unless
         *         both {@code 0 <= p < n} and {@code 0 <= q < n}
         */
        public void union(int p, int q) {
//            System.out.println("union: " + p + ", " + q);
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ) return;

            // make smaller root point to larger one
            if (size[rootP] < size[rootQ]) {
                parent[rootP] = rootQ;
                size[rootQ] += size[rootP];
            }
            else {
                parent[rootQ] = rootP;
                size[rootP] += size[rootQ];
            }
            count--;
        }
    }

    public static void main(String[] args) {
        int n = StdIn.readInt();
        Percolation perc = new Percolation(n);
        perc.printMatrix();
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            perc.open(p, q);
            if(perc.percolates()) {
                break;
            }
        }
        perc.printMatrix();
        StdOut.println("Percolates: " + perc.percolates() + ", count: " + perc.openCount);
    }

}
