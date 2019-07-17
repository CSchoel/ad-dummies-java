package ad.dummies.p01basics.c01algorithms;

public class E03Riddle {
    public static int riddle(int n, int m) {
        int x = n;
        int y = m;
        int p = 0;
        while (x >= 1) {
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
}
