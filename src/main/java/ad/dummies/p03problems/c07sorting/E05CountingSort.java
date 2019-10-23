package ad.dummies.p03problems.c07sorting;

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
public class E05CountingSort {
    public static void countingSort(int[] a) {
        if (a.length == 0) {
            return;
        }
        int max = Arrays.stream(a).max().getAsInt();
        int[] count = new int[max+1];
        for (int value : a) {
            // loop invariant: count[x] counts how often value x has been
            // previously encountered in a
            count[value]++;
        }
        int i = 0;
        for(int c = 0; c < count.length; c++) {
            // loop invariant: a[0..i-1] consists of all elements of the
            // original input array that are smaller than c and a[0..i-1] is
            // sorted
            for(int ci = 0; ci < count[c]; ci++) {
                a[i] = c;
                i++;
            }
        }
    }

    public static void main(String[] args) {
        int[] data = {2, 5, 8, 1, 0, 2};
        int[] sorted = Arrays.copyOf(data, data.length);
        countingSort(sorted);
        System.out.printf("Data:   %s\n", Arrays.toString(data));
        System.out.printf("Sorted: %s\n", Arrays.toString(sorted));
    }
}
