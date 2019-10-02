package ad.dummies.p01basics.c01algorithms;

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
public class E03Riddle {
    public static int riddle(int n, int m) {
        int x = n;
        int y = m;
        int p = 0;
        while (x >= 1) {
            assert n * m == x * y + p; // loop invariant
            if (x % 2 == 0) {
                x = x / 2;
            } else {
                p += y;
                x = (x - 1) / 2;
            }
            y *= 2;
        }
        return p;
    }

    public static void main(String[] args) {
        int n = 9;
        int m = 7;
        System.out.printf("riddle(%d,%d) = %d\n", n ,m , riddle(n, m));
    }
}
