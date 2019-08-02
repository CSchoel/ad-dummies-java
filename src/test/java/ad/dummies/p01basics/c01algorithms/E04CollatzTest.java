package ad.dummies.p01basics.c01algorithms;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class E04CollatzTest {
    private ArrayList<Integer> output;
    private PrintStream oldOut;

    @BeforeEach
    public void hackSystemOut() {
        output = new ArrayList<>();
        oldOut = System.out;
        System.setOut(new PrintStream(System.out){
            @Override
            public void println(int x) {
                super.println(x);
                output.add(x);
            }
        });
    }
    @AfterEach
    public void unhackSystemOut() {
        System.setOut(oldOut);
    }

    @Test
    public void testCollatz25() {
        E04Collatz.collatz(25);
        assertEquals(
                List.of(
                    25, 76, 38, 19, 58, 29, 88, 44, 22, 11, 34, 17, 52,
                    26, 13, 40, 20, 10, 5, 16, 8, 4, 2, 1
                ),
                output
        );
    }

    @Test
    public void testCollatz27() {
        E04Collatz.collatz(27);
        assertEquals(112, output.size());
        // FIXME error in the book (9323 instead of 9232)
        assertEquals(9232, Collections.max(output));
    }

    @Test
    public void testCollatz32() {
        E04Collatz.collatz(32);
        assertEquals(List.of(32, 16, 8, 4, 2, 1), output);
    }
}
