package ad.dummies.p01basics.c01algorithms;

public class E01Summation {
    public static int summation1(int n) {
        int s = 0;
        for(int i = 0; i <= n; i++) {
            s += i;
        }
        return s;
    }
    public static int summation2(int n) {
        int s = n * (n + 1) / 2;
        return s;
    }
}
