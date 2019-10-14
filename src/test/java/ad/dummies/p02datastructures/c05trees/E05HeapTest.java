package ad.dummies.p02datastructures.c05trees;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.*;
import static ad.dummies.p02datastructures.c05trees.E05Heap.*;

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
 * @see E05Heap
 */
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
class E05HeapTest {

    @Test
    public void maxOfOneElementHeapIsThatElement() {
        Heap h = new Heap();
        h.insert(2);
        assertEquals(2, h.max());
    }

    @Test
    public void maxOfTwoElementHeapIsMaximumElementWhenMaxIsInsertedLast() {
        Heap h = new Heap();
        h.insert(1);
        h.insert(2);
        assertEquals(2, h.max());
    }

    @Test
    public void maxOfTwoElementHeapIsMaximumElementWhenMaxIsInsertedFirst() {
        Heap h = new Heap();
        h.insert(2);
        h.insert(1);
        assertEquals(2, h.max());
    }

    @Test
    public void maxOfFiveElementHeapIsMaximumElement() {
        Heap h = new Heap();
        h.insert(2);
        h.insert(1);
        h.insert(6);
        h.insert(5);
        h.insert(4);
        assertEquals(6, h.max());
    }

    @Test
    public void mainMethodDoesNotThrowAnyExceptions() {
        main(new String[0]);
    }
}