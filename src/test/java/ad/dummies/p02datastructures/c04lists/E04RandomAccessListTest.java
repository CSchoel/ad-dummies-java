package ad.dummies.p02datastructures.c04lists;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class E04RandomAccessListTest {
    @Test
    public void emptyListHasSize0() {
        E04RandomAccessList.List lst = new E04RandomAccessList.List();
        assertEquals(0, lst.size());
    }

    @Test
    public void insert1IntoEmptyList() {
        E04RandomAccessList.List lst = new E04RandomAccessList.List();
        lst.insertAtPos1(5, 0);
        assertEquals(1, lst.size());
        assertEquals(5, lst.get(0));
    }

    @Test
    public void inser1InFrontOfSingleElement() {
        E04RandomAccessList.List lst = new E04RandomAccessList.List();
        lst.insertAtPos1(5, 0);
        lst.insertAtPos1(-5, 0);
        assertEquals(-5, lst.get(0));
        assertEquals(5, lst.get(1));
    }

    @Test
    public void insert1AfterSingleElement() {
        E04RandomAccessList.List lst = new E04RandomAccessList.List();
        lst.insertAtPos1(5, 0);
        lst.insertAtPos1(-5, 1);
        assertEquals(5, lst.get(0));
        assertEquals(-5, lst.get(1));
    }

    @Test
    public void insert1BetweenTwoElements() {
        E04RandomAccessList.List lst = new E04RandomAccessList.List();
        lst.insertAtPos1(5, 0);
        lst.insertAtPos1(8, 0);
        lst.insertAtPos1(1, 1);
        assertEquals(8, lst.get(0));
        assertEquals(1, lst.get(1));
        assertEquals(5, lst.get(2));
    }

    @Test
    public void insert1AfterTwoElements() {
        E04RandomAccessList.List lst = new E04RandomAccessList.List();
        lst.insertAtPos1(5, 0);
        lst.insertAtPos1(8, 0);
        lst.insertAtPos1(1, 2);
        assertEquals(8, lst.get(0));
        assertEquals(5, lst.get(1));
        assertEquals(1, lst.get(2));
    }

    @Test
    public void mainMethodDoesNotThrowAnyExceptions() {
        E04RandomAccessList.main(new String[0]);
    }
}