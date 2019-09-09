package ad.dummies.p01basics.c03datastructures;

public class E03FactorialList {
    public static class FactList {
        public int v;
        public FactList n;
        public FactList(int v, FactList n) {
            this.v = v;
            this.n = n;
        }
    }

    public static FactList fList(int n) {
        FactList l = null;
        int f = 1;
        for(int i = 1; i <= n; i++) {
            l = new FactList(f, l);
            f = f * i;
        }
        return l;
    }

    public static FactList fListAE(int n) {
        FactList a = null;
        FactList e = null;
        // FIXME: if-expression in loop in book is unnecessarily complicated
        a = new FactList(1, null);
        e = a;
        for(int i = 1; i <= n; i++) {
            FactList t = new FactList(e.v * i, null);
            e.n = t;
            e = t;
        }
        return a;
    }
}
