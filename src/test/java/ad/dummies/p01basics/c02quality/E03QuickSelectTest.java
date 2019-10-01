package ad.dummies.p01basics.c02quality;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
class E03QuickSelectTest {

    @Test
    void quickSelectK1OneElement() {
        assertEquals(5, E03QuickSelect.quickSelect(new double[]{
                5
        }, 1));
    }

    @Test
    void quickSelectK1FirstOfTwo() {
        assertEquals(5, E03QuickSelect.quickSelect(new double[]{
                5, 10
        }, 1));
    }

    @Test
    void quickSelectK1SecondOfTwo() {
        assertEquals(5, E03QuickSelect.quickSelect(new double[]{
                10, 5
        }, 1));
    }

    @Test
    void quickSelectK1Ascending() {
        assertEquals(2, E03QuickSelect.quickSelect(new double[]{
                2, 3, 4, 5, 6, 7, 8, 9, 10
        }, 1));
    }

    @Test
    void quickSelectK1Descending() {
        assertEquals(2, E03QuickSelect.quickSelect(new double[]{
                10, 9, 8, 7, 6, 5, 4, 3, 2
        }, 1));
    }

    @Test
    void quickSelectK1Unordered() {
        assertEquals(2, E03QuickSelect.quickSelect(new double[]{
                6, 10, 8, 4, 3, 7, 2, 9, 5
        }, 1));
    }

    @Test
    void quickSelectK3ThreeElements() {
        assertEquals(10, E03QuickSelect.quickSelect(new double[]{
                10, 5, 3
        }, 3));
    }

    @Test
    void quickSelectK3Ascending() {
        assertEquals(4, E03QuickSelect.quickSelect(new double[]{
                2, 3, 4, 5, 6, 7, 8, 9, 10
        }, 3));
    }

    @Test
    void quickSelectK3Descending() {
        assertEquals(4, E03QuickSelect.quickSelect(new double[]{
                10, 9, 8, 7, 6, 5, 4, 3, 2
        }, 3));
    }

    @Test
    void quickSelectK3Unordered() {
        assertEquals(4, E03QuickSelect.quickSelect(new double[]{
                6, 10, 8, 4, 3, 7, 2, 9, 5
        }, 3));
    }

    @Test
    public void mainMethodDoesNotThrowAnyExceptions() {
        E03QuickSelect.main(new String[0]);

    }
}