package ad.dummies.p01basics.c03datastructures;

import java.util.Iterator;

public class E09Iterator {
    public interface IntList extends Iterable<Integer> {}
    public static class Nil implements IntList {
        @Override
        public Iterator<Integer> iterator() {
            return new IntListIterator(this);
        }
    }
    public static class Cons implements IntList {
        private final int value;
        private final IntList next;
        public Cons(int value, IntList next) {
            this.value = value;
            this.next = next;
        }
        public int value() { return value; }
        public IntList next() { return next; }

        @Override
        public Iterator<Integer> iterator() {
            return new IntListIterator(this);
        }
    }
    public static class IntListIterator implements Iterator<Integer> {
        IntList current;
        public IntListIterator(IntList start) {
            current = start;
        }

        @Override
        public boolean hasNext() {
            return !(current instanceof Nil);
        }

        @Override
        public Integer next() {
            Cons c = (Cons) current;
            int tmp = c.value();
            current = c.next();
            return tmp;
        }
    }
    public static int listSum(IntList lst) {
        int s = 0;
        for(int x: lst) { // foreach is possible, because IntList is Iterable
            s += x;
        }
        return s;
    }

}
