package ad.dummies.p01basics.c03datastructures;

public class E02FactorialList {
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
}
