import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Iterator;

public class CircularLinkedListTest {

    private CircularLinkedList<Integer> list;

    @BeforeEach
    public void setUp() {
        list = new CircularLinkedList<>();
    }

    @Test
    public void testConstructorEmptyList() {
        assertEquals(0, list.size());
    }

    @Test
    public void testConstructorWithValues() {
        CircularLinkedList<Integer> listWithValues = new CircularLinkedList<>(1, 2, 3);
        assertEquals(3, listWithValues.size());
        assertEquals(1, listWithValues.get(0));
        assertEquals(2, listWithValues.get(1));
        assertEquals(3, listWithValues.get(2));
    }

    @Test
    public void testAdd() {
        list.add(1);
        assertEquals(1, list.size());
        assertEquals(1, list.get(0));

        list.add(2);
        assertEquals(2, list.size());
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));

        list.add(3);
        assertEquals(3, list.size());
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
    }

    @Test
    public void testRemoveByValue() {
        list.add(1);
        list.add(2);
        list.add(3);

        assertTrue(list.remove(Integer.valueOf(2)));
        assertEquals(2, list.size());
        assertEquals(1, list.get(0));
        assertEquals(3, list.get(1));

        assertFalse(list.remove(Integer.valueOf(4)));
        assertEquals(2, list.size());
    }

    @Test
    public void testRemoveByPosition() {
        list.add(1);
        list.add(2);
        list.add(3);

        list.remove(1);
        assertEquals(2, list.size());
        assertEquals(1, list.get(0));
        assertEquals(3, list.get(1));

        list.remove(0);
        assertEquals(1, list.size());
        assertEquals(3, list.get(0));

        list.remove(0);
        assertEquals(0, list.size());
    }

    @Test
    public void testGet() {
        list.add(1);
        list.add(2);
        list.add(3);

        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));

        assertThrows(IndexOutOfBoundsException.class, () -> list.get(3));
    }

    @Test
    public void testIterator() {
        list.add(1);
        list.add(2);
        list.add(3);

        Iterator<Integer> iterator = list.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(1, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(2, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(3, iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testRemoveFromEmptyList() {
        assertFalse(list.remove(Integer.valueOf(1)));
    }

    @Test
    public void testRemoveAtPositionFromEmptyList() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(0));
    }

    @Test
    public void testSize() {
        assertEquals(0, list.size());
        list.add(1);
        assertEquals(1, list.size());
        list.add(2);
        assertEquals(2, list.size());
        list.remove(1);
        assertEquals(1, list.size());
        list.remove(0);
        assertEquals(0, list.size());
    }
}
