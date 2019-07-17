package ad.dummies.p01basics.c02quality;

public class E01Weight {
    public static int minWeight1(double[] people) {
        for (int i = 0; i < people.length; i++) {
            if (isLightest(i, people)) {
                return i;
            }
        }
        return Integer.MAX_VALUE; // if array is empty
    }
    private static boolean isLightest(int i, double[] people) {
        for (int j = 0; j < people.length; j++) {
            if (people[i] > people[j]) {
                return false;
            }
        }
        return true;
    }

    public static int minWeight2(int[] people) {
        int m = 1;
        for (int i = 1; i < people.length; i++) {
            if (people[i] < people[m]) {
                m = i;
            }
        }
        return m;
    }
}
