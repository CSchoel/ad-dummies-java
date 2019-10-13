package ad.dummies.p02datastructures.c04lists;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.*;

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
 * @see E04RandomAccessList
 */
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
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