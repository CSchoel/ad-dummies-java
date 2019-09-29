package ad.dummies.p02datastructures.c04lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    public static void main(String[] args) {
        int[] data = {1, 2, 3};
        Queue q = new Queue();
        for(int x: data) { q.enqueue(x); }
        List<Integer> deq = new ArrayList<>();
        for(int i = 0; i < data.length; i++) { deq.add(q.dequeue()); }
        System.out.printf("Enqueuing %s yields dequeue order %s\n",
                Arrays.toString(data), deq
        );

    }
}
