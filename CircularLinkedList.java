import java.util.Iterator;

/**
 * @author Vivek Vemulakonda
 * @version 1.0
 * A circular linked list implementation of the interface
 * @param <E> the element it takes in
 */
public class CircularLinkedList<E> implements CircularLinkedListInterface<E>, Iterable<E> {
    private Node<E> front;
    private int size;

    /** Node class represents an element in the linked list
     *
     * @param <T> the type of data stored in the node
     */
    private static class Node<T>{
        public T data;
        public Node<T> next;

        /**
         * Constructs a node with the given data
         *
         * @param data data for the node to contain
         */
        public Node(T data){
            this.data = data;
            this.next = null;
        }
    }

    /**
     * Constructs a circular linked list with any amount of values given
     *
     * @param values the values that are used to initialize linked list
     */
    @SafeVarargs
    public CircularLinkedList(E... values){
        this();
        for(E value : values){
            add(value);
        }
    }

    /**
     * Constructs an empty Circular linked list
     */
    public CircularLinkedList(){
        front = null;
        size = 0;
    }

    /**
     * Retrieves a count of elements being maintained by the list.
     *
     * @return the size of the list (count of elements)
     */
    public int size(){
        return size;
    }

    /**
     * Retrieves the data at the specified position in the list
     *
     * @param position 0-based index for the list; must be in the range 0 to size - 1
     * @return the data in the specified position in the list
     */
    public E get(int position){
        if(position < 0 || position >= size){
            throw new IndexOutOfBoundsException();
        }
        Node<E> current = front;
        for(int i = 0; i < position; i++){
            current = current.next;
        }
        return current.data;
    }

    /**
     * Adds a new node to the end of the list; by nature, this element's next will point to the first element
     *
     * @param value the element to add to the list
     */
    @Override
    public void add(E value) {
        Node<E> newNode = new Node<>(value);
        if(front == null){
            front = newNode;
            front.next = front;
        } else {
            Node<E> current = front;
            while(current.next != front){
                current = current.next;
            }
            current.next = newNode;
            newNode.next = front;
        }
        size++;
    }

    /**
     * Removes the specified item from the list, if it exists there.
     *
     * @param value the element to remove from the list
     * @return true, if the element was found and removed; false, if not found or list is empty
     */
    @Override
    public boolean remove(E value) {
        if (front == null) {
            return false;
        }

        Node<E> current = front;
        Node<E> previous = null;

        for (int i = 0; i < size; i++) {
            if (current.data.equals(value)) {
                if (previous == null) {
                    if (size == 1) {
                        front = null;
                    } else {
                        Node<E> last = front;
                        while (last.next != front) {
                            last = last.next;
                        }
                        front = front.next;
                        last.next = front;
                    }
                } else {
                    previous.next = current.next;
                }
                size--;
                return true;
            }
            previous = current;
            current = current.next;
        }

        return false;
    }

    /**
     * Removes the node at the specified position in the list
     *
     * @param position position in the list; must be in range 0 to size - 1
     */
    @Override
    public void remove(int position) {
        if (position < 0 || position >= size) {
            throw new IndexOutOfBoundsException("Position out of range");
        }

        Node<E> current = front;
        Node<E> previous = null;

        for (int i = 0; i < position; i++) {
            previous = current;
            current = current.next;
        }

        if (previous == null) {
            if (size == 1) {
                front = null;
            } else {
                Node<E> last = front;
                while (last.next != front) {
                    last = last.next;
                }
                front = front.next;
                last.next = front;
            }
        } else {
            previous.next = current.next;
        }
        size--;
    }

    /**
     * Retrieves an iterator over the list's elements.  Do not do other list operations like add or remove
     * from within an iterator loop; the results are not guaranteed to function as you might expect
     *
     * @return a strongly typed iterator over elements in the list
     */
    @Override
    public Iterator<E> iterator() {
        return new LinkedListIterator();
    }

    /**
     * An iterator over the elements of the linked list.
     */
    private class LinkedListIterator implements Iterator<E> {
        private Node<E> current = front;
        private int count = 0;

        /**
         * Returns true if there are more elements in the list
         *
         * @return true if there are more elements in the list
         */
        @Override
        public boolean hasNext() {
            return count < size;
        }

        /**
         * Returns the next element in the iteration
         *
         * @return the next element in the iteration
         */
        public E next(){
            E data = current.data;
            current = current.next;
            count++;
            return data;
        }
    }
}
