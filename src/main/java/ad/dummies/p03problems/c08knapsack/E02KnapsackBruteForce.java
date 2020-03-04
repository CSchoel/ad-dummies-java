package ad.dummies.p03problems.c08knapsack;

import java.util.*;
import java.util.function.Function;

public class E02KnapsackBruteForce {
    public static class KnapsackObject {
        public final double weight;
        public final double value;
        public KnapsackObject(double weight, double value) {
            this.weight = weight;
            this.value = value;
        }
        public String toString() { return String.format("%s €|%s kg", value, weight);}
    }
    public static class Solution {
        public final Set<KnapsackObject> selected;
        public final double totalValue;
        public Solution(Set<KnapsackObject> selected, double totalValue) {
            this.selected = selected;
            this.totalValue = totalValue;
        }
    }
    public static class SubsetIterator<E> implements Iterator<Set<E>> {
        public Set<E> current = new HashSet<>();
        public List<E> all;

        public SubsetIterator(List<E> all) {
            this.all = all;
        }

        @Override
        public boolean hasNext() {
            return !current.containsAll(all);
        }
        @Override
        public Set<E> next() {
            // build subsets like binary number => make all ones to zeros until you find a zero that must become one
            int i;
            for(i = 0; current.contains(all.get(i)); i++) {
                current.remove(all.get(i));
            }
            current.add(all.get(i));
            return new HashSet<>(current);
        }
    }
    public static Set<KnapsackObject> knapsackBruteForce(List<KnapsackObject> objects, double maxWeight) {
        Set<KnapsackObject> best = null;
        double bestValue = 0;
        Iterator<Set<KnapsackObject>> it = new SubsetIterator<>(objects);
        while(it.hasNext()) {
            Set<KnapsackObject> candidate = it.next();
            double candidateWeight = candidate.stream().mapToDouble(x -> x.weight).sum();
            double candidateValue = candidate.stream().mapToDouble(x -> x.value).sum();
            if (candidateWeight <= maxWeight && candidateValue > bestValue) {
                best = candidate;
                bestValue = candidateValue;
            }
        }
        return best;
    }

    public static Set<KnapsackObject> knapsackRek(List<KnapsackObject> objects, double maxWeight) {
        return searchTreeRek(objects, maxWeight, new HashSet<>(), 0).selected;
    }

    private static Solution searchTreeRek(List<KnapsackObject> objects, double maxWeight, Set<KnapsackObject> selected, double selectedValue) {
        if (maxWeight < 0) { // invalid path => generate invalid solution
            return new Solution(new HashSet<>(), Double.NEGATIVE_INFINITY);
        }
        if (objects.size() == 0) { // all choices made => generate solution
            return new Solution(selected, selectedValue);
        }
        KnapsackObject first = objects.get(0);
        List<KnapsackObject> withoutFirst = objects.subList(1, objects.size());
        Set<KnapsackObject> selectedWithFirst = new HashSet<>(selected);
        selectedWithFirst.add(first);
        Solution dontTake = searchTreeRek(withoutFirst, maxWeight, selected, selectedValue);
        Solution take = searchTreeRek(withoutFirst, maxWeight - first.weight, selectedWithFirst, selectedValue + first.value);
        return take.totalValue > dontTake.totalValue ? take : dontTake;
    }

    public static void main(String[] args) {
        List<KnapsackObject> objects;
        double max;
        Set<KnapsackObject> selected;
        objects = new ArrayList<>();
        objects.add(new KnapsackObject(20, 10));
        for(int i = 0; i < 20; i++) {
            objects.add(new KnapsackObject(1, 1));
        }
        max = 20;
        System.out.printf("Selecting %.3f kg from %s:\n", max, objects);
        System.out.println("  knapsackBruteForce");
        selected = knapsackBruteForce(objects, max);
        System.out.printf("    Solution   : %s\n", selected);
        System.out.printf("    Total value: %.3f\n", selected.stream().mapToDouble(x -> x.value).sum());
        System.out.println("  knapsackRek");
        selected = knapsackRek(objects, max);
        System.out.printf("    Solution   : %s\n", selected);
        System.out.printf("    Total value: %.3f\n", selected.stream().mapToDouble(x -> x.value).sum());
        objects = List.of(
                new KnapsackObject(0.1, 10),
                new KnapsackObject(20, 500)
        );
        max = 20;
        System.out.printf("Selecting %.3f kg from %s:\n", max, objects);
        System.out.println("  knapsackBruteForce");
        selected = knapsackBruteForce(objects, max);
        System.out.printf("    Solution   : %s\n", selected);
        System.out.printf("    Total value: %.3f\n", selected.stream().mapToDouble(x -> x.value).sum());
        System.out.println("  knapsackRek");
        selected = knapsackRek(objects, max);
        System.out.printf("    Solution   : %s\n", selected);
        System.out.printf("    Total value: %.3f\n", selected.stream().mapToDouble(x -> x.value).sum());
    }
}
