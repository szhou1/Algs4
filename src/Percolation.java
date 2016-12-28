import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Percolation {

    public Percolation(int n) {

        
    }

    public static void main(String[] args) {
        int n = StdIn.readInt();
        Percolation perc = new Percolation(n);

        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            StdOut.println(p + " " + q);
        }
    }

}
