package ad.dummies.p03problems.c07sorting;

import java.util.Arrays;
import java.util.Comparator;

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
public class E01SelectionSort {
    public static <E extends Comparable<E>> void selectionSort(E[] ar) {
        selectionSort(ar, Comparator.naturalOrder());
    }
    public static <E> void selectionSort(E[] ar, Comparator<E> comp) {
        selectionSort(ar, comp, 0);
    }
    //FIXME: book introduces r as additional parameter that is never used
    private static <E> void selectionSort(E[] ar, Comparator<E> comp, int l) {
        for(int i = l; i < ar.length; i++) {
            assert isSorted(ar, comp, i): "loop invariant";
            int m = argmin(ar, comp, i);
            swap(ar, i, m);
        }
    }

    private static <E> boolean isSorted(E[] ar, Comparator<E> comp, int end) {
        for(int i = 1; i < end; i++) {
            if (comp.compare(ar[i-1], ar[i]) > 0) { // ar[i-1] > ar[i]
                return false;
            }
        }
        return true;
    }

    private static void swap(Object[] ar, int i, int j) {
        Object tmp = ar[i];
        ar[i] = ar[j];
        ar[j] = tmp;
    }

    private static <E> int argmin(E[] ar, Comparator<E> comp, int i) {
        int m = i;
        //FIXME: book has one-off error (last index is j=ar.length)
        for(int j = i+1; j < ar.length; j++) {
            if (comp.compare(ar[j], ar[m]) < 0) { // ar[j] < ar[m]
                m = j;
            }
        }
        return m;
    }

    public static void main(String[] args) {
        // Sort elements that have a natural order
        String[] strings = {
          "moose", "zebra", "quokka", "bison", "pig"
        };
        System.out.printf("Sorting strings: %s\n", Arrays.toString(strings));
        selectionSort(strings);
        System.out.printf("Sorted: %s\n", Arrays.toString(strings));
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
        selectionSort(
                persons,
                Comparator.<Person, String>comparing(x -> x.lastName).
                        thenComparing(x -> x.firstName)
        );
        System.out.printf("Sorted: %s\n", Arrays.toString(persons));
    }
}
