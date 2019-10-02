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
 * @see E07FactorialRec
 */
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
class E07FactorialRecTest {

    @Test
    void fRecZero() {
        assertEquals(1, E07FactorialRec.fRec(0));
    }

    @Test
    void fRecFour() {
        assertEquals(24, E07FactorialRec.fRec(4));
    }

    @Test
    void fTailRecZero() {
        assertEquals(1, E07FactorialRec.fTailRec(0));
    }

    @Test
    void fTailRecFour() {
        assertEquals(24, E07FactorialRec.fTailRec(4));
    }

    @Test
    void fIterZero() {
        assertEquals(1, E07FactorialRec.fIter(0));
    }

    @Test
    void fIterFour() {
        assertEquals(24, E07FactorialRec.fIter(4));
    }

    @Test
    void fTailRec2Zero() {
        assertEquals(1, E07FactorialRec.fTailRec2(0));
    }

    @Test
    void fTailRec2Four() {
        assertEquals(24, E07FactorialRec.fTailRec2(4));
    }

    @Test
    void fIter2Zero() {
        assertEquals(1, E07FactorialRec.fIter2(0));
    }

    @Test
    void fIter2Four() {
        assertEquals(24, E07FactorialRec.fIter2(4));
    }

    @Test
    public void mainMethodDoesNotThrowAnyExceptions() {
        E07FactorialRec.main(new String[0]);
    }
}