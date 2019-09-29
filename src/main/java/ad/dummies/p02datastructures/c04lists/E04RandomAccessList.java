package ad.dummies.p02datastructures.c04lists;

import java.util.Arrays;
import java.util.function.Function;

public class E04RandomAccessList {
    public static class List {
        private static class Cell {
            int content;
            Cell next;
            public Cell(int content, Cell next) {
                this.content = content;
                this.next = next;
            }
        }

        private Cell listHead;


        public void insertAtPos1(int x, int index) {
            Cell newCell = new Cell(x, null);
            Cell prev = null;
            Cell pos = listHead;
            for(int i = 0; i < index; i++) {
                prev = pos;
                pos = pos.next;
            }
            newCell.next = pos;
            if (prev == null) {
                listHead = newCell;
            } else {
                prev.next = newCell;
            }
        }

        public void insertAtPos2(int x, int index) {
            // NOTE: Using explicit pointers is not possible in Java.
            // This is rarely a drawback since using explicit pointers
            // complicates the code and makes it more error prone. They should
            // only be used as a last resort.
            // Instead of the pointer variant I implemented a version of the
            // insert algorithm that saves two lines of code by just keeping
            // the reference to one instead of two Cells.
            Cell newCell = new Cell(x, null);
            Cell prev = listHead;
            for(int i = 1; i < index; i++) {
                prev = prev.next;
            }
            if (index == 0) {
                listHead = newCell;
            } else {
                newCell.next = prev.next;
                prev.next = newCell;
            }
        }

        public int get(int i) {
            Cell cur = listHead;
            for(int n = i; n > 0; n--) { cur = cur.next; }
            return cur.content;
        }

        public int size() {
            Cell cur = listHead;
            int n = 0;
            while(cur != null) {
                cur = cur.next;
                n++;
            }
            return n;
        }
    }

    public static void main(String[] args) {
        List lst = new List();
        Function<List, String> toS = l -> {
            int[] data = new int[l.size()];
            for(int i = 0; i < data.length; i++) {
                data[i] = l.get(i);
            }
            return Arrays.toString(data);
        };
        int[][] inserts = {{5, 0}, {6, 1}, {0, 1}, {-1, 3}};
        System.out.println("insertAtPos1");
        for(int[] ins: inserts) {
            System.out.printf("Inserting value %d at position %d\n", ins[0], ins[1]);
            lst.insertAtPos1(ins[0], ins[1]);
            System.out.printf("List content: %s\n", toS.apply(lst));
        }
        System.out.println();
        lst = new List();
        System.out.println("insertAtPos2");
        for(int[] ins: inserts) {
            System.out.printf("Inserting value %d at position %d\n", ins[0], ins[1]);
            lst.insertAtPos2(ins[0], ins[1]);
            System.out.printf("List content: %s\n", toS.apply(lst));
        }
    }
}
