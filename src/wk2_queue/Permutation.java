package wk2_queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Permutation {

    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> queue = new RandomizedQueue<String>();

        while (!StdIn.isEmpty() && queue.size() < k) {
            queue.enqueue(StdIn.readString());
        }

        int n = k;
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (StdRandom.uniform(++n) + 1 <= k) {
                queue.dequeue();
                queue.enqueue(s);
            }
        }

        for (int i = 0; i < k; i++) {
            StdOut.println(queue.dequeue());
        }
    }
}
