package ad.dummies.p01basics.c01algorithms;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class E01SummationTest {

    public int findHighestInput(Function<Integer, Integer> sum) {
        int highestInput = 0;
        int highestResult = 0;
        while(highestResult >= 0) {
            highestResult = sum.apply(++highestInput);
        }
        highestInput--;
        return highestInput;
    }

    @CsvSource({
            "0,0", "1,1", "3,2", "6,3", "10,4", "15,5", "21,6", "28,7",
            "300, 24"
    })
    @ParameterizedTest(name = "input:{1}")
    public void summation1(int expected, int input) {
        assertEquals(expected, E01Summation.summation1(input));
    }

    @Test
    public void summation1maxValue() {
        // highest input possible that will not lead to integer overflow
        assertEquals(2147450880, E01Summation.summation1(65535));
    }

    @Test
    public void summation1maxInput() {
        assertEquals(65535, findHighestInput(E01Summation::summation1));
    }

    @CsvSource({
            "0,0", "1,1", "3,2", "6,3", "10,4", "15,5", "21,6", "28,7",
            "300, 24"
    })
    @ParameterizedTest(name = "input:{1}")
    public void summation2(int expected, int input) {
        assertEquals(expected, E01Summation.summation2(input));
    }

    @Test
    public void summation2maxValue() {
        // highest input possible that will not lead to integer overflow
        assertEquals(1073720970, E01Summation.summation1(46340));
    }

    @Test
    public void summation2maxInput() {
        assertEquals(46340, findHighestInput(E01Summation::summation2));
    }
}
