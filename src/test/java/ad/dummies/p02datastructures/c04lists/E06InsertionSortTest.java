package ad.dummies.p02datastructures.c04lists;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

import static ad.dummies.p02datastructures.c04lists.E06InsertionSort.*;

/**
 * <p>Unit tests for an example from the german book "Algorithms and data
 * structures for dummies":</p>
 *
 * <p>A. Gogol-Döring and T. Letschert, <i>Algorithmen und Datenstrukturen für
 * Dummies</i>. Weinheim, Germany: Wiley-VCH, 2019.</p>
 *
 * <p>The current version of these examples with unit tests and benchmarks can
 * be found <a href="https://github.com/CSchoel/ad-dummies-java">on GitHub</a>.
 * </p>
 *
 * @author Christopher Schölzel
 * @see E06InsertionSort
 */
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
class E06InsertionSortTest {

    private static IntList buildIntList(int ... data) {
        IntList lst = new Nil();
        for(int i = data.length - 1; i >= 0; i--) {
            lst = new Cons(data[i], lst);
        }
        return lst;
    }

    // ------------ insertionSortFL --------------

    @Test
    public void insertionSortFLEmptyList() {
        assertTrue(new Nil().insertionSortFL() instanceof Nil);
    }

    @Test
    public void insertionSortFLTwoElementsAscending() {
        IntList sorted = buildIntList(1, 2).insertionSortFL();

        Cons cur = (Cons) sorted;
        assertEquals(1, cur.head);
        cur = (Cons) cur.tail;
        assertEquals(2, cur.head);
        assertTrue(cur.tail instanceof Nil);
    }

    @Test
    public void insertionSortFLTwoElementsDescending() {
        IntList sorted = buildIntList(2, 1).insertionSortFL();

        Cons cur = (Cons) sorted;
        assertEquals(1, cur.head);
        cur = (Cons) cur.tail;
        assertEquals(2, cur.head);
        assertTrue(cur.tail instanceof Nil);
    }

    @Test
    public void insertionSortFLFiveElementsAscending() {
        IntList sorted = buildIntList(-1, 0, 8, 10, 11).insertionSortFL();

        Cons cur = (Cons) sorted;
        assertEquals(-1, cur.head);
        cur = (Cons) cur.tail;
        assertEquals(0, cur.head);
        cur = (Cons) cur.tail;
        assertEquals(8, cur.head);
        cur = (Cons) cur.tail;
        assertEquals(10, cur.head);
        cur = (Cons) cur.tail;
        assertEquals(11, cur.head);
        assertTrue(cur.tail instanceof Nil);
    }

    @Test
    public void insertionSortFLFiveElementsDescending() {
        IntList sorted = buildIntList(11, 10, 8, 0, -1).insertionSortFL();

        Cons cur = (Cons) sorted;
        assertEquals(-1, cur.head);
        cur = (Cons) cur.tail;
        assertEquals(0, cur.head);
        cur = (Cons) cur.tail;
        assertEquals(8, cur.head);
        cur = (Cons) cur.tail;
        assertEquals(10, cur.head);
        cur = (Cons) cur.tail;
        assertEquals(11, cur.head);
        assertTrue(cur.tail instanceof Nil);
    }

    @Test
    public void insertionSortFLFiveElementsUnordered() {
        IntList sorted = buildIntList( -1, 11, 8, 0, 10).insertionSortFL();

        Cons cur = (Cons) sorted;
        assertEquals(-1, cur.head);
        cur = (Cons) cur.tail;
        assertEquals(0, cur.head);
        cur = (Cons) cur.tail;
        assertEquals(8, cur.head);
        cur = (Cons) cur.tail;
        assertEquals(10, cur.head);
        cur = (Cons) cur.tail;
        assertEquals(11, cur.head);
        assertTrue(cur.tail instanceof Nil);
    }

    // ------------ insertionSortF --------------

    @Test
    public void insertionSortFEmptyList() {
        int[] data = new int[]{};
        int[] sorted = insertionSortF(data);
        assertArrayEquals(new int[0], sorted);
    }

    @Test
    public void insertionSortFTwoElementsAscending() {
        int[] data = new int[]{1, 2};
        int[] sorted = insertionSortF(data);
        assertArrayEquals(new int[]{1, 2}, sorted);
    }

    @Test
    public void insertionSortFTwoElementsDescending() {
        int[] data = new int[]{2, 1};
        int[] sorted = insertionSortF(data);
        assertArrayEquals(new int[]{1, 2}, sorted);
    }

    @Test
    public void insertionSortFFiveElementsAscending() {
        int[] data = new int[]{-1, 0, 8, 10, 11};
        int[] sorted = insertionSortF(data);
        assertArrayEquals(new int[]{-1, 0, 8, 10, 11}, sorted);
    }

