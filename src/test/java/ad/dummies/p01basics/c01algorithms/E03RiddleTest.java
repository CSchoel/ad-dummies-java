package ad.dummies.p01basics.c01algorithms;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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
 * @see E03Riddle
 */
public class E03RiddleTest {
    @CsvSource({"1,1,1", "1,5,5", "5,1,5", "2,7,14", "6,7,42", "32,32,1024"})
    @ParameterizedTest(name="riddle({0},{1})")
    public void riddle(int n, int m, int expected) {
        assertEquals( expected , E03Riddle.riddle(n, m));
    }

    @Test
    public void riddleLargeInput() {
        assertEquals(1 << 31, E03Riddle.riddle(1 << 15, 1 << 16));
    }

    @Test
    public void riddleNzero() {
        assertEquals(0, E03Riddle.riddle(0, 7));
    }

    @Test
    public void riddleMzero() {
        assertEquals(0, E03Riddle.riddle(7, 0));
    }

    @Test
    public void mainMethodDoesNotThrowAnyExceptions() {
        E03Riddle.main(new String[0]);

    }
}
