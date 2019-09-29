package ad.dummies.p01basics.c01algorithms;

public class E04Collatz {
    public static void collatz(int n) {
        while (n > 1) {
            System.out.println(n);
            if (n % 2 == 0) {
                n /= 2;
            } else {
                n = 3 * n + 1;
            }
        }
        System.out.println(n); // always 1
    }

    public static void main(String[] args) {
        collatz(25);
    }
}
