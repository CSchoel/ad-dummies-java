package ad.dummies.p01basics.c03datastructures;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.*;

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
}