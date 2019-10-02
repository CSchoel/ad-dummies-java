package ad.dummies.p01basics.c03datastructures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static ad.dummies.p01basics.c03datastructures.E05ListSumAlgDT.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
 * @see E05ListSumAlgDT
 */
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class E05ListSumAlgDTTest {

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
    public void listSumNil() {
        assertEquals(0, E05ListSumAlgDT.listSum(lstZero));
    }

    @Test
    public void listSumFive() {
        assertEquals(8, E05ListSumAlgDT.listSum(lstFive));
    }

    @Test
    public void listSumRnil() {
        assertEquals(0, E05ListSumAlgDT.listSumR(lstZero));
    }

    @Test
    public void listSumRfive() {
        assertEquals(8, E05ListSumAlgDT.listSumR(lstFive));
    }

    @Test
    public void listSumRObjectOrientedNil() {
        assertEquals(0, lstZero.listSumR());
    }

    @Test
    public void listSumRObjectOrientedFive() {
        assertEquals(8, lstFive.listSumR());
    }

    @Test
    public void mainMethodDoesNotThrowAnyExceptions() {
        E05ListSumAlgDT.main(new String[0]);
    }
}
