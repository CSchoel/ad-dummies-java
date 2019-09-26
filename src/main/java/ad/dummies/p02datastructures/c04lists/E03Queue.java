package ad.dummies.p02datastructures.c04lists;

public class E03Queue {
    private static class Queue {
        private static class Cell {
            int content;
            Cell next;
            public Cell(int content, Cell next) {
                this.content = content;
                this.next = next;
            }
        }

        private Cell queueHead;
        private Cell queueEnd;
        public void enqueue(int x) {
            Cell newCell = new Cell(x, null);
            if (queueHead == null) {
                queueHead = newCell;
                queueEnd = newCell;
            } else {
                queueEnd.next = newCell;
                queueEnd = newCell;
            }
        }
        public int dequeue() {
            assert queueHead != null; // queue must not be empty
            Cell firstCell = queueHead;
            if (firstCell.next == null) {
                queueHead = null;
                queueEnd = null;
            } else {
                queueHead = queueHead.next;
            }
            return firstCell.content;
        }
    }
}
