package ad.dummies.p01basics.c03datastructures;

import java.util.Arrays;

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
public class E05ListSumAlgDT {
    public interface IntList {
        int listSumR();
    }
    public static class Nil implements IntList {
        public int listSumR() { return 0; }
    }
    public static class Cons implements IntList {
        private final int value;
        private final IntList next;
        public Cons(int value, IntList next) {
            this.value = value;
            this.next = next;
        }
        public int value() {
            return value;
        }

        public IntList next() {
            return next;
        }

        // more elegant object oriented version

        public int listSumR() {
            return value + next.listSumR();
        }
    }

    // clunky "pattern matching" versions of list sums

    public static int listSum(IntList lst) {
        int s = 0;
        while (!(lst instanceof Nil)) {
            Cons lstc = (Cons) lst;
            s += lstc.value();
            lst = lstc.next();
        }
        return s;
    }

    // would be possible if https://openjdk.java.net/jeps/8213076 was
    // implemented in java
    /*
    public static int listSum2(IntList lst) {
        int s = 0;
        while (!(lst instanceof Nil)) {
            switch(lst) {
                case Cons c -> {
                    s += c.value();
                    lst = c.next();
                }
            }
        }
        return s;
    }*/

    public static int listSumR(IntList lst) {
        if (lst instanceof Nil) {
            return 0;
        } else {
            Cons lstc = (Cons) lst;
            return lstc.value() + listSumR(lstc.next());
        }
    }

    // future version assuming https://openjdk.java.net/jeps/8213076 was implemented

    /*
    public static int listSum3(IntList lst) {
        return switch(lst) {
            case Nil n -> 0;
            case Cons c ->  c.value() + listSum3(c.next());
        };
    }
    */

    public static void main(String[] args) {
        int[] values = {4, -1, -8};
        IntList lst = new Nil();
        for(int v: values) { lst = new Cons(v, lst); }
        System.out.printf("  listSum(%s) = %d\n", Arrays.toString(values), listSum(lst));
        System.out.printf(" listSumR(%s) = %d\n", Arrays.toString(values), listSumR(lst));
        System.out.printf("%s.listSumR() = %d\n", Arrays.toString(values), lst.listSumR());
    }
}
