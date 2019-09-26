package ad.dummies.p02datastructures.c04lists;

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
            newCell.next = prev.next;
            if (index == 0) {
                listHead = newCell;
            } else {
                prev.next = newCell;
            }
        }
    }
}
