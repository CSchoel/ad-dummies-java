package ad.dummies.p01basics.c03datastructures;

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
public class E04FactorialListAlgDT {
    public interface FactorialList { }
    public static class Nil implements FactorialList { }
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
    }

    public static FactorialList fList(int n) {
        FactorialList l = new Nil();
        for (int i = 0; i <= n; i++) {
            l = new Cons(f(i), l);
        }
        return l;
    }

    public static int f(int x) {
        return x == 0 ? 1 : x * f(x - 1);
    }

    public static void main(String[] args) {
        int n = 4;
        FactorialList fl = fList(n);
        StringBuilder sb = new StringBuilder("[");
        while(fl instanceof Cons) {
            Cons flCons = (Cons) fl;
            sb.append(flCons.value);
            fl = flCons.next;
            if (fl instanceof Cons) { sb.append(", "); }
        }
        sb.append("]");
        System.out.printf("fList(%d) = %s", n, sb);
    }
}
