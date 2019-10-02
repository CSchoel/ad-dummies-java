package ad.dummies.p01basics.c02quality;

import java.util.Arrays;
import java.util.Random;

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
public class E03QuickSelect {
    private static final Random random = new Random();
    public static double quickSelect(double[] data, int k) {
        double x = data[random.nextInt(data.length)];
        double[] data_lt = Arrays.stream(data).filter(y -> y < x).toArray();
        double[] data_gt = Arrays.stream(data).filter(y -> y > x).toArray();
        int count_leq = data_lt.length + 1;
        if (k == count_leq) {
            return x;
        } else if (k < count_leq) {
            return quickSelect(data_lt, k);
        } else {
            return quickSelect(data_gt, k - count_leq);
        }
    }

    public static void main(String[] args) {
        double[] ar = {1.7, -1.2, 1.8, 3.7, -2.8};
        int k = 3;
        System.out.printf("quickSelect(%s, %d) = %s\n", Arrays.toString(ar), 3, quickSelect(ar, k));
    }
}
