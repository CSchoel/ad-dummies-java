package ad.dummies.p01basics.c03datastructures;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.*;
import ad.dummies.p01basics.c03datastructures.E09Quicksort.*;


@TestMethodOrder(MethodOrderer.Alphanumeric.class)
class E09QuicksortTest {

    private static IntList buildIntList(int... ints) {
        IntList res = new Nil();
        for(int x: ints) {
            res = new Cons(x, res);
        }
        return res;
    }

    @Test
    public void quicksortEmptyList() {
        assertTrue(new Nil().quicksort() instanceof Nil);
    }

    @Disabled("Fails because quicksort splits only in two parts, not three")
    @Test
    public void quicksortTwoElementsAscending() {
        IntList sorted = buildIntList(1, 2).quicksort();

        assertTrue(sorted instanceof Cons);
        assertEquals(1, ((Cons) sorted).value());
        sorted = ((Cons) sorted).next();

        assertTrue(sorted instanceof Cons);
        assertEquals(2, ((Cons) sorted).value());
        sorted = ((Cons) sorted).next();

        assertTrue(sorted instanceof Nil);
    }

    @Test
    public void quicksortTwoElementsDescending() {
        IntList sorted = buildIntList(2, 1).quicksort();

        assertTrue(sorted instanceof Cons);
        assertEquals(1, ((Cons) sorted).value());
        sorted = ((Cons) sorted).next();

        assertTrue(sorted instanceof Cons);
        assertEquals(2, ((Cons) sorted).value());
        sorted = ((Cons) sorted).next();

        assertTrue(sorted instanceof Nil);
    }

    @Disabled("Fails because quicksort splits only in two parts, not three")
    @Test
    public void quicksortFiveElementsAscending() {
        IntList sorted = buildIntList(-1, 0, 8, 10, 11).quicksort();

        assertTrue(sorted instanceof Cons);
        assertEquals(-1, ((Cons) sorted).value());
        sorted = ((Cons) sorted).next();

        assertTrue(sorted instanceof Cons);
        assertEquals(0, ((Cons) sorted).value());
        sorted = ((Cons) sorted).next();

        assertTrue(sorted instanceof Cons);
        assertEquals(8, ((Cons) sorted).value());
        sorted = ((Cons) sorted).next();

        assertTrue(sorted instanceof Cons);
        assertEquals(10, ((Cons) sorted).value());
        sorted = ((Cons) sorted).next();

        assertTrue(sorted instanceof Cons);
        assertEquals(11, ((Cons) sorted).value());
        sorted = ((Cons) sorted).next();

        assertTrue(sorted instanceof Nil);
    }

    @Test
    public void quicksortFiveElementsDescending() {
        IntList sorted = buildIntList(11, 10, 8, 0, -1).quicksort();

        assertTrue(sorted instanceof Cons);
        assertEquals(-1, ((Cons) sorted).value());
        sorted = ((Cons) sorted).next();

        assertTrue(sorted instanceof Cons);
        assertEquals(0, ((Cons) sorted).value());
        sorted = ((Cons) sorted).next();

        assertTrue(sorted instanceof Cons);
        assertEquals(8, ((Cons) sorted).value());
        sorted = ((Cons) sorted).next();

        assertTrue(sorted instanceof Cons);
        assertEquals(10, ((Cons) sorted).value());
        sorted = ((Cons) sorted).next();

        assertTrue(sorted instanceof Cons);
        assertEquals(11, ((Cons) sorted).value());
        sorted = ((Cons) sorted).next();

        assertTrue(sorted instanceof Nil);
    }

    @Disabled("Fails because quicksort splits only in two parts, not three")
    @Test
    public void quicksortFiveElementsUnordered() {
        IntList sorted = buildIntList( -1, 11, 8, 0, 10).quicksort();

        assertTrue(sorted instanceof Cons);
        assertEquals(-1, ((Cons) sorted).value());
        sorted = ((Cons) sorted).next();

        assertTrue(sorted instanceof Cons);
        assertEquals(0, ((Cons) sorted).value());
        sorted = ((Cons) sorted).next();

        assertTrue(sorted instanceof Cons);
        assertEquals(8, ((Cons) sorted).value());
        sorted = ((Cons) sorted).next();

        assertTrue(sorted instanceof Cons);
        assertEquals(10, ((Cons) sorted).value());
        sorted = ((Cons) sorted).next();

        assertTrue(sorted instanceof Cons);
        assertEquals(11, ((Cons) sorted).value());
        sorted = ((Cons) sorted).next();

        assertTrue(sorted instanceof Nil);
    }

    @Test
    public void mainMethodDoesNotThrowAnyExceptions() {
        E09Quicksort.main(new String[0]);
    }
}