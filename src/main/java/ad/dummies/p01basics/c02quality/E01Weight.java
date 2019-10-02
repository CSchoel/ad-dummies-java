package ad.dummies.p01basics.c02quality;

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
public class E01Weight {
    public static int minWeight1(double[] people) {
        for (int i = 0; i < people.length; i++) {
            if (isLightest(i, people)) {
                return i;
            }
        }
        return -1; // if array is empty
    }
    private static boolean isLightest(int i, double[] people) {
        for (int j = 0; j < people.length; j++) {
            if (people[i] > people[j]) {
                return false;
            }
        }
        return true;
    }

    public static int minWeight2(double[] people) {
        int m = 0;
        for (int i = 1; i < people.length; i++) {
            if (people[i] < people[m]) {
                m = i;
            }
        }
        return m;
    }

    public static void main(String[] args) {
        double[] ar = {80, 75, 85, 90, 100};
        System.out.printf("minWeight1(%s) = %d\n", Arrays.toString(ar), minWeight1(ar));
        System.out.printf("minWeight2(%s) = %d\n", Arrays.toString(ar), minWeight2(ar));
    }
}
