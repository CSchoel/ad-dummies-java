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
public class E08GnomeSort {
    public static <E extends Comparable<E>> void gnomeSort(E[] a) {
        gnomeSort(a, Comparator.naturalOrder());
    }
    public static <E> void gnomeSort(E[] a, Comparator<E> c) {
        int i = 0;
        while (i < a.length) {
            assert isSortedUntil(a, c, i);
            if (i == 0 || c.compare(a[i-1], a[i]) <= 0) {
                i++;
            } else {
                swap(a, i-1, i);
                i--;
            }
        }
    }

    public static <E> boolean isSortedUntil(E[] a, Comparator<E> c, int i) {
        for(int j = 1; j < i; j++) {
            if (c.compare(a[j-1], a[j]) > 0) {
                return false;
            }
        }
        return true;
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
        gnomeSort(sortedStrings);
        System.out.printf("gnomeSort: %s\n", Arrays.toString(sortedStrings));

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
        gnomeSort(sortedPersons, personComparator);
        System.out.printf("gnomeSort: %s\n", Arrays.toString(sortedPersons));
    }
}
