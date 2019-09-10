package ad.dummies.p01basics.c03datastructures;

public class E06GGT {
    public static int GGTRec(int m, int n) {
        // FIXME: book assumes n > 0 and m > 0
        if (m == 0) { return n; }
        if (n == 0) { return m; }
        if (m == n) { return n; }
        return GGTRec(Math.max(m, n) - Math.min(m, n), Math.min(m, n));
    }

    public static int GGTIter(int x, int y) {
        // FIXME: book assumes n > 0 and m > 0
        if (x == 0) { return y; }
        if (y == 0) { return x; }
        while (x != y) {
            if (x > y) {
                x = x - y;
            } else {
                y = y - x;
            }
        }
        return x;
    }

    public static class Tuple {
        private int x;
        private int y;
        public Tuple(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public int x() { return x; }
        public int y() { return y; }
        public int max() { return x > y ? x : y; }
        public int min() { return x < y ? x : y; }
    }

    public static int GGTIterTuple(int x, int y) {
        // FIXME: book assumes n > 0 and m > 0
        if (x == 0) { return y; }
        if (y == 0) { return x; }
        Tuple args = new Tuple(x, y);
        while (args.x() != args.y()) {
            args = new Tuple(args.max() - args.min(), args.min());
        }
        return args.x();
    }
}
