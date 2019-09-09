package ad.dummies.p01basics.c03datastructures;

public class E04FactorialListADT {
    public interface FactorialList<T> {
        int listSum3();
    }
    public static class Nil implements FactorialList {
        public int listSum3() { return 0; }
    }
    public static class Cons implements FactorialList {
        private final int value;
        private final FactorialList next;
        public Cons(int value, FactorialList next) {
            this.value = value;
            this.next = next;
        }
        public int value() {
            return value;
        }

        public FactorialList next() {
            return next;
        }

        // more elegant object oriented version

        public int listSum3() {
            return value + next.listSum3();
        }
    }

    // clunky "pattern matching" versions of list sums

    public static int listSum1(FactorialList lst) {
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
    public static int listSum2(FactorialList lst) {
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

    public static int listSum3(FactorialList lst) {
        if (lst instanceof Nil) {
            return 0;
        } else {
            Cons lstc = (Cons) lst;
            return lstc.value() + listSum3(lstc.next());
        }
    }

    // future version assuming https://openjdk.java.net/jeps/8213076 was implemented

    /*
    public static int listSum3(FactorialList<int> lst) {
        return switch(lst) {
            case Nil<int> n -> 0;
            case Cons<int> c ->  c.value() + listSum3(c.next());
        };
    }
    */
}
