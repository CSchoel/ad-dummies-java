package ad.dummies.p01basics.c01algorithms;

import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class E01SummationTest {
    public void testSummation(Function<Integer, Integer> sum, int maxInput, int maxValue) {
        assertEquals(0, sum.apply(0));
        assertEquals(1, sum.apply(1));
        assertEquals(3, sum.apply(2));
        assertEquals(6, sum.apply(3));
        assertEquals(10, sum.apply(4));
        assertEquals(15, sum.apply(5));
        assertEquals(21, sum.apply(6));
        assertEquals(28, sum.apply(7));
        assertEquals(300, sum.apply(24));
        // highest input possible that will not lead to integer overflow
        assertEquals(maxValue, sum.apply(maxInput));
    }

    public int findHighestInput(Function<Integer, Integer> sum) {
        int highestInput = 0;
        int highestResult = 0;
        while(highestResult >= 0) {
            highestResult = sum.apply(++highestInput);
        }
        highestInput--;
        return highestInput;
    }

    @Test
    public void testSummation1() {
        testSummation(E01Summation::summation1, 65535, 2147450880);
    }

    @Test
    public void testSummation1Highest() {
        assertEquals(65535, findHighestInput(E01Summation::summation1));
    }

    @Test
    public void testSummation2() {
        testSummation(E01Summation::summation2, 46340, 1073720970);
    }

    @Test
    public void testSummation2Highest() {
        assertEquals(46340, findHighestInput(E01Summation::summation2));
    }
}
