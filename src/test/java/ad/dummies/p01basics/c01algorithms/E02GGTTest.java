package ad.dummies.p01basics.c01algorithms;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.opentest4j.AssertionFailedError;

import java.math.BigInteger;
import java.time.Duration;
import java.util.function.BiFunction;

import static org.junit.jupiter.api.Assertions.*;

public class E02GGTTest {
    public void ggt(BiFunction<Integer, Integer, Integer> ggt) {
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
    public void ggt1() {
        // assert that ggt1(6, 15) does not complete within 1 ms
        Executable timeout = () -> assertTimeoutPreemptively(Duration.ofMillis(1), () -> {
            E02GGT.ggt1(BigInteger.valueOf(6), BigInteger.valueOf(15));
        });
        assertThrows(AssertionFailedError.class, timeout);
    }

    @Test
    public void ggt2a() {
        ggt(E02GGT::ggt2a);
    }

    @Test
    public void ggt2b() {
        ggt(E02GGT::ggt2b);
    }

    @Test
    public void ggt3() {
        ggt(E02GGT::ggt3);
    }
}
