package ad.dummies.p01basics.c03datastructures;

public class E01FactorialArray {

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
        IntTuple[] a = new IntTuple[n];
        a[0] = new IntTuple(0, 1);
        for (int i = 0; i < n; i++) {
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
        FactRecord[] a = new FactRecord[n];
        a[0] = new FactRecord(0, 1);
        for (int i = 0; i < n; i++) {
            a[i] = new FactRecord(i, a[i-1].f * i);
        }
        return a;
    }
}
