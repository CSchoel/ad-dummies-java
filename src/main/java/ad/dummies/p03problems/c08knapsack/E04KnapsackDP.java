package ad.dummies.p03problems.c08knapsack;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class E04KnapsackDP {
    public static class KnapsackObject {
        public final int weight;
        public final double value;
        public KnapsackObject(int weight, double value) {
            this.weight = weight;
            this.value = value;
        }
        public String toString() { return String.format("%s €|%s kg", value, weight);}
    }
    public static boolean takeObject(int o, int w, List<KnapsackObject> objects, double[][] table) {
        if (objects.get(o).weight > w) {
            return false;
        }
        double take = table[w - objects.get(o).weight][o] + objects.get(o).value;
        double skip = table[w][o];
        return take > skip;
    }

    public static Set<KnapsackObject> knapsackDP(List<KnapsackObject> objects, int maxWeight) {
        double[][] table = new double[maxWeight+1][objects.size()+1];
        for(int o = 0; o < objects.size(); o++) {
            for(int w = 1; w <= maxWeight; w++) {
                if (takeObject(o, w, objects, table)) {
                    table[w][o + 1] = table[w - objects.get(o).weight][o] + objects.get(o).value;
                } else {
                    table[w][o + 1] = table[w][o];
                }
            }
        }
        Set<KnapsackObject> res = new HashSet<>();
        int w = maxWeight;
        for(int o = objects.size() - 1; o >= 0; o--) {
            if (takeObject(o, w, objects, table)) {
                res.add(objects.get(o));
                w -= objects.get(o).weight;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        List<KnapsackObject> objects = List.of(
                new KnapsackObject(3, 300),
                new KnapsackObject(12, 200),
                new KnapsackObject(10, 150),
                new KnapsackObject(7, 100)
        );
        int max = 20;
        Set<KnapsackObject> selected;
        selected = knapsackDP(objects, max);
        System.out.printf("Solution   : %s\n", selected);
        System.out.printf("Total value: %.3f\n", selected.stream().mapToDouble(x -> x.value).sum());
    }
}
