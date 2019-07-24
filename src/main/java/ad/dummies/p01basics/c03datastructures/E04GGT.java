package ad.dummies.p01basics.c03datastructures;

public class E04GGT {
    public static int GGTRec(int m, int n) {
        if (m == n) {
            return n;
        }
        return GGTRec(Math.max(m, n) - Math.min(m, n), Math.min(m, n));
    }

    public static int GGTIter(int x, int y) {
        while (x != y) {
            if (x > y) {
                x = x - y;
            } else {
                y = x - y;
            }
        }
        return x;
    }

    // tuple variant is not possible without creating own tuple type
}
