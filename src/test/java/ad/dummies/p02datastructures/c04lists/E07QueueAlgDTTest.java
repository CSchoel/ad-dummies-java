package ad.dummies.p02datastructures.c04lists;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static ad.dummies.p02datastructures.c04lists.E07QueueAlgDT.*;
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
 * @see E07QueueAlgDT
 */
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
class E07QueueAlgDTTest {

    @Test
    public void queueEnqueueAndThenDequeueOneElement() {
        Queue q = new Queue();
        q = q.enqueue(5);
        assertEquals(5, q.dequeue().value);
    }

    @Test
    public void queueEnqueueAndThenDequeueTwoElements() {
        Queue q = new Queue();
        q = q.enqueue(5);
        q = q.enqueue(-3);
        Queue.ValueAndQueue vq = q.dequeue();
        q = vq.queue;
        assertEquals(5, vq.value);
        vq = q.dequeue();
        q = vq.queue;
        assertEquals(-3, vq.value);
    }

    @Test
    public void queueDequeueInBetweenEnqueuesDoesNotDisturbOrder() {
        Queue q = new Queue();
        q = q.enqueue(1);
        q = q.enqueue(2);
        Queue.ValueAndQueue vq = q.dequeue();
        q = vq.queue;
        assertEquals(1, vq.value);
        q = q.enqueue(3);
        q = q.enqueue(4);
        vq = q.dequeue();
        q = vq.queue;
        assertEquals(2, vq.value);
        vq = q.dequeue();
        q = vq.queue;
        assertEquals(3, vq.value);
        vq = q.dequeue();
        q = vq.queue;
        assertEquals(4, vq.value);
    }

    @Test
    public void queueAEnqueueAndThenDequeueOneElement() {
        QueueA q = new QueueA();
        q = q.enqueue(5);
        assertEquals(5, q.dequeue().value);
    }

    @Test
    public void queueAEnqueueAndThenDequeueTwoElements() {
        QueueA q = new QueueA();
        q = q.enqueue(5);
        q = q.enqueue(-3);
        QueueA.ValueAndQueueA vq = q.dequeue();
        q = vq.queue;
        assertEquals(5, vq.value);
        vq = q.dequeue();
        q = vq.queue;
        assertEquals(-3, vq.value);
    }

    @Test
    public void queueADequeueInBetweenEnqueuesDoesNotDisturbOrder() {
        QueueA q = new QueueA();
        q = q.enqueue(1);
        q = q.enqueue(2);
        QueueA.ValueAndQueueA vq = q.dequeue();
        q = vq.queue;
        assertEquals(1, vq.value);
        q = q.enqueue(3);
        q = q.enqueue(4);
        vq = q.dequeue();
        q = vq.queue;
        assertEquals(2, vq.value);
        vq = q.dequeue();
        q = vq.queue;
        assertEquals(3, vq.value);
        vq = q.dequeue();
        q = vq.queue;
        assertEquals(4, vq.value);
    }

    @Test
    public void mainMethodDoesNotThrowAnyExceptions() {
        main(new String[0]);
    }
}