package ad.dummies.p03problems.c08knapsack;

import java.util.*;

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

    public static List<KnapsackObject> knapsackDP(List<KnapsackObject> objects, int maxWeight) {
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
        List<KnapsackObject> res = new ArrayList<>();
        int w = maxWeight;
        for(int o = objects.size() - 1; o >= 0; o--) {
            if (takeObject(o, w, objects, table)) {
                res.add(objects.get(o));
                w -= objects.get(o).weight;
            }
        }
        return res;
    }

    public static class Combination {
        public final int weight;
        public final double value;
        public final List<KnapsackObject> objects;
        public Combination(List<KnapsackObject> objects, int i) {
            this.objects = new ArrayList<>();
            int rest = i;
            int weight = 0;
            double value = 0;
            for(KnapsackObject o: objects) {
                if (rest % 2 == 1) {
                    this.objects.add(o);
                    value += o.value;
                    weight += o.weight;
                }
                rest /= 2;
            }
            this.value = value;
            this.weight = weight;
        }

        public Combination(int weight, double value, List<KnapsackObject> objects) {
            this.weight = weight;
            this.value = value;
            this.objects = objects;
        }

        public Combination add(KnapsackObject o) {
            List<KnapsackObject> objects = new ArrayList<>(this.objects);
            objects.add(o);
            return new Combination(weight + o.weight, value + o.value, objects);
        }
    }

    public static List<Combination> getDominants(List<Combination>  combinations) {
        combinations.sort(Comparator.comparing(x -> -x.value));
        combinations.sort(Comparator.comparing(x -> x.weight));
        Combination combi = combinations.get(0);
        List<Combination> d = new ArrayList<>();
        d.add(combi);
        for(int i = 1; i < combinations.size(); i++) {
            if (combinations.get(i).value > combi.value) {
                combi = combinations.get(i);
                d.add(combi);
            }
        }
        return d;
    }

    public static List<KnapsackObject> knapsackDomi(List<KnapsackObject> objects, int maxWeight) {
        List<Combination> d = new ArrayList<>();
        d.add(new Combination(0, 0, new ArrayList<>()));
        for(KnapsackObject o: objects) {
            List<Combination> combinations = new ArrayList<>();
            for(Combination combi: d) {
                combinations.add(combi);
                if (combi.weight + o.weight <= maxWeight) {
                    combinations.add(combi.add(o));
                }
            }
            d = getDominants(combinations);
        }
        return d.get(d.size() - 1).objects;
    }

    public static void main(String[] args) {
        List<KnapsackObject> objects = List.of(
                new KnapsackObject(3, 300),
                new KnapsackObject(12, 200),
                new KnapsackObject(10, 150),
                new KnapsackObject(7, 100)
        );
        int max = 20;
        List<KnapsackObject> selected;
        selected = knapsackDP(objects, max);
        System.out.println("knapsackDP");
        System.out.printf("  Solution   : %s\n", selected);
        System.out.printf("  Total value: %.3f\n", selected.stream().mapToDouble(x -> x.value).sum());
        System.out.println("knapsackDomi");
        selected = knapsackDomi(objects, max);
        System.out.printf("  Solution   : %s\n", selected);
        System.out.printf("  Total value: %.3f\n", selected.stream().mapToDouble(x -> x.value).sum());
    }
}
