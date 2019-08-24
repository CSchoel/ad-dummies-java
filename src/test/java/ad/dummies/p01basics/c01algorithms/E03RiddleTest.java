package ad.dummies.p01basics.c01algorithms;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class E03RiddleTest {
    @Test
    public void riddle() {
        assertEquals( 1 , E03Riddle.riddle(1, 1));
        assertEquals( 5 , E03Riddle.riddle(1, 5));
        assertEquals( 5 , E03Riddle.riddle(5, 1));
        assertEquals( 0 , E03Riddle.riddle(0, 7));
        assertEquals( 0 , E03Riddle.riddle(7, 0));
        assertEquals(14, E03Riddle.riddle(2, 7));
        assertEquals(42, E03Riddle.riddle(6, 7));
        assertEquals(1024, E03Riddle.riddle(32, 32));
        assertEquals(
                3 * 7 * 7 * 11 * 11 * 13 * 13 * 15,
                E03Riddle.riddle(3 * 7 * 7 * 11, 11 * 13 * 13 * 15)
        );
        assertEquals(1 << 31, E03Riddle.riddle(1 << 15, 1 << 16));
    }

    @Test
    public void riddleLoop() {
        Random r = new Random();
        for(int i = 0; i < 1000; ++i) {
            int x = r.nextInt(Short.MAX_VALUE);
            int y = r.nextInt(Short.MAX_VALUE);
            int res = E03Riddle.riddle(x, y);
            String msg = String.format("%d * %d != %d", x, y, res);
            assertEquals(x * y, res, msg);
        }
    }
}
