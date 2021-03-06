package ad.dummies.p01basics.c03datastructures;

import java.util.Arrays;
import java.util.function.Function;
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
public class E02FactorialArray {

    // TODO case variants are not possible, because java has no pattern matching
    // TODO it may become possible with http://openjdk.java.net/jeps/305, but that is not likely without dedicated data classes

    public static int[] fArray1(int n) {
        int[] a = new int[n+1];
        a[0] = 1;
        for (int i = 1; i <= n; i++) {
            a[i] = a[i-1] * i;
        }
        return a;
    }

    public static class IntTuple {
        private final int v1;
        private final int v2;
        public IntTuple(int a, int b) {
            v1 = a;
            v2 = b;
        }
        public int v1() {
            return v1;
        }
        public int v2() {
            return v2;
        }
    }

    public static IntTuple[] fArray2(int n) {
        IntTuple[] a = new IntTuple[n+1];
        a[0] = new IntTuple(0, 1);
        for (int i = 1; i <= n; i++) {
            a[i] = new IntTuple(i, a[i-1].v2() * i); // FIXME: book uses t[] instead of a[]
        }
        return a;
    }

    public static class FactRecord {
        public int v;
        public int f;
        public FactRecord(int v, int f) {
            this.v = v;
            this.f = f;
        }
    }

    public static FactRecord[] fArray3(int n) {
        FactRecord[] a = new FactRecord[n+1];
        a[0] = new FactRecord(0, 1);
        for (int i = 1; i <= n; i++) {
            a[i] = new FactRecord(i, a[i-1].f * i);
        }
        return a;
    }

    public static void main(String[] args) {
        int n = 4;
        System.out.printf("fArray1(%d) = %s\n", n, Arrays.toString(fArray1(n)));
        System.out.printf("fArray2(%d) = [%s]\n", n, Arrays.stream(fArray2(n)).map(t -> "("+t.v1+", "+t.v2+")").collect(Collectors.joining(", ")));
        System.out.printf("fArray3(%d) = [%s]\n", n, Arrays.stream(fArray3(n)).map(t -> "[v: "+t.v+", f: "+t.f+"]").collect(Collectors.joining(", ")));
    }
}
