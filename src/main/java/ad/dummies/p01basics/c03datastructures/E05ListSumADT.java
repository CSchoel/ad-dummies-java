package ad.dummies.p01basics.c03datastructures;

public class E05ListSumADT {
    public interface IntList {
        int listSum3();
    }
    public static class Nil implements IntList {
        public int listSum3() { return 0; }
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

        public int listSum3() {
            return value + next.listSum3();
        }
    }

    // clunky "pattern matching" versions of list sums

    public static int listSum1(IntList lst) {
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

    public static int listSum3(IntList lst) {
        if (lst instanceof Nil) {
            return 0;
        } else {
            Cons lstc = (Cons) lst;
            return lstc.value() + listSum3(lstc.next());
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
}
