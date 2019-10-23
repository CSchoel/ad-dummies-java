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
public class E06RadixSort {
    public interface RadixSetup {
        int extractDigit(int number, int digit);
        int maxNumberOfDigits(int[] a);
        int radix();
    }
    public static class NaiveRadixSetup implements RadixSetup {
        private int radix;
        public NaiveRadixSetup(int radix) {
            this.radix = radix;
        }

        @Override
        public int extractDigit(int number, int digit) {
            int res = number;
            for(int i = 0; i < digit; i++) { res /= radix; }
            return res % radix;
        }

        @Override
        public int maxNumberOfDigits(int[] a) {
            int m = 0;
            for(int x: a) {
                int digits = 0;
                for(int rest = x; rest > 0; rest /= radix) { digits++; }
                if (digits > m) {
                    m = digits;
                }
            }
            return m;
        }

        public int radix() { return radix; }
    }

    public static class BinaryRadixSetup implements RadixSetup {
        private int exp;
        private int mask;
        public BinaryRadixSetup(int exp) {
            this.exp = exp;
            mask = 0xffffffff >>> (32 - exp);
        }

        @Override
        public int extractDigit(int number, int digit) {
            return (number >>> (digit * exp)) & mask;
        }

        @Override
        public int maxNumberOfDigits(int[] a) {
            return (32 + exp - 1) / exp;
        }

        @Override
        public int radix() {
            return 1 << exp;
        }
    }

    public static void sortByDigit(int[] a, RadixSetup s, int di) {
        int[] count = new int[s.radix()];
        for (int x : a) {
            // loop invariant: count[d] counts all previously encountered
            // values x for which the di-th digit equals d
            int d = s.extractDigit(x, di);
            count[d]++;
        }
        int i = 0;
        int[] pos = new int[s.radix()];
        for(int d = 0; d < pos.length; d++) {
            // loop invariant: pos[j] marks the first position where an element
            // with value d at the di-th digit would occur in the sorted array
            // for all j < d
            pos[d] = i;
            i += count[d];
        }
        int[] b = new int[a.length];
        for (int x : a) {
            // loop invariant: all previously encountered values x have been
            // placed at the location that they will have in the sorted array
            int d = s.extractDigit(x, di);
            b[pos[d]] = x;
            pos[d]++;
        }
        System.arraycopy(b, 0, a, 0, a.length);
    }

    public static void radixSort(int[] a, RadixSetup s) {
        int maxDigits = s.maxNumberOfDigits(a);
        for(int k = 0; k < maxDigits; k++) {
            // loop invariant: a is sorted by the k last digits
            sortByDigit(a, s, k);
        }
    }

    public static void main(String[] args) {
        int[] data = {27998, 55438, 84533, 19800, 278990, 55438};
        System.out.printf("Sorting array:   %s\n", Arrays.toString(data));
        int[] sorted = Arrays.copyOf(data, data.length);
        radixSort(sorted, new NaiveRadixSetup(100));
        System.out.printf("radixSort with r=200: %s\n", Arrays.toString(sorted));
        sorted = Arrays.copyOf(data, data.length);
        radixSort(sorted, new BinaryRadixSetup(8));
        System.out.printf("radixSort with r=256: %s\n", Arrays.toString(sorted));
    }
}
