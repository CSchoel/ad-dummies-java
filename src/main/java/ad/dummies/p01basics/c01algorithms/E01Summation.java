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
public class E01Summation {
    public static int summation1(int n) {
        int s = 0;
        for(int i = 1; i <= n; i++) {
            s += i;
        }
        return s;
    }
    public static int summation2(int n) {
        int s = n * (n + 1) / 2;
        return s;
    }

    public static void main(String[] args) {
        int n = 100;
        System.out.printf("summation1(%d) = %d\n", n, summation1(n));
        System.out.printf("summation2(%d) = %d\n", n, summation2(n));
    }
}
