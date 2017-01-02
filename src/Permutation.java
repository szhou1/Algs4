import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {

    public static void main(String [] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> queue = new RandomizedQueue<String>();

        StdOut.println("k: " + k);
        while(!StdIn.isEmpty()) {
//            if(queue.size() == k) {
//                String d = queue.dequeue();
//                StdOut.println("-: " + d);
//            }
            String s = StdIn.readString();
            queue.enqueue(s);                
            System.out.println("+: " + s);
        }

        StdOut.println("Size:" + queue.size());
        StdOut.println("Cap:" + queue.queueCapacity());
        StdOut.println("Content:");
        
        for(String s : queue) {
            StdOut.println(s);
        }
        
        StdOut.println("result");
        for(int i = 0; i < k; i++) {
            StdOut.println(queue.dequeue());
        }
    }
}