    @Test
    public void insertionSortFFiveElementsDescending() {
        int[] data = new int[]{11, 10, 8, 0, -1};
        int[] sorted = insertionSortF(data);
        assertArrayEquals(new int[]{-1, 0, 8, 10, 11}, sorted);
    }

    @Test
    public void insertionSortFFiveElementsUnordered() {
        int[] data = new int[]{-1, 11, 8, 0, 10};
        int[] sorted = insertionSortF(data);
        assertArrayEquals(new int[]{-1, 0, 8, 10, 11}, sorted);
    }

    // ------------ insertionSortFA --------------

    @Test
    public void insertionSortFAEmptyList() {
        int[] data = new int[]{};
        int[] sorted = insertionSortFA(data);
        assertArrayEquals(new int[0], sorted);
    }

    @Test
    public void insertionSortFATwoElementsAscending() {
        int[] data = new int[]{1, 2};
        int[] sorted = insertionSortFA(data);
        assertArrayEquals(new int[]{1, 2}, sorted);
    }

    @Test
    public void insertionSortFATwoElementsDescending() {
        int[] data = new int[]{2, 1};
        int[] sorted = insertionSortFA(data);
        assertArrayEquals(new int[]{1, 2}, sorted);
    }

    @Test
    public void insertionSortFAFiveElementsAscending() {
        int[] data = new int[]{-1, 0, 8, 10, 11};
        int[] sorted = insertionSortFA(data);
        assertArrayEquals(new int[]{-1, 0, 8, 10, 11}, sorted);
    }

    @Test
    public void insertionSortFAFiveElementsDescending() {
        int[] data = new int[]{11, 10, 8, 0, -1};
        int[] sorted = insertionSortFA(data);
        assertArrayEquals(new int[]{-1, 0, 8, 10, 11}, sorted);
    }

    @Test
    public void insertionSortFAFiveElementsUnordered() {
        int[] data = new int[]{-1, 11, 8, 0, 10};
        int[] sorted = insertionSortFA(data);
        assertArrayEquals(new int[]{-1, 0, 8, 10, 11}, sorted);
    }

    // ------------ insertionSortI --------------

    @Test
    public void insertionSortIEmptyList() {
        int[] data = new int[]{};
        insertionSortI(data);
        assertArrayEquals(new int[0], data);
    }

    @Test
    public void insertionSortITwoElementsAscending() {
        int[] data = new int[]{1, 2};
        insertionSortI(data);
        assertArrayEquals(new int[]{1, 2}, data);
    }

    @Test
    public void insertionSortITwoElementsDescending() {
        int[] data = new int[]{2, 1};
        insertionSortI(data);
        assertArrayEquals(new int[]{1, 2}, data);
    }

    @Test
    public void insertionSortIFiveElementsAscending() {
        int[] data = new int[]{-1, 0, 8, 10, 11};
        insertionSortI(data);
        assertArrayEquals(new int[]{-1, 0, 8, 10, 11}, data);
    }

    @Test
    public void insertionSortIFiveElementsDescending() {
        int[] data = new int[]{11, 10, 8, 0, -1};
        insertionSortI(data);
        assertArrayEquals(new int[]{-1, 0, 8, 10, 11}, data);
    }

    @Test
    public void insertionSortIFiveElementsUnordered() {
        int[] data = new int[]{-1, 11, 8, 0, 10};
        insertionSortI(data);
        assertArrayEquals(new int[]{-1, 0, 8, 10, 11}, data);
    }

    // ------------ sortP --------------


    @Test
    public void sortPEmptyList() {
        int[] data = new int[]{};
        int[] sorted = sortP(data);
        assertArrayEquals(new int[0], sorted);
    }

    @Test
    public void sortPTwoElementsAscending() {
        int[] data = new int[]{1, 2};
        int[] sorted = sortP(data);
        assertArrayEquals(new int[]{1, 2}, sorted);
    }

    @Test
    public void sortPTwoElementsDescending() {
        int[] data = new int[]{2, 1};
        int[] sorted = sortP(data);
        assertArrayEquals(new int[]{1, 2}, sorted);
    }

    @Test
    public void sortPFiveElementsAscending() {
        int[] data = new int[]{-1, 0, 8, 10, 11};
        int[] sorted = sortP(data);
        assertArrayEquals(new int[]{-1, 0, 8, 10, 11}, sorted);
    }

    @Test
    public void sortPFiveElementsDescending() {
        int[] data = new int[]{11, 10, 8, 0, -1};
        int[] sorted = sortP(data);
        assertArrayEquals(new int[]{-1, 0, 8, 10, 11}, sorted);
    }

    @Test
    public void sortPFiveElementsUnordered() {
        int[] data = new int[]{-1, 11, 8, 0, 10};
        int[] sorted = sortP(data);
        assertArrayEquals(new int[]{-1, 0, 8, 10, 11}, sorted);
    }
}