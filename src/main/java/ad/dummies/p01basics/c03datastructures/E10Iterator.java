package ad.dummies.p01basics.c03datastructures;

import java.util.Iterator;
import java.util.function.BiFunction;

/**
 * <p>Example from the german book "Algorithms and data structures for
 * dummies":</p>
 *
 * <p>A. Gogol-Döring and T. Letschert, <i>Algorithmen und Datenstrukturen für
 * Dummies</i>. Weinheim, Germany: Wiley-VCH, 2019.</p>
 *
 * <p>The current version of these examples with unit tests and benchmarks can
 * be found <a href="https://github.com/CSchoel/ad-dummies-java">on GitHub</a>.
 * </p>
 *
 * @author Christopher Schölzel
 */
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

    public static void main(String[] args) {
        int[] data = {-4, 0, 1, -5, 7, 8};
        IntList lst = new Nil();
        for(int x: data) { lst = new Cons(x, lst); }
        System.out.printf("listSumInner([%s]) = %d\n",
                lst.traverseWith((x, acc) ->  x + (acc.length() == 0 ? "" : ", ") + acc, ""),
                listSumInner(lst)
        );
        StringBuilder s2 = new StringBuilder();
        Iterator<Integer> it = lst.iterator();
        while(it.hasNext()) {
            s2.append(it.next());
            if (it.hasNext()) { s2.append(", "); }
        }
        System.out.printf("listSumOuter([%s]) = %d\n", s2, listSumOuter(lst));
    }
}
