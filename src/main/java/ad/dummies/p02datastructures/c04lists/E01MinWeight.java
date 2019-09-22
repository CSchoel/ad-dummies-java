package ad.dummies.p02datastructures.c04lists;

public class E01MinWeight {
    public static double minWeight2Iter(Iterable<Double> lst) {
        double m = lst.iterator().next(); // first element
        for (double man: lst) {
            if (man < m) {
                m = man;
            }
        }
        return m;
    }

    public static int minWeight2Array(double[] a) {
        int m = 0;
        for(int i = 1; i < a.length; i++) {
            if (a[i] < a[m]) {
                m = i;
            }
        }
        return m;
    }
}
