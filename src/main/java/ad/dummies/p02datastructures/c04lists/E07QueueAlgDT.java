package ad.dummies.p02datastructures.c04lists;

public class E07QueueAlgDT {
    public static class Queue {
        public interface QueueRep {
            QueueRep addToTail(int x);
        }
        public static class Empty implements QueueRep {

            @Override
            public QueueRep addToTail(int x) {
                return new Entries(x, this);
            }
        }
        public static class Entries implements QueueRep {
            public final int value;
            public final QueueRep rest;
            public Entries(int value, QueueRep rest) {
                this.value = value;
                this.rest = rest;
            }

            public QueueRep addToTail(int x) {
                return new Entries(value, rest.addToTail(x));
            }
        }

        public static class ValueAndQueue {
            public final int value;
            public final Queue queue;
            public ValueAndQueue(int value, Queue queue) {
                this.value = value;
                this.queue = queue;
            }
        }

        QueueRep queueRep;
        public Queue() {
            queueRep = new Empty();
        }
        private Queue(QueueRep queueRep) {
            this.queueRep = queueRep;
        }

        public Queue enqueue(int x) {
            return new Queue(queueRep.addToTail(x));
        }

        public ValueAndQueue dequeue() {
            assert queueRep instanceof Entries;
            Entries queueEntries = (Entries) queueRep;
            return new ValueAndQueue(queueEntries.value, new Queue(queueEntries.rest));
        }
    }

    public static class QueueA {
        public interface QueueRep {
            QueueRep reverse(QueueRep acc);
            default QueueRep reverse() {
                return this.reverse(new Empty());
            }
        }
        public static class Empty implements QueueRep {
            @Override
            public QueueRep reverse(QueueRep acc) {
                return acc;
            }
        }
        public static class Entries implements QueueRep {
            public final int value;
            public final QueueRep rest;
            public Entries(int value, QueueRep rest) {
                this.value = value;
                this.rest = rest;
            }

            @Override
            public QueueRep reverse(QueueRep acc) {
                return rest.reverse(new Entries(value, acc));
            }
        }

        public static class ValueAndQueueA {
            public final int value;
            public final QueueA queue;
            public ValueAndQueueA(int value, QueueA queue) {
                this.value = value;
                this.queue = queue;
            }
        }

        private QueueRep queueIn;
        private QueueRep queueOut;
        public QueueA() {
            queueIn = new Empty();
            queueOut = new Empty();
        }

        public QueueA(QueueRep queueIn, QueueRep queueOut) {
            this.queueIn = queueIn;
            this.queueOut = queueOut;
        }

        public QueueA enqueue(int x) {
            return new QueueA(new Entries(x, queueIn), queueOut);
        }

        public ValueAndQueueA dequeue() {
            if (queueOut instanceof Entries) {
                Entries entriesOut = (Entries) queueOut;
                return new ValueAndQueueA(entriesOut.value, new QueueA(queueIn, entriesOut.rest));
            } else {
                Entries newQueueOut = (Entries) queueIn.reverse();
                return new ValueAndQueueA(newQueueOut.value, new QueueA(new Empty(), newQueueOut.rest));
            }
        }
    }
}
