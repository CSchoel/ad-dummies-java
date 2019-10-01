package ad.dummies.p01basics.c03datastructures;

import ad.dummies.p01basics.c01algorithms.E02GGT;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
class E06GGTTest {

    @CsvSource({"0,0,0", "0,4,4", "4,0,4", "1,0,1", "0,1,1"})
    @ParameterizedTest(name = "ggt({0},{1})")
    public void GGTRecZeroInputs(int n, int m, int expected) {
        assertEquals(expected, E06GGT.GGTRec(n, m));
    }

    @Test
    public void GGTRecNoCommonPrimeFactor() {
        assertEquals(1, E06GGT.GGTRec(3 * 7 * 11, 5 * 13 * 17));
    }

    @Test
    public void GGTRecOneCommonPrimeFactor() {
        assertEquals(3, E06GGT.GGTRec(3 * 7 * 13, 3 * 3 * 11));
    }

    @Test
    public void GGTRecTwoCommonPrimeFactors() {
        assertEquals(55, E06GGT.GGTRec(5 * 11 * 23, 5 * 11 * 2 * 2));
    }

    @Test
    public void GGTRecSameCommonPrimeFactorMultipleTimes() {
        assertEquals(1024, E06GGT.GGTRec(1024, 1024 * 13));
    }

    @CsvSource({"0,0,0", "0,4,4", "4,0,4", "1,0,1", "0,1,1"})
    @ParameterizedTest(name = "ggt({0},{1})")
    public void GGTIterZeroInputs(int n, int m, int expected) {
        assertEquals(expected, E06GGT.GGTIter(n, m));
    }

    @Test
    public void GGTIterNoCommonPrimeFactor() {
        assertEquals(1, E06GGT.GGTIter(3 * 7 * 11, 5 * 13 * 17));
    }

    @Test
    public void GGTIterOneCommonPrimeFactor() {
        assertEquals(3, E06GGT.GGTIter(3 * 7 * 13, 3 * 3 * 11));
    }

    @Test
    public void GGTIterTwoCommonPrimeFactors() {
        assertEquals(55, E06GGT.GGTIter(5 * 11 * 23, 5 * 11 * 2 * 2));
    }

    @Test
    public void GGTIterSameCommonPrimeFactorMultipleTimes() {
        assertEquals(1024, E06GGT.GGTIter(1024, 1024 * 13));
    }

    @CsvSource({"0,0,0", "0,4,4", "4,0,4", "1,0,1", "0,1,1"})
    @ParameterizedTest(name = "ggt({0},{1})")
    public void GGTIterTupleZeroInputs(int n, int m, int expected) {
        assertEquals(expected, E06GGT.GGTIterTuple(n, m));
    }

    @Test
    public void GGTIterTupleNoCommonPrimeFactor() {
        assertEquals(1, E06GGT.GGTIterTuple(3 * 7 * 11, 5 * 13 * 17));
    }

    @Test
    public void GGTIterTupleOneCommonPrimeFactor() {
        assertEquals(3, E06GGT.GGTIterTuple(3 * 7 * 13, 3 * 3 * 11));
    }

    @Test
    public void GGTIterTupleTwoCommonPrimeFactors() {
        assertEquals(55, E06GGT.GGTIterTuple(5 * 11 * 23, 5 * 11 * 2 * 2));
    }

    @Test
    public void GGTIterTupleSameCommonPrimeFactorMultipleTimes() {
        assertEquals(1024, E06GGT.GGTIterTuple(1024, 1024 * 13));
    }

    @Test
    public void mainMethodDoesNotThrowAnyExceptions() {
        E06GGT.main(new String[0]);
    }
}