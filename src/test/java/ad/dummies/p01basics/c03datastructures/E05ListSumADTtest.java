package ad.dummies.p01basics.c03datastructures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static ad.dummies.p01basics.c03datastructures.E05ListSumADT.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class E05ListSumADTtest {

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
        assertEquals(0, E05ListSumADT.listSum(lstZero));
    }

    @Test
    public void listSumFive() {
        assertEquals(8, E05ListSumADT.listSum(lstFive));
    }

    @Test
    public void listSumRnil() {
        assertEquals(0, E05ListSumADT.listSumR(lstZero));
    }

    @Test
    public void listSumRfive() {
        assertEquals(8, E05ListSumADT.listSumR(lstFive));
    }

    @Test
    public void listSumRObjectOrientedNil() {
        assertEquals(0, lstZero.listSumR());
    }

    @Test
    public void listSumRObjectOrientedFive() {
        assertEquals(8, lstFive.listSumR());
    }
}
