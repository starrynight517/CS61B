package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by hug.
 */

public class TestBuggyAList {
    // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing list1 = new AListNoResizing();
        BuggyAList list2 = new BuggyAList();

        list1.addLast(4);
        list2.addLast(4);
        list1.addLast(5);
        list2.addLast(5);
        list1.addLast(6);
        list2.addLast(6);

        assertEquals(list1.size(), list2.size());

        assertEquals(list1.removeLast(), list2.removeLast());
        assertEquals(list1.removeLast(), list2.removeLast());
        assertEquals(list1.removeLast(), list2.removeLast());
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> L1 = new BuggyAList<>();
        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 3);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                L1.addLast(randVal);
            } else if (operationNumber == 1) {
                // size
                int size1 = L.size();
                int size2= L1.size();
            }else if (operationNumber == 2) {
                int size = L.size();
                if(size>0){
                    L.getLast();
                    L1.getLast();
                    L.removeLast();
                    L1.removeLast();
                }
            }

                }
            }
}