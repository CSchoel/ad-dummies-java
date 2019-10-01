package ad.dummies.p02datastructures.c04lists;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.*;

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