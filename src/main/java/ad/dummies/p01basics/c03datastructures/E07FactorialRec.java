package ad.dummies.p01basics.c03datastructures;

import java.text.MessageFormat;

public class E07FactorialRec {
    public static long fRec(int n) {
        if (n == 0) { return 1; }
        return fRec(n - 1) * n;
    }
    public static long fTailRec(int n) {
        return fTailRec(n, 1);
    }
    private static long fTailRec(int n, long acc) {
        if (n == 0) { return acc; }
        return fTailRec(n - 1, acc * n);
    }
    public static long fIter(int n) {
        long f = 1;
        while (n != 0) {
            f = n * f;
            n = n - 1;
        }
        return f;
    }

    public interface NList {}
    public static class Nil implements NList {}
    public static class Cons implements NList {
        private final long value;
        private final NList next;
        public Cons(int value, NList next) {
            this.value = value;
            this.next = next;
        }
        public long value() { return value; }
        public NList next() { return next; }
    }
    public static long fTailRec2(int n) {
        return fMultiply(fCollect(n, new Nil()), 1);
    }
    private static NList fCollect(int n, NList acc) {
        if (n == 0) {
            return acc;
        }
        return fCollect(n - 1, new Cons(n, acc));
    }
    private static long fMultiply(NList rev, long acc) {
        if (rev instanceof Nil) {
            return acc;
        } else {
            Cons revc = (Cons) rev;
            return fMultiply(revc.next, acc * revc.value());
        }
    }

    public static long fIter2(int n) {
        NList accList = new Nil();
        for (int currentN = n; currentN != 0; currentN--) {
            accList = new Cons(currentN, accList);
        }
        long accFact = 1;
        NList rev = accList;
        while (!(rev instanceof Nil)) {
            Cons revc = (Cons) rev;
            accFact *= revc.value();
            rev = revc.next();
        }
        return accFact;
    }

    public static void main(String[] args) {
        int n = 4;
        System.out.printf("     fRec(%d) = %d\n", n, fRec(n));
        System.out.printf(" fTailRec(%d) = %d\n", n, fTailRec(n));
        System.out.printf("    fIter(%d) = %d\n", n, fIter(n));
        System.out.printf("fTailRec2(%d) = %d\n", n, fTailRec2(n));
        System.out.printf("   fIter2(%d) = %d\n", n, fIter2(n));
    }
}
