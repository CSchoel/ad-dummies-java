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
 * @see E02FactorialArray
 */
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
class E02FactorialArrayTest {

    @Test
    void fArray1zero() {
        int[] ar = E02FactorialArray.fArray1(0);
        assertArrayEquals(new int[]{1}, ar);
    }

    @Test
    void fArray1four() {
        int[] ar = E02FactorialArray.fArray1(4);
        assertArrayEquals(new int[]{1, 1, 2, 6, 24}, ar);
    }

    @Test
    void fArray2zero() {
        E02FactorialArray.IntTuple[] ar = E02FactorialArray.fArray2(0);
        assertEquals(0, ar[0].v1());
        assertEquals(1, ar[0].v2());
    }

    @Test
    void fArray2four() {
        E02FactorialArray.IntTuple[] ar = E02FactorialArray.fArray2(4);
        assertEquals(0, ar[0].v1());
        assertEquals(1, ar[0].v2());

        assertEquals(1, ar[1].v1());
        assertEquals(1, ar[1].v2());

        assertEquals(2, ar[2].v1());
        assertEquals(2, ar[2].v2());

        assertEquals(3, ar[3].v1());
        assertEquals(6, ar[3].v2());

        assertEquals(4, ar[4].v1());
        assertEquals(24, ar[4].v2());
    }

    @Test
    void fArray3zero() {
        E02FactorialArray.FactRecord[] ar = E02FactorialArray.fArray3(0);

        assertEquals(0, ar[0].v);
        assertEquals(1, ar[0].f);
    }

    @Test
    void fArray3four() {
        E02FactorialArray.FactRecord[] ar = E02FactorialArray.fArray3(4);

        assertEquals(0, ar[0].v);
        assertEquals(1, ar[0].f);

        assertEquals(1, ar[1].v);
        assertEquals(1, ar[1].f);

        assertEquals(2, ar[2].v);
        assertEquals(2, ar[2].f);

        assertEquals(3, ar[3].v);
        assertEquals(6, ar[3].f);

        assertEquals(4, ar[4].v);
        assertEquals(24, ar[4].f);
    }

    @Test
    public void mainMethodDoesNotThrowAnyExceptions() {
        E02FactorialArray.main(new String[0]);
    }
}