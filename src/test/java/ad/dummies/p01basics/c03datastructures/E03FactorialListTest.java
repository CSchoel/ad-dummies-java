package ad.dummies.p01basics.c03datastructures;

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
 * @see E03FactorialList
 */
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
class E03FactorialListTest {

    @Test
    void fListZero() {
        E03FactorialList.FactList lst = E03FactorialList.fList(0);

        assertNull(lst);
    }

    @Test
    void fListFour() {
        E03FactorialList.FactList lst = E03FactorialList.fList(4);

        assertEquals(24, lst.v);
        assertEquals(6, lst.n.v);
        assertEquals(2, lst.n.n.v);
        assertEquals(1, lst.n.n.n.v);
        assertNull(lst.n.n.n.n);
    }


    @Test
    void fListAEzero() {
        E03FactorialList.FactList lst = E03FactorialList.fListAE(0);

        assertEquals(1, lst.v);
        assertNull(lst.n);
    }

    @Test
    void fListAEfour() {
        E03FactorialList.FactList lst = E03FactorialList.fListAE(4);

        assertEquals(1, lst.v);
        assertEquals(1, lst.n.v);
        assertEquals(2, lst.n.n.v);
        assertEquals(6, lst.n.n.n.v);
        assertEquals(24, lst.n.n.n.n.v);
        assertNull(lst.n.n.n.n.n);
    }

    @Test
    public void mainMethodDoesNotThrowAnyExceptions() {
        E03FactorialList.main(new String[0]);
    }
}