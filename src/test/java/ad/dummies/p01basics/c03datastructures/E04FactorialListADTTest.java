package ad.dummies.p01basics.c03datastructures;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static ad.dummies.p01basics.c03datastructures.E04FactorialListADT.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
class E04FactorialListADTTest {

    @Test
    void fListZero() {
        FactorialList lst = fList(0);

        assertTrue(lst instanceof Cons);
        assertEquals(1, ((Cons) lst).value());
        assertTrue(((Cons) lst).next() instanceof Nil);
    }

    @Test
    void fListFour() {
        FactorialList lst = fList(4);

        // extract first value
        assertTrue(lst instanceof Cons);
        assertEquals(24, ((Cons) lst).value());
        lst = ((Cons) lst).next();

        // extract second value
        assertTrue(lst instanceof Cons);
        assertEquals(6, ((Cons) lst).value());
        lst = ((Cons) lst).next();

        // extract third value
        assertTrue(lst instanceof Cons);
        assertEquals(2, ((Cons) lst).value());
        lst = ((Cons) lst).next();

        // extract fourth value
        assertTrue(lst instanceof Cons);
        assertEquals(1, ((Cons) lst).value());
        lst = ((Cons) lst).next();

        assertTrue(((Cons) lst).next() instanceof Nil);
    }

}