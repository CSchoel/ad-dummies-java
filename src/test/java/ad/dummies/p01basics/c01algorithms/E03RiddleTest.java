package ad.dummies.p01basics.c01algorithms;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
