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
public class E07BubbleSort {
    public static <E extends Comparable<E>> void bubbleSort(E[] a) {
        bubbleSort(a, Comparator.naturalOrder());
    }

    public static <E> void bubbleSort(E[] a, Comparator<E> cmp) {
        boolean sorted;
        do {
            // loop invariant: a[0..k] is sorted where k is an iteration
            // counter for the outer loop
            sorted = true;
            for(int i = 0; i < a.length - 1; i++) {
                if (cmp.compare(a[i], a[i+1]) > 0) {
                    swap(a, i, i+1);
                    sorted = false;
                }
            }
        } while (!sorted);
    }

    public static <E> void swap(E[] a, int i, int j) {
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
        bubbleSort(sortedStrings);
        System.out.printf("bubbleSort: %s\n", Arrays.toString(sortedStrings));

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
        bubbleSort(sortedPersons, personComparator);
        System.out.printf("bubbleSort: %s\n", Arrays.toString(sortedPersons));
    }
}
