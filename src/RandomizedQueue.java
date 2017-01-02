import java.util.Iterator;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item [] q;
    private int count;
    
    // construct an empty randomized queue
    @SuppressWarnings("unchecked")
    public RandomizedQueue() {
        q = (Item[]) new Object[2];
        count = 0;
    }

    // is the queue empty?
    public boolean isEmpty() {
        return q.length > 0;
    }

    // return the number of items on the queue
    public int size() {
        return count;
    }
    
    public int queueCapacity() {
        return q.length;
    }

    // add the item
    @SuppressWarnings("unchecked")
    public void enqueue(Item item) {
        q[count] = item;
        count++;
        if(count > 0.75 * q.length) {
            StdOut.println("enlarge array");
            Item [] larger = (Item []) new Object[q.length * 2];
            for(int i = 0; i < q.length; i++) {
                larger[i] = q[i];
            }
            q = larger;
        }
    }

    // remove and return a random item
    @SuppressWarnings("unchecked")
    public Item dequeue() {
        int randomIndex = (int) (StdRandom.uniform() * q.length);
        while(q[randomIndex] == null) {
            randomIndex = (int) (StdRandom.uniform() * q.length);
        }
        Item item = q[randomIndex];
        q[randomIndex] = null;
        count--;
        if(count < 0.25 * q.length) {
            StdOut.println("shrink array");
            Item [] smaller = (Item []) new Object[q.length / 2];
            for(int i = 0, j = 0; i < q.length; i++) {
                if(q[i] != null) {
                    smaller[j] = q[i];
                    j++;
                }
            }
            q = smaller;
        }
        return item;
    }

    // return (but do not remove) a random item
    public Item sample() {
        int randomIndex = (int) (StdRandom.uniform() * q.length);
        while(q[randomIndex] == null) {
            randomIndex = (int) (StdRandom.uniform() * q.length);
        }
        Item item = q[randomIndex];
        return item;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new QueueIterator();
    }
    
    private class QueueIterator implements Iterator<Item> {

        private Item [] qIter;
        private int countIter = count;
        
        @SuppressWarnings("unchecked")
        public QueueIterator() {
            qIter = (Item []) new Object[q.length];
            for(int i = 0; i < q.length; i++) {
                qIter[i] = q[i];
            }
        }
        
        @Override
        public boolean hasNext() {
            return countIter > 0;
        }

        @Override
        public Item next() {
            int randomIndex = (int) (StdRandom.uniform() * qIter.length);
            while(qIter[randomIndex] == null) {
                randomIndex = (int) (StdRandom.uniform() * qIter.length);
            }
            Item item = qIter[randomIndex];
            qIter[randomIndex] = null;
            countIter--;
            return item;
        }
        
    }
    
    public static void main(String[] args) { // unit testing

        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        queue.enqueue("a");
        queue.enqueue("b");
        queue.enqueue("c");
        queue.enqueue("d");
        queue.enqueue("e");
        queue.enqueue("f");
        
        StdOut.println("count: " + queue.count);
        StdOut.println("cap: " + queue.queueCapacity());
        
        for(String s : queue) {
            StdOut.println(s);
        }
        
        StdOut.println("dequeued: " + queue.dequeue());
        
        for(String s : queue) {
            StdOut.println(s);
        }        
        StdOut.println("count: " + queue.count);
        StdOut.println("cap: " + queue.queueCapacity());
        
        for(String s : queue) {
            StdOut.println(queue.dequeue());
        }
        for(String s : queue) {
            StdOut.println(s);
        }        
        StdOut.println("count: " + queue.count);
        StdOut.println("cap: " + queue.queueCapacity());
                
        
    }
}
