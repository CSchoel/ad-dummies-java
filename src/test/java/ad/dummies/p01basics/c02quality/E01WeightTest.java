package ad.dummies.p01basics.c02quality;

import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class E01WeightTest {
    public void minWeight(Function<double[], Integer> max) {
        assertEquals(0, max.apply(new double[]{90}));
        assertEquals(1, max.apply(new double[]{94.5, 90}));
        assertEquals(0, max.apply(new double[]{90, 94.5}));
        assertEquals(0, max.apply(new double[]{90, 94.5}));
    }

    @Test
    public void minWeight1() {
        minWeight(E01Weight::minWeight1);
    }

    @Test
    public void minWeight2() {
        minWeight(E01Weight::minWeight2);
    }
}
