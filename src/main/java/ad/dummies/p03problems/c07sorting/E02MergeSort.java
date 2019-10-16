package ad.dummies.p03problems.c07sorting;

import java.lang.reflect.Array;
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
public class E02MergeSort {
    public static  <E extends Comparable<E>> void mergeSort(E[] a) {
        mergeSort(a, Comparator.naturalOrder());
    }

    @SuppressWarnings("unchecked")
    public static <E> void mergeSort(E[] a, Comparator<E> c) {
        // create a helper array of the correct type using the reflection API
        E[] b = (E[]) Array.newInstance(a.getClass().getComponentType(), a.length);
        mergeSort(a, b, c, 0, a.length - 1);
    }
    public static <E> void mergeSort(E[] a, E[] b, Comparator<E> c, int l, int r) {
        if (l >= r) {
            // arrays of length 1 and 2 are always sorted
            return;
        }
        int m = (l + r + 1) / 2; // (l + r) / 2 rounded up
        mergeSort(a, b, c, l, m-1);
        mergeSort(a, b, c, m, r);
        merge(a, b, c, l, m, r);
    }

    public static <E> boolean isSorted(E[] a, Comparator<E> c, int l, int r) {
        for(int i = l; i < r; i++) {
            if (c.compare(a[i], a[i+1]) > 0) {
                return false;
            }
        }
        return true;
    }

    public static <E> void merge(E[] a, E[] b, Comparator<E> c, int l, int m, int r) {
        // NOTE: If you want to plagiarize this, you should read this article
        // https://arbitrary-but-fixed.net/teaching/java/2018/12/09/mergesort.de.html
        assert isSorted(a, c, l, m-1);
        assert isSorted(a, c, m, r);
        int il = l;
        int ir = m;
        int j = l;
        while (il < m || ir <= r) {
            assert isSorted(b, c, l, j - 1): "loop invariant";
            if (ir > r || il < m && c.compare(a[il], a[ir]) <= 0) { // a[il] <= a[ir]
                // take from left
                b[j] = a[il];
                il++;
            } else {
                // take from right
                b[j] = a[ir];
                ir++;
            }
            j++;
        }
        System.arraycopy(b, l, a, l, r - l + 1);
    }

    public static void main(String[] args) {
        // Sort elements that have a natural order
        String[] strings = {
                "moose", "zebra", "quokka", "bison", "pig"
        };
        System.out.printf("Sorting strings: %s\n", Arrays.toString(strings));
        mergeSort(strings);
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
        mergeSort(
                persons,
                Comparator.<Person, String>comparing(x -> x.lastName).
                        thenComparing(x -> x.firstName)
        );
        System.out.printf("Sorted: %s\n", Arrays.toString(persons));
    }
}
