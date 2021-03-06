package ad.dummies.p01basics.c01algorithms;

import java.math.BigInteger;
import java.util.stream.IntStream;

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
public class E02GGT {
    // NOTE: to achieve true infinite loop, we need BigIntegers
    public static BigInteger ggt1(BigInteger n, BigInteger m) {
        BigInteger t = BigInteger.ONE;
        for(
                BigInteger x = BigInteger.ONE;
                x.compareTo(BigInteger.ONE) >= 0;
                x = x.add(BigInteger.ONE)
        ) {
            if (BigInteger.ZERO.equals(n.mod(x)) && BigInteger.ZERO.equals(m.mod(x))) {
                t = x;
            }
        }
        return t;
    }

    public static int ggt2a(int n, int m) {
        // FIXME: book assumes n > 0 and m > 0
        if (n == 0) { return m; }
        if (m == 0) { return n; }
        int t = 1;
        for(int x = 2; x <= Math.min(m, n); x++) {
            if (n % x == 0 && m % x == 0) {
                t = x;
            }
        }
        return t;
    }

    public static int ggt2b(int n, int m) {
        // FIXME: book assumes n > 0 and m > 0
        if (n == 0) { return m; }
        if (m == 0) { return n; }
        return IntStream.range(2, Math.min(n, m) + 1)
                .filter(x -> n % x == 0 && m % x == 0).max().orElse(1);
    }

    public static int ggt3(int n, int m) {
        if (n == 0) { return m; }
        if (m == 0) { return n; }
        int t = 1;
        for(int x = 2; x <= Math.min(n, m); x++) {
            if(isDivisor(x, m) && isDivisor(x, n)) {
                t = x;
            }
        }
        return t;
    }

    private static boolean isDivisor(int x, int z) {
        while (z > 0) {
            z -= x;
        }
        return z == 0;
    }

    public static void main(String[] args) {
        int n = 18;
        int m = 30;
        System.out.printf("ggt2a(%d, %d) = %d\n", n, m, ggt2a(n, m));
        System.out.printf("ggt2b(%d, %d) = %d\n", n, m, ggt2b(n, m));
        System.out.printf(" ggt3(%d, %d) = %d\n", n, m, ggt3(n, m));
    }
}
