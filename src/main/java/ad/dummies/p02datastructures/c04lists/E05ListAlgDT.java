package ad.dummies.p02datastructures.c04lists;

import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Function;
import java.util.function.Predicate;

public class E05ListAlgDT {
    private interface List<E> extends Iterable<E> {
        int length();
        List<E> append(List<E> other);
        List<E> quicksort(Comparator<E> cmp);
        List<E> filter(Predicate<? super E> pred);
        <F> List<F> map(Function<E, F> f);
        List<List<E>> perm();
    }
    private static class ListIterator<E> implements Iterator<E> {
        List<E> pos;

        public ListIterator(List<E> pos) {
            this.pos = pos;
        }

        @Override
        public boolean hasNext() {
            return !(pos instanceof Nil);
        }

        @Override
        public E next() {
            assert pos instanceof Cons;
            Cons<E> consPos = (Cons<E>) pos;
            E value = consPos.head;
            pos = consPos.tail;
            return value;
        }
    }
    private static class Nil<E> implements List<E> {
        @Override
        public int length() {
            return 0;
        }

        @Override
        public List<E> append(List<E> other) {
            return other;
        }

        @Override
        public List<E> quicksort(Comparator<E> cmp) {
            return this;
        }

        @Override
        public List<E> filter(Predicate<? super E> pred) {
            return this;
        }

        @Override
        public <F> List<F> map(Function<E, F> f) {
            return new Nil<>();
        }

        @Override
        public List<List<E>> perm() {
            return new Cons<>(this, new Nil<>());
        }

        @Override
        public Iterator<E> iterator() {
            return new ListIterator<>(this);
        }
    }
    private static class Cons<E> implements List<E> {
        private final E head;
        private final List<E> tail;
        public Cons(E value, List<E> next) {
            this.head = value;
            this.tail = next;
        }

        @Override
        public int length() {
            return 1 + tail.length();
        }

        @Override
        public List<E> append(List<E> other) {
            return new Cons<>(head, tail.append(other));
        }

        @Override
        public List<E> quicksort(Comparator<E> cmp) {
            E pivot = head;
            List<E> small = filter(x -> cmp.compare(x, pivot) < 0);
            List<E> equal = filter(x -> cmp.compare(x, pivot) == 0);
            List<E> large = filter(x -> cmp.compare(x, pivot) > 0);
            return small.quicksort(cmp).append(equal).append(large.quicksort(cmp));
        }

        @Override
        public List<E> filter(Predicate<? super E> pred) {
            if (pred.test(head)) {
                return new Cons<>(head, tail.filter(pred));
            } else {
                return tail.filter(pred);
            }
        }

        @Override
        public <F> List<F> map(Function<E, F> f) {
            return new Cons<>(f.apply(head), tail.map(f));
        }

        @Override
        public List<List<E>> perm() {
            return insertInEveryListAtEveryPos(head, tail.perm());
        }

        @Override
        public Iterator<E> iterator() {
            return new ListIterator<>(this);
        }
    }
    public static Integer sum(List<Integer> lst) {
        // cannot be implemented in the normal OO-style, because it only works
        // for Integer lists
        if (lst instanceof  Nil) {
            return 0;
        } else {
            Cons<Integer> consLst = (Cons<Integer>) lst;
            return consLst.head + sum(consLst.tail);
        }
    }
    public static <E> List<E> join(List<List<E>> lists) {
        if (lists instanceof Nil) {
            return new Nil<>();
        } else {
            Cons<List<E>> consLists = (Cons<List<E>>) lists;
            return consLists.head.append(join(consLists.tail));
        }
    }

    public static <E> List<List<E>> insertInEveryListAtEveryPos(E head, List<List<E>> perm) {
        return join(perm.map(l -> insertAtEveryPos(head, l)));
    }

    private static <E> List<List<E>> insertAtEveryPos(E head, List<E> lst) {
        if (lst instanceof Nil) {
            return new Cons<>(new Cons<>(head, new Nil<>()), new Nil<>());
        } else {
            Cons<E> consLst = (Cons<E>) lst;
            List<E> atFront = new Cons<>(head, consLst);
            List<List<E>> somewhereInTail = insertAtEveryPos(head, consLst.tail);
            // add head in front of every modified tail
            somewhereInTail = somewhereInTail.map(l -> new Cons<>(consLst.head, l));
            return new Cons<>(atFront, somewhereInTail);
        }
    }

    public static void main(String[] args) {
        Function<List<?>, String> toS = l -> {
            StringBuilder sb = new StringBuilder("[");
            for(String s: l.map(Object::toString)) {
                sb.append(s);
                sb.append(", ");
            }
            if (l.length() > 0) {
                sb.delete(sb.length()-2, sb.length());
            }
            sb.append("]");
            return sb.toString();
        };
        List<Integer> lst = new Nil<>();
        int[] data = {9, 1, 75, -5};
        for(int x: data) { lst = new Cons<>(x, lst); }
        String lstS = toS.apply(lst);
        System.out.printf("%s.length() = %d\n", lstS, lst.length());
        System.out.printf("%s.filter(x -> x %% 3 == 0) = %s\n", lstS, toS.apply(lst.filter(x -> x % 3 == 0)));
        System.out.printf("%s.map(x -> x * 2) = %s\n", lstS, toS.apply(lst.map(x -> x * 2)));
        System.out.printf("%s.quicksort() = %s\n", lstS, toS.apply(lst.quicksort(Integer::compareTo)));
        List<Integer> lst2 = new Nil<>();
        data = new int[]{2, 5, 8};
        for(int x: data) { lst2 = new Cons<>(x, lst2); }
        String lstS2 = toS.apply(lst2);
        System.out.printf("%s.perm() = %s\n", lstS2, toS.apply(lst2.perm().map(toS::apply)));
        System.out.printf("%s.append(%s) = %s\n", lstS, lstS2, toS.apply(lst.append(lst2)));
    }
}
