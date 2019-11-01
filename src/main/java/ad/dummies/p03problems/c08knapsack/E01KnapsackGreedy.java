package ad.dummies.p03problems.c08knapsack;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

/**
 * <p>Example from the german book "Algorithms and data structures for
 * dummies":</p>
 *
 * <p>A. Gogol-Döring and T. Letschert, <i>Algorithmen und Datenstrukturen für
 * Dummies</i>. Weinheim, Germany: Wiley-VCH, 2019.</p>
 *
 * <p>The current version of these examples with unit tests and benchmarks can
 * be found <a href="https://github.com/CSchoel/ad-dummies-java">on GitHub</a>.
 * </p>
 *
 * @author Christopher Schölzel
 */
public class E01KnapsackGreedy {
    public static class KnapsackObject {
        public final double weight;
        public final double value;
        public KnapsackObject(double weight, double value) {
            this.weight = weight;
            this.value = value;
        }
        public String toString() { return String.format("%s €|%s kg", value, weight);}
    }
    public static List<KnapsackObject> knapsackGreedy(List<KnapsackObject> objects, double maxWeight) {
        return knapsackGreedy(objects, maxWeight, x-> x.value);
    }
    public static List<KnapsackObject> knapsackGreedy(List<KnapsackObject> objects, double maxWeight, Function<KnapsackObject, Double> attractiveness) {
        List<KnapsackObject> sorted = new ArrayList<>(objects);
        sorted.sort(Comparator.comparing(x -> -attractiveness.apply(x)));
        List<KnapsackObject> selected = new ArrayList<>();
        double w = 0;
        for(int i = 0; i < sorted.size() && w + sorted.get(i).weight <= maxWeight; i++) {
            selected.add(sorted.get(i));
            w += sorted.get(i).weight;
        }
        return selected;
    }

    public static void main(String[] args) {
        List<KnapsackObject> objects;
        double max;
        boolean useGoldExample = true;
        if (useGoldExample) {
            objects = new ArrayList<>();
            objects.add(new KnapsackObject(20, 10));
            for(int i = 0; i < 20; i++) {
                objects.add(new KnapsackObject(1, 1));
            }
            max = 20;
        } else {
            objects = List.of(
                    new KnapsackObject(0.1, 10),
                    new KnapsackObject(20, 500)
            );
            max = 20;
        }
        System.out.printf("Selecting %.3f kg from %s:\n", max, objects);
        List<KnapsackObject> selected1v = knapsackGreedy(objects, max, x -> x.value);
        System.out.printf("Attractivenes = value:        %s\n", selected1v);
        System.out.printf("    Total value: %.3f\n", selected1v.stream().mapToDouble(x -> x.value).sum());
        List<KnapsackObject> selected1a = knapsackGreedy(objects, max, x -> x.value/x.weight);
        System.out.printf("Attractivenes = weight/value: %s\n", selected1a);
        System.out.printf("    Total value: %.3f\n", selected1a.stream().mapToDouble(x -> x.value).sum());
    }
}
