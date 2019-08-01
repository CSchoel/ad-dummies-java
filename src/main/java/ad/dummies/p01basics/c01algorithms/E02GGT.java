package ad.dummies.p01basics.c01algorithms;

import java.util.stream.IntStream;

public class E02GGT {
    public static int ggt2(int n, int m) {
        if (n == 0) { return m; }
        if (m == 0) { return n; }
        return IntStream.range(2, Math.min(n, m) + 1)
                .filter(x -> n % x == 0 && m % x == 0).max().orElse(0);
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

    public static boolean isDivisor(int x, int z) {
        while (z > 0) {
            z -= x;
        }
        return z == 0;
    }
}
