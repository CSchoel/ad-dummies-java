package ad.dummies.p01basics.c03datastructures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static ad.dummies.p01basics.c03datastructures.E10Iterator.*;

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
 * @see E10Iterator
 */
class E10IteratorTest {

    private IntList lstZero;

    private IntList lstFive;

    @BeforeEach
    public void createLists() {
        lstZero = new Nil();
        lstFive = new Nil();
        for(int x : new int[]{5, 1, -3, 13, -8}) {
            lstFive = new Cons(x, lstFive);
        }
    }

    @Test
    void listSumInnerNil() {
        assertEquals(0, listSumInner(lstZero));
    }

    @Test
    void listSumInnerFiveElements() {
        assertEquals(8, listSumInner(lstFive));
    }

    @Test
    void listSumOuterNil() {
        assertEquals(0, listSumOuter(lstZero));
    }

    @Test
    void listSumOuterFiveElements() {
        assertEquals(8, listSumOuter(lstFive));
    }

    @Test
    public void mainMethodDoesNotThrowAnyExceptions() {
        E10Iterator.main(new String[0]);
    }
}