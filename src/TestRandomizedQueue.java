import static org.junit.Assert.*;

import org.junit.Test;

import edu.princeton.cs.algs4.StdOut;

public class TestRandomizedQueue {

    @Test
    public void test() {
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        assertTrue(queue.isEmpty());

        queue.enqueue("a");
        assertEquals(1, queue.size());
        queue.enqueue("b");
        queue.enqueue("c");
        assertEquals(3, queue.size());

        for (String s : queue) {
            StdOut.println(s);
            assertNotNull(s);
        }
        StdOut.println("count: " + queue.size());
        StdOut.println("cap: " + queue.queueCapacity());

        queue.enqueue("d");
        queue.enqueue("e");
        queue.enqueue("f");
        assertEquals(6, queue.size());

        StdOut.println("count: " + queue.size());
        StdOut.println("cap: " + queue.queueCapacity());

        for (String s : queue) {
            StdOut.println(s);
            assertNotNull(s);
        }

        StdOut.println("dequeued: " + queue.dequeue());

        for (String s : queue) {
            StdOut.println(s);
            assertNotNull(s);
        }
        StdOut.println("count: " + queue.size());
        StdOut.println("cap: " + queue.queueCapacity());

        for (String s : queue) {
            StdOut.println(queue.dequeue());
            assertNotNull(s);
        }
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
        for (String s : queue) {
            StdOut.println(s);
            assertNotNull(s);
        }
        StdOut.println("count: " + queue.size());
        StdOut.println("cap: " + queue.queueCapacity());

    }
}
