import static org.junit.Assert.*;

import org.junit.Test;

public class TestDeque {

    @Test
    public void test() {
        Deque<String> d = new Deque<String>();
        assertTrue(d.isEmpty());
        d.addFirst("a");
        assertEquals(1, d.size());
        String removed = d.removeFirst();
        assertEquals("a", removed);
        assertEquals(0, d.size());
        
        d.addFirst("b");
        d.addLast("c");
        d.addLast("d");
        d.addFirst("e");
        assertEquals(4, d.size());
        
        assertEquals("d", d.removeLast());
        assertEquals("e", d.removeFirst());
        assertEquals(2, d.size());
        
        for (String s : d) {
            System.out.println(s);
            assertNotNull(s);
        }
        
        assertEquals("b", d.removeFirst());
        assertEquals("c", d.removeLast());
        assertTrue(d.isEmpty());
    }
    
    @Test
    public void test1() {
        Deque<String> d = new Deque<String>();
        assertTrue(d.isEmpty());
        d.addFirst("a");
        assertEquals("a", d.removeFirst());
        assertTrue(d.isEmpty());
    }

    @Test
    public void test2() {
        Deque<String> d = new Deque<String>();
        assertTrue(d.isEmpty());
        d.addFirst("a");
        assertEquals("a", d.removeLast());
        assertTrue(d.isEmpty());
    }
}
