package deque;

import org.junit.Test;
import java.util.Comparator;
import static org.junit.Assert.*;

/** Performs some basic MaxArrayDeque tests. */
public class MaxArrayDequeTest {

    @Test
    /** Test max() with default Comparator provided at construction. */
    public void maxTest() {
        Comparator<Integer> comparator = Integer::compareTo;
        MaxArrayDeque<Integer> mad1 = new MaxArrayDeque<>(comparator);

        mad1.addLast(3);
        mad1.addLast(1);
        mad1.addLast(4);
        mad1.addLast(1);
        mad1.addLast(5);
        mad1.addLast(9);

        assertEquals("Max value should be 9", (Integer) 9, mad1.max());
    }

    @Test
    /** Test max() with a custom Comparator passed to the method. */
    public void maxWithCustomComparatorTest() {
        MaxArrayDeque<String> mad1 = new MaxArrayDeque<>(String::compareTo);

        mad1.addLast("apple");
        mad1.addLast("banana");
        mad1.addLast("cherry");

        // Using default lexicographical order (alphabetical)
        assertEquals("Max value should be cherry", "cherry", mad1.max());

        // Custom Comparator to find the shortest string

    }

    @Test
    /** Test max() on an empty deque should return null. */
    public void maxEmptyDequeTest() {
        Comparator<Integer> comparator = Integer::compareTo;
        MaxArrayDeque<Integer> mad1 = new MaxArrayDeque<>(comparator);

        assertNull("Max on empty deque should return null", mad1.max());
        assertNull("Max with custom comparator on empty deque should return null", mad1.max(comparator));
    }

    @Test
    /** Test max() with multiple parameterized types in the deque. */
    public void multipleParamMaxTest() {
        Comparator<String> comparator = String::compareTo;
        MaxArrayDeque<String> mad1 = new MaxArrayDeque<>(comparator);

        mad1.addFirst("zebra");
        mad1.addFirst("apple");
        mad1.addFirst("mango");

        assertEquals("Max value should be zebra", "zebra", mad1.max());

        mad1.removeLast();
        assertEquals("Max value should be mango after removing zebra", "mango", mad1.max());
    }

    @Test
    /** Add a large number of elements to deque and test max(). */
    public void bigMaxArrayDequeTest() {
        Comparator<Integer> comparator = Integer::compareTo;
        MaxArrayDeque<Integer> mad1 = new MaxArrayDeque<>(comparator);

        for (int i = 0; i < 1000; i++) {
            mad1.addLast(i);
        }

        assertEquals("Max value should be 999", (Integer) 999, mad1.max());
    }
}
