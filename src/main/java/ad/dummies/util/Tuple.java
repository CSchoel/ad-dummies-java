package ad.dummies.util;

public class Tuple<A, B> {
    private A v1;
    private B v2;
    public Tuple(A a, B b) {
        v1 = a;
        v2 = b;
    }
    public A v1() {
        return v1;
    }
    public B v2() {
        return v2;
    }
}
