import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node head;
    private Node tail;
    private int size;

    public Deque() {
        head = null;
        tail = null;
        size = 0;
    }

    private class Node {
        private Item item;
        private Node prev;
        private Node next;

        public Node(Item item) {
            this.item = item;
        }
    }

    // is the deque empty?
    public boolean isEmpty() {
        return head == null || tail == null;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null)
            throw new NullPointerException();
        Node node = new Node(item);
        size++;
        if (isEmpty()) {
            head = node;
            tail = node;
            return;
        }

        head.prev = node;
        node.next = head;
        head = node;
    }

    // add the item to the end
    public void addLast(Item item) {
        if (item == null)
            throw new NullPointerException();
        Node node = new Node(item);
        size++;
        if (isEmpty()) {
            head = node;
            tail = node;
            return;
        }

        tail.next = node;
        node.prev = tail;
        tail = node;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException();
        size--;
        Item first = head.item;
        if (head.next != null) {
            head.next.prev = null;
        }
        head = head.next;
        if (head == null)
            tail = null;
        return first;
    }

    // remove and return the item from the end
    public Item removeLast() {
        if (isEmpty())
            throw new NoSuchElementException();
        size--;
        Item last = tail.item;
        if (tail.prev != null) {
            tail.prev.next = null;
        }
        tail = tail.prev;
        if (tail == null)
            head = null;
        return last;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {

        private Node current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (current == null)
                throw new NoSuchElementException();
            Item i = current.item;
            current = current.next;
            return i;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {

    }

}
