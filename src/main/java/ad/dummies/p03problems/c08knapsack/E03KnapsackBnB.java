package ad.dummies.p03problems.c08knapsack;

import java.util.*;

public class E03KnapsackBnB {
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
    public static class BnBInfo {
        public final double remainingValue;
        public final Solution bestKnown;
        public BnBInfo(List<KnapsackObject> all) {
            remainingValue = all.stream().mapToDouble(x -> x.value).sum();
            bestKnown = new Solution(Set.of(), Double.NEGATIVE_INFINITY);
        }
        public BnBInfo(double remainingValue, Solution bestKnown) {
            this.remainingValue = remainingValue;
            this.bestKnown = bestKnown;
        }
        public BnBInfo removeObject(KnapsackObject o) {
            return new BnBInfo(remainingValue - o.value, bestKnown);
        }
        public BnBInfo updateSolution(Solution other) {
            if (other.totalValue > bestKnown.totalValue) {
                return new BnBInfo(remainingValue, other);
            }
            return this;
        }
    }

    public static Set<KnapsackObject> knapsackBnB(List<KnapsackObject> objects, double maxWeight) {
        return searchTreeBnB(objects, maxWeight, new HashSet<>(), 0, new BnBInfo(objects)).selected;
    }

    private static Solution searchTreeBnB(List<KnapsackObject> objects, double maxWeight, Set<KnapsackObject> selected, double selectedValue, BnBInfo info) {
        if (maxWeight < 0) { // invalid path => generate invalid solution
            return new Solution(new HashSet<>(), Double.NEGATIVE_INFINITY);
        }
        if (objects.size() == 0) { // all choices made => generate solution
            return new Solution(selected, selectedValue);
        }
        if (selectedValue + info.remainingValue <= info.bestKnown.totalValue) {
            System.out.printf("We have %s at total value of %s €.\n", selected, selectedValue);
            System.out.printf("Regardless what we take from remaining %s,\n", objects);
            System.out.printf("we cannot beat known solution %s at total value of %s €.\n\n", info.bestKnown.selected, info.bestKnown.totalValue);
            return info.bestKnown; // we cannot improve => report best known solution
        }
        KnapsackObject first = objects.get(0);
        List<KnapsackObject> withoutFirst = objects.subList(1, objects.size());
        Set<KnapsackObject> selectedWithFirst = new HashSet<>(selected);
        selectedWithFirst.add(first);
        Solution take = searchTreeBnB(withoutFirst, maxWeight - first.weight, selectedWithFirst, selectedValue + first.value, info.removeObject(first));
        info = info.updateSolution(take);
        Solution dontTake = searchTreeBnB(withoutFirst, maxWeight, selected, selectedValue, info.removeObject(first));
        return take.totalValue > dontTake.totalValue ? take : dontTake;
    }

    public static void main(String[] args) {
        List<KnapsackObject> objects = List.of(
                new KnapsackObject(3, 300),
                new KnapsackObject(12, 200),
                new KnapsackObject(10, 150),
                new KnapsackObject(7, 100)
        );
        double max = 20;
        Set<KnapsackObject> selected;
        selected = knapsackBnB(objects, max);
        System.out.printf("Solution   : %s\n", selected);
        System.out.printf("Total value: %.3f\n", selected.stream().mapToDouble(x -> x.value).sum());
    }
}
