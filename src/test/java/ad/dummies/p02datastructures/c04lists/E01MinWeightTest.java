package ad.dummies.p02datastructures.c04lists;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
class E01MinWeightTest {

    @Test
    public void minWeight2IterOneElement() {
        assertEquals(90, E01MinWeight.minWeight2Iter(List.of(90.0)));
    }

    @Test
    public void minWeight2IterFirstOfTwo() {
        assertEquals(90, E01MinWeight.minWeight2Iter(List.of(90.0, 94.5)));
    }

    @Test
    public void minWeight2IterSecondOfTwo() {
        assertEquals(90, E01MinWeight.minWeight2Iter(List.of(94.5, 90.0)));
    }

    @Test
    public void minWeight2IterAscending() {
        assertEquals(70, E01MinWeight.minWeight2Iter(List.of(
                70.0, 75.0, 80.0, 85.0, 90.0, 95.0
        )));
    }

    @Test
    public void minWeight2IterDescending() {
        assertEquals(70, E01MinWeight.minWeight2Iter(List.of(
                95.0, 90.0, 85.0, 80.0, 75.0, 70.0
        )));
    }

    @Test
    public void minWeight2IterUnordered() {
        assertEquals(70, E01MinWeight.minWeight2Iter(List.of(
                95.0, 75.0, 85.0, 70.0, 80.0, 90.0
        )));
    }

    @Test
    public void minWeight2IterMinValueExistsTwice() {
        Double[] people = {
                95.0, 70.0, 85.0, 70.0, 80.0, 90.0
        };
        double res = E01MinWeight.minWeight2Iter(Arrays.asList(people));
        assertEquals(70, res);
    }

    @Test
    public void minWeight2IterAllEqual() {
        Double[] people = {
                80.0, 80.0, 80.0, 80.0, 80.0, 80.0
        };
        double res = E01MinWeight.minWeight2Iter(Arrays.asList(people));
        assertEquals(80, res);
    }

    @Test
    public void minWeight2OneElement() {
        assertEquals(0, E01MinWeight.minWeight2Array(new double[]{90}));
    }

    @Test
    public void minWeight2FirstOfTwo() {
        assertEquals(0, E01MinWeight.minWeight2Array(new double[]{90, 94.5}));
    }

    @Test
    public void minWeight2SecondOfTwo() {
        assertEquals(1, E01MinWeight.minWeight2Array(new double[]{94.5, 90}));
    }

    @Test
    public void minWeight2Ascending() {
        assertEquals(0, E01MinWeight.minWeight2Array(new double[]{
                70, 75, 80, 85, 90, 95
        }));
    }

    @Test
    public void minWeight2Descending() {
        assertEquals(5, E01MinWeight.minWeight2Array(new double[]{
                95, 90, 85, 80, 75, 70
        }));
    }

    @Test
    public void minWeight2Unordered() {
        assertEquals(3, E01MinWeight.minWeight2Array(new double[]{
                95, 75, 85, 70, 80, 90
        }));
    }

    @Test
    public void minWeight2MinValueExistsTwice() {
        double[] people = {
                95, 70, 85, 70, 80, 90
        };
        int res = E01MinWeight.minWeight2Array(people);
        // two possible positions => only check value at position
        assertEquals(70, people[res]);
    }

    @Test
    public void minWeight2AllEqual() {
        double[] people = {
                80, 80, 80, 80, 80, 80
        };
        int res = E01MinWeight.minWeight2Array(people);
        // six possible positions => only check value at position
        assertEquals(80, people[res]);
    }

    @Test
    public void mainMethodDoesNotThrowAnyExceptions() {
        E01MinWeight.main(new String[0]);
    }
}