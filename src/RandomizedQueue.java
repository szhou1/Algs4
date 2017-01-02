import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] q;
    private int count;

    // construct an empty randomized queue
    public RandomizedQueue() {
        q = (Item[]) new Object[4];
        count = 0;
    }

    // is the queue empty?
    public boolean isEmpty() {
        return count == 0;
    }

    // return the number of items on the queue
    public int size() {
        return count;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null)
            throw new NullPointerException();

        q[count] = item;
        count++;
        if (count > (0.75 * q.length)) {
            resize(q.length * 2);
        }
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException();

        int randomIndex = StdRandom.uniform(count);
        Item item = q[randomIndex];
        q[randomIndex] = q[--count];
        q[count] = null;
        if (count < 0.25 * q.length) {
            resize(q.length / 2);
        }
        return item;
    }

    private void resize(int newSize) {
        Item[] resized = (Item[]) new Object[newSize];
        for (int i = 0; i < count; i++) {
            resized[i] = q[i];
        }
        q = resized;
    }

    // return (but do not remove) a random item
    public Item sample() {
        if (isEmpty())
            throw new NoSuchElementException();
        return q[StdRandom.uniform(count)];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<Item> {

        private Item[] qIter;
        private int countIter;

        public QueueIterator() {
            qIter = (Item[]) new Object[count];
            for (int i = 0; i < count; i++) {
                qIter[i] = q[i];
            }
            StdRandom.shuffle(qIter);
            countIter = count;
        }

        @Override
        public boolean hasNext() {
            return countIter > 0;
        }

        @Override
        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();

            Item item = qIter[--countIter];
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    public static void main(String[] args) { // unit testing

    }
}
