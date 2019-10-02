package ad.dummies.p01basics.c03datastructures;

import java.util.function.Function;

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
public class E03FactorialList {
    public static class FactList {
        public int v;
        public FactList n;
        public FactList(int v, FactList n) {
            this.v = v;
            this.n = n;
        }
    }

    public static FactList fList(int n) {
        FactList l = null;
        int f = 1;
        for(int i = 1; i <= n; i++) {
            // FIXME: book manipulates f after l => last value is missing
            f = f * i;
            l = new FactList(f, l);
        }
        return l;
    }

    public static FactList fListAE(int n) {
        FactList a = null;
        FactList e = null;
        // FIXME: if-expression in loop in book is unnecessarily complicated
        a = new FactList(1, null);
        e = a;
        for(int i = 1; i <= n; i++) {
            FactList t = new FactList(e.v * i, null);
            e.n = t;
            e = t;
        }
        return a;
    }

    public static void main(String[] args) {
        Function<FactList, String> toS = lst -> {
            StringBuilder sb = new StringBuilder("[");
            while(lst != null) {
                sb.append(lst.v);
                lst = lst.n;
                if (lst != null) { sb.append(", "); }
            }
            sb.append("]");
            return sb.toString();
        };
        int n = 4;
        System.out.printf("  fList(%d) = %s\n", n, toS.apply(fList(n)));
        System.out.printf("fListAE(%d) = %s\n", n, toS.apply(fListAE(n)));
    }
}
