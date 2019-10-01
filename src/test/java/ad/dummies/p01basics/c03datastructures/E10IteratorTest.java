package ad.dummies.p01basics.c03datastructures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static ad.dummies.p01basics.c03datastructures.E10Iterator.*;

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