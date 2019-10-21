package ad.dummies.p03problems.c07sorting;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
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
public class E03QuickSort {
    public interface PivotChoice<E> {
        int choosePivot(E[] a, int l, int r);
    }
    public static <E> void quickSort(E[] a, Comparator<E> c) {
        quickSort(a, c, (ar,l,r) -> r, 0, a.length - 1);
    }
    public static <E> void quickSort(E[] a, Comparator<E> c, PivotChoice<E> pc) {
        quickSort(a, c, pc, 0, a.length - 1);
    }
    public static <E extends Comparable<E>> void quickSort(E[] a) {
        quickSort(a, Comparator.naturalOrder(), (ar,l,r) -> r, 0, a.length - 1);
    }
    public static <E extends Comparable<E>> void quickSort(E[] a, PivotChoice<E> pc) {
        quickSort(a, Comparator.naturalOrder(), pc, 0, a.length - 1);
    }
    private static <E> void quickSort(E[] a, Comparator<E> c, PivotChoice<E> pc, int l, int r) {
        if (l >= r) { return; }
        int m = partition(a, c, pc, l, r);
        quickSort(a, c, pc, l, m-1);
        quickSort(a, c, pc, m + 1, r);
    }

    private static <E> int partition(E[] a, Comparator<E> c, PivotChoice<E> pc, int l, int r) {
        int ip = pc.choosePivot(a, l, r);
        swap(a, ip, r); // move pivot to right position
        E pivot = a[r];
        int m = l;
        for(int i = l; i < r; i++) { // move everything but the pivot element
            assert allLeq(a, c, pivot, 0, m-1): "loop invariant: elements left of m are <= pivot";
            assert allGeq(a, c, pivot, m, i - 1): "loop invariant: elements between m and i are >= pivot";
            if (c.compare(a[i], pivot) < 0) {
                swap(a, i, m);
                m++;
            }
        }
        swap(a, m , r);
        return m;
    }

    private static <E> boolean allGeq(E[] a, Comparator<E> c, E pivot, int l, int r) {
        for(int i = l; i <= r; i++) {
            if (c.compare(a[i], pivot) < 0) { return false; }
        }
        return true;
    }

    private static <E> boolean allLeq(E[] a, Comparator<E> c, E pivot, int l, int r) {
        for(int i = l; i <= r; i++) {
            if (c.compare(a[i], pivot) > 0) { return false; }
        }
        return true;
    }

    private static <E> void swap(E[] a, int i, int j) {
        E tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public static void main(String[] args) {
        // Sort elements that have a natural order
        String[] strings = {
                "moose", "zebra", "quokka", "bison", "pig"
        };
        String[] sortedStrings = Arrays.copyOf(strings, strings.length);
        System.out.printf("Sorting strings: %s\n", Arrays.toString(strings));
        quickSort(sortedStrings);
        System.out.printf("quickSort with pivot=r:      %s\n", Arrays.toString(sortedStrings));
        sortedStrings = Arrays.copyOf(strings, strings.length);
        Random r = new Random();
        quickSort(sortedStrings, (a, li, ri) -> r.nextInt(ri-li) + li);
        System.out.printf("quickSort with random pivot: %s\n", Arrays.toString(sortedStrings));

        // Sort elements that do not have a natural order
        class Person {
            String firstName;
            String lastName;
            public Person(String firstName, String lastName) {
                this.firstName = firstName;
                this.lastName = lastName;
            }
            public String toString() {return firstName + " " + lastName; }
        }
        Person[] persons = {
                new Person("Ada", "Lovelace"),
                new Person("Annie", "Easley"),
                new Person("Anita", "Borg"),
                new Person("Margaret", "Hamilton")
        };
        System.out.printf("Sorting persons: %s\n", Arrays.toString(persons));
        Comparator<Person> personComparator = Comparator
                .<Person, String>comparing(x -> x.lastName)
                .thenComparing(x -> x.firstName);
        Person[] sortedPersons = Arrays.copyOf(persons, persons.length);
        quickSort(sortedPersons, personComparator);
        System.out.printf("quickSort with pivot=r:      %s\n", Arrays.toString(sortedPersons));
        sortedPersons = Arrays.copyOf(persons, persons.length);
        quickSort(sortedPersons, personComparator, (a, li, ri) -> r.nextInt(ri-li) + li);
        System.out.printf("quickSort with random pivot: %s\n", Arrays.toString(sortedPersons));
    }
}
