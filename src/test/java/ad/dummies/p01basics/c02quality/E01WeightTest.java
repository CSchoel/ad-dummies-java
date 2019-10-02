package ad.dummies.p01basics.c02quality;

import ad.dummies.p01basics.c01algorithms.E01Summation;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

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
 * @see E01Weight
 */

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class E01WeightTest {

    @Test
    public void minWeight1OneElement() {
        assertEquals(0, E01Weight.minWeight1(new double[]{90}));
    }

    @Test
    public void minWeight1FirstOfTwo() {
        assertEquals(0, E01Weight.minWeight1(new double[]{90, 94.5}));
    }

    @Test
    public void minWeight1SecondOfTwo() {
        assertEquals(1, E01Weight.minWeight1(new double[]{94.5, 90}));
    }

    @Test
    public void minWeight1Ascending() {
        assertEquals(0, E01Weight.minWeight1(new double[]{
                70, 75, 80, 85, 90, 95
        }));
    }

    @Test
    public void minWeight1Descending() {
        assertEquals(5, E01Weight.minWeight1(new double[]{
                95, 90, 85, 80, 75, 70
        }));
    }

    @Test
    public void minWeight1Unordered() {
        assertEquals(3, E01Weight.minWeight1(new double[]{
                95, 75, 85, 70, 80, 90
        }));
    }

    @Test
    public void minWeight1MinValueExistsTwice() {
        double[] people = {
                95, 70, 85, 70, 80, 90
        };
        int res = E01Weight.minWeight1(people);
        // two possible positions => only check value at position
        assertEquals(70, people[res]);
    }

    @Test
    public void minWeight1AllEqual() {
        double[] people = {
                80, 80, 80, 80, 80, 80
        };
        int res = E01Weight.minWeight1(people);
        // six possible positions => only check value at position
        assertEquals(80, people[res]);
    }

    @Test
    public void minWeight2OneElement() {
        assertEquals(0, E01Weight.minWeight2(new double[]{90}));
    }

    @Test
    public void minWeight2FirstOfTwo() {
        assertEquals(0, E01Weight.minWeight2(new double[]{90, 94.5}));
    }

    @Test
    public void minWeight2SecondOfTwo() {
        assertEquals(1, E01Weight.minWeight2(new double[]{94.5, 90}));
    }

    @Test
    public void minWeight2Ascending() {
        assertEquals(0, E01Weight.minWeight2(new double[]{
                70, 75, 80, 85, 90, 95
        }));
    }

    @Test
    public void minWeight2Descending() {
        assertEquals(5, E01Weight.minWeight2(new double[]{
                95, 90, 85, 80, 75, 70
        }));
    }

    @Test
    public void minWeight2Unordered() {
        assertEquals(3, E01Weight.minWeight2(new double[]{
                95, 75, 85, 70, 80, 90
        }));
    }

    @Test
    public void minWeight2MinValueExistsTwice() {
        double[] people = {
                95, 70, 85, 70, 80, 90
        };
        int res = E01Weight.minWeight2(people);
        // two possible positions => only check value at position
        assertEquals(70, people[res]);
    }

    @Test
    public void minWeight2AllEqual() {
        double[] people = {
                80, 80, 80, 80, 80, 80
        };
        int res = E01Weight.minWeight2(people);
        // six possible positions => only check value at position
        assertEquals(80, people[res]);
    }

    @Test
    public void mainMethodDoesNotThrowAnyExceptions() {
        E01Weight.main(new String[0]);
    }
}
