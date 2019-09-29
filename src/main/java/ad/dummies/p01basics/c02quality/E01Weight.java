package ad.dummies.p01basics.c02quality;

import java.util.Arrays;

public class E01Weight {
    public static int minWeight1(double[] people) {
        for (int i = 0; i < people.length; i++) {
            if (isLightest(i, people)) {
                return i;
            }
        }
        return -1; // if array is empty
    }
    private static boolean isLightest(int i, double[] people) {
        for (int j = 0; j < people.length; j++) {
            if (people[i] > people[j]) {
                return false;
            }
        }
        return true;
    }

    public static int minWeight2(double[] people) {
        int m = 0;
        for (int i = 1; i < people.length; i++) {
            if (people[i] < people[m]) {
                m = i;
            }
        }
        return m;
    }

    public static void main(String[] args) {
        double[] ar = {80, 75, 85, 90, 100};
        System.out.printf("minWeight1(%s) = %d\n", Arrays.toString(ar), minWeight1(ar));
        System.out.printf("minWeight2(%s) = %d\n", Arrays.toString(ar), minWeight2(ar));
    }
}
