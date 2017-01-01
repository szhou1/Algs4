import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

    Node head;
    Node tail;
    int size;

    public Deque() {
        head = null;
        tail = null;
        size = 0;
    }

    private class Node {
        Item item;
        Node prev;
        Node next;

        public Node(Item item) {
            this.item = item;
        }
    }

    // is the deque empty?
    public boolean isEmpty() {
        return head == null;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        Node node = new Node(item);
        size++;
        if (isEmpty()) {
            head = tail = node;
            return;
        }

        head.prev = node;
        node.next = head;
        head = node;
    }

    // add the item to the end
    public void addLast(Item item) {
        Node node = new Node(item);
        size++;
        if (isEmpty()) {
            head = tail = node;
            return;
        }

        tail.next = node;
        node.prev = tail;
        tail = node;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty())
            return null;
        size--;
        Item first = head.item;
        head.next.prev = null;
        head = head.next;
        return first;
    }

    // remove and return the item from the end
    public Item removeLast() {
        if (isEmpty())
            return null;
        size--;
        Item last = tail.item;
        tail.prev.next = null;
        tail = tail.prev;
        return last;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {

        Node current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            Item i = current.item;
            current = current.next;
            return i;
        }

    }

    public static void main(String[] args) {
        Deque<String> d = new Deque<String>();
        d.addFirst("e");
        d.addFirst("t");
        d.addLast("z");
        d.addLast("h");
        d.addFirst("s");
        
        d.removeLast();
        d.removeFirst();

        for (String s : d) {
            System.out.println(s);
        }
    }

}
