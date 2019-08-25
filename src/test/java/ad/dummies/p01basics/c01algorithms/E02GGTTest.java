package ad.dummies.p01basics.c01algorithms;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.opentest4j.AssertionFailedError;

import java.math.BigInteger;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class E02GGTTest {

    @Test
    public void ggt1shouldNotTerminate() {
        // assert that ggt1(6, 15) does not complete within 1 ms
        Executable timeout = () -> assertTimeoutPreemptively(Duration.ofMillis(1), () -> {
            E02GGT.ggt1(BigInteger.valueOf(6), BigInteger.valueOf(15));
        });
        assertThrows(AssertionFailedError.class, timeout);
    }

    @CsvSource({"0,0,0", "0,4,4", "4,0,4", "1,0,1", "0,1,1"})
    @ParameterizedTest(name = "ggt({0},{1})")
    public void ggt2aZeroInputs(int n, int m, int expected) {
        assertEquals(expected, E02GGT.ggt2a(n, m));
    }

    @Test
    public void ggt2aNoCommonPrimeFactor() {
        assertEquals(1, E02GGT.ggt2a(3 * 7 * 11, 5 * 13 * 17));
    }

    @Test
    public void ggt2aOneCommonPrimeFactor() {
        assertEquals(3, E02GGT.ggt2a(3 * 7 * 13, 3 * 3 * 11));
    }

    @Test
    public void ggt2aTwoCommonPrimeFactors() {
        assertEquals(55, E02GGT.ggt2a(5 * 11 * 23, 5 * 11 * 2 * 2));
    }

    @Test
    public void ggt2aSameCommonPrimeFactorMultipleTimes() {
        assertEquals(1024, E02GGT.ggt2a(1024, 1024 * 13));
    }

    @CsvSource({"0,0,0", "0,4,4", "4,0,4", "1,0,1", "0,1,1"})
    @ParameterizedTest(name = "ggt({0},{1})")
    public void ggt2bZeroInputs(int n, int m, int expected) {
        assertEquals(expected, E02GGT.ggt2b(n, m));
    }

    @Test
    public void ggt2bNoCommonPrimeFactor() {
        assertEquals(1, E02GGT.ggt2b(3 * 7 * 11, 5 * 13 * 17));
    }

    @Test
    public void ggt2bOneCommonPrimeFactor() {
        assertEquals(3, E02GGT.ggt2b(3 * 7 * 13, 3 * 3 * 11));
    }

    @Test
    public void ggt2bTwoCommonPrimeFactors() {
        assertEquals(55, E02GGT.ggt2b(5 * 11 * 23, 5 * 11 * 2 * 2));
    }

    @Test
    public void ggt2bSameCommonPrimeFactorMultipleTimes() {
        assertEquals(1024, E02GGT.ggt2b(1024, 1024 * 13));
    }

    @CsvSource({"0,0,0", "0,4,4", "4,0,4", "1,0,1", "0,1,1"})
    @ParameterizedTest(name = "ggt({0},{1})")
    public void ggt3ZeroInputs(int n, int m, int expected) {
        assertEquals(expected, E02GGT.ggt3(n, m));
    }

    @Test
    public void ggt3NoCommonPrimeFactor() {
        assertEquals(1, E02GGT.ggt3(3 * 7 * 11, 5 * 13 * 17));
    }

    @Test
    public void ggt3OneCommonPrimeFactor() {
        assertEquals(3, E02GGT.ggt3(3 * 7 * 13, 3 * 3 * 11));
    }

    @Test
    public void ggt3TwoCommonPrimeFactors() {
        assertEquals(55, E02GGT.ggt3(5 * 11 * 23, 5 * 11 * 2 * 2));
    }

    @Test
    public void ggt3SameCommonPrimeFactorMultipleTimes() {
        assertEquals(1024, E02GGT.ggt3(1024, 1024 * 13));
    }
}
