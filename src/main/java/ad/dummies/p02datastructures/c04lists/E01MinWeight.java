package ad.dummies.p02datastructures.c04lists;

import java.util.Arrays;
import java.util.stream.Collectors;

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
public class E01MinWeight {
    public static double minWeight2Iter(Iterable<Double> lst) {
        double m = lst.iterator().next(); // first element
        for (double man: lst) {
            if (man < m) {
                m = man;
            }
        }
        return m;
    }

    public static int minWeight2Array(double[] a) {
        int m = 0;
        for(int i = 1; i < a.length; i++) {
            if (a[i] < a[m]) {
                m = i;
            }
        }
        return m;
    }

    public static void main(String[] args) {
        double[] data = {80, 75, 85, 90, 100};
        System.out.printf(" minWeight2Iter(%s) = %s\n", Arrays.toString(data), minWeight2Iter(Arrays.stream(data).boxed().collect(Collectors.toList())));
        System.out.printf("minWeight2Array(%s) = %d\n", Arrays.toString(data), minWeight2Array(data));
    }
}
