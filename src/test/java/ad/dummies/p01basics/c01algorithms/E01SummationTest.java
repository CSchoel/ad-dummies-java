package ad.dummies.p01basics.c01algorithms;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.function.Function;

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
 * @see E01Summation
 */
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
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

    @Test
    public void mainMethodDoesNotThrowAnyExceptions() {
        E01Summation.main(new String[0]);

    }
}
