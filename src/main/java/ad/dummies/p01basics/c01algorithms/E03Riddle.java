package ad.dummies.p01basics.c01algorithms;

public class E03Riddle {
    public static int riddle(int n, int m) {
        int x = n;
        int y = m;
        int p = 0;
        while (x >= 1) {
            assert n * m == x * y + p; // loop invariant
            if (x % 2 == 0) {
                x = x / 2;
            } else {
                p += y;
                x = (x - 1) / 2;
            }
            y *= 2;
        }
        return p;
    }

    public static void main(String[] args) {
        int n = 9;
        int m = 7;
        System.out.printf("riddle(%d,%d) = %d\n", n ,m , riddle(n, m));
    }
}
