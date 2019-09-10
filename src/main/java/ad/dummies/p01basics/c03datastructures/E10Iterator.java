package ad.dummies.p01basics.c03datastructures;

import java.util.Iterator;
import java.util.function.BiFunction;

public class E10Iterator {
    public interface IntList extends Iterable<Integer> {
        <R> R traverseWith(BiFunction<? super Integer, R, R> f, R start);
    }
    public static class Nil implements IntList {
        @Override
        public Iterator<Integer> iterator() {
            return new IntListIterator(this);
        }

        public <R> R traverseWith(BiFunction<? super Integer, R, R> f, R start) {
            return start;
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

        @Override
        public <R> R traverseWith(BiFunction<? super Integer, R, R> f, R start) {
            return f.apply(value, next.traverseWith(f, start));
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
    public static int listSumInner(IntList lst) {
        return lst.traverseWith(Integer::sum, 0);
    }

    public static int listSumOuter(IntList lst) {
        int s = 0;
        for(int x: lst) { // foreach is possible, because IntList is Iterable
            s += x;
        }
        return s;
    }

}
