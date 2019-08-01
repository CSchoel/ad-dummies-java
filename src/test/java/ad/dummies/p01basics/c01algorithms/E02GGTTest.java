package ad.dummies.p01basics.c01algorithms;

import org.junit.jupiter.api.Test;

import java.util.function.BiFunction;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class E02GGTTest {
    public void testGGT(BiFunction<Integer, Integer, Integer> ggt) {
        assertEquals(0, ggt.apply(0, 0));
        assertEquals(4, ggt.apply(0, 4));
        assertEquals(4, ggt.apply(4, 0));
        assertEquals(1, ggt.apply(1, 0));
        assertEquals(1, ggt.apply(0, 1));
        assertEquals(2, ggt.apply(4, 2));
        assertEquals(3, ggt.apply(3 * 7 * 13, 3 * 3 * 11));
        assertEquals(55, ggt.apply(5 * 11 * 23, 5 * 11 * 2 * 2));
        assertEquals(1, ggt.apply(3 * 7 * 11, 5 * 13 * 17));
        assertEquals(1024, ggt.apply(1024, 1024 * 13));
        assertEquals(1, ggt.apply((int) Short.MAX_VALUE, Short.MAX_VALUE - 1));
    }

    @Test
    public void testGGT2() {
        testGGT(E02GGT::ggt2);
    }

    @Test
    public void testGGT3() {
        testGGT(E02GGT::ggt3);
    }
}
