package ad.dummies.p02datastructures.c04lists;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.*;

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
 * @see E03Queue
 */
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
class E03QueueTest {

    @Test
    public void enqueueAndThenDequeueOneElement() {
        E03Queue.Queue q = new E03Queue.Queue();
        q.enqueue(14);
        assertEquals(14, q.dequeue());
    }

    @Test
    public void enqueueAndThenDequeueTwoElements() {
        E03Queue.Queue q = new E03Queue.Queue();
        q.enqueue(14);
        q.enqueue(8);
        assertEquals(14, q.dequeue());
        assertEquals(8, q.dequeue());
    }

    @Test
    public void dequeueInBetweenEnqueuesDoesNotDisturbOrder() {
        E03Queue.Queue q = new E03Queue.Queue();
        q.enqueue(1);
        q.enqueue(2);
        assertEquals(1, q.dequeue());
        q.enqueue(3);
        q.enqueue(4);
        assertEquals(2, q.dequeue());
        assertEquals(3, q.dequeue());
        assertEquals(4, q.dequeue());
    }

    @Test
    public void mainMethodDoesNotThrowAnyExceptions() {
        E03Queue.main(new String[0]);
    }
}