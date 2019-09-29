package ad.dummies.p01basics.c03datastructures;

import java.util.Arrays;

public class E09Quicksort {
    public interface IntList {
        IntList append(IntList other);
        IntList quicksort();
        IntList lessEq(int x);
        IntList greater(int x);
    }
    public static class Nil implements IntList {
        public IntList append(IntList other) { return other; }
        public IntList quicksort() { return this; }
        public IntList lessEq(int x) { return this; }
        public IntList greater(int x) { return this; }
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
        public IntList append(IntList other) {
            return new Cons(value, next.append(other));
        }
        public IntList quicksort() {
            if (next instanceof Nil) {
                return this;
            }
            int pivot = value;
            IntList l1 = lessEq(pivot);
            IntList l2 = greater(pivot);
            return l1.quicksort().append(l2.quicksort());
        }
        public IntList lessEq(int x) {
            if (value <= x) {
                return new Cons(value, next.lessEq(x));
            }
            return next.lessEq(x);
        }
        public IntList greater(int x) {
            if (value > x) {
                return new Cons(value, next.greater(x));
            }
            return next.greater(x);
        }
    }

    public static void main(String[] args) {
        int[] ar = {5, 4, 3, 2, 1};
        IntList lst = new Nil();
        for(int x: ar) { lst = new Cons(x, lst); }
        IntList sorted = lst.quicksort();
        int[] sortedAr = new int[ar.length];
        for(int i = 0; i < sortedAr.length; i++) {
            Cons cons = (Cons) lst;
            sortedAr[i] = cons.value();
            lst = cons.next();
        }
        System.out.printf("%s.quicksort() = %s\n", Arrays.toString(ar), Arrays.toString(sortedAr));
    }
}
