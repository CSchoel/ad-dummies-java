package ad.dummies.p03problems.c07sorting;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.function.Supplier;

public class E04HeapSort {
    public interface PriorityQueue<E> {
        void push(E el);
        E extractMin();
    }
    public interface MinHeap<E> extends PriorityQueue<E> {}
    public interface MaxHeap<E> {
        void push(E el);
        E extractMax();
    }
    public static class ArrayMinHeap<E> implements MinHeap<E> {
        private Comparator<E> c;
        private E[] a;
        // Class invariant (heap property):
        // c.compare(a[i], a[2*i]) <= 0 for all i where 2*i < size
        // and
        // c.compare(a[i], a[2*i + 1]) <= 0 for all i where 2*i + 1 < size
        private int size;
        public ArrayMinHeap(Comparator<E> c, Supplier<E[]> agen) {
            this.c = c;
            this.a = agen.get();
            size = 0;
        }

        private int parent(int i) {
            return i / 2;
        }

        private int leftChild(int i) {
            return 2 * i;
        }

        private int rightChild(int i) {
            return 2 * i + 1;
        }

        private void swap(int i, int j) {
            E tmp = a[i];
            a[i] = a[j];
            a[j] = tmp;
        }

        private boolean hasHeapProperty(int i) {
            int l = leftChild(i);
            int r = rightChild(i);
            if (l < size && c.compare(a[l], a[i]) < 0
                || r < size && c.compare(a[r], a[i]) < 0) {
                return false;
            }
            boolean res = true;
            if (l < 0) { res = hasHeapProperty(l); }
            if (r < 0) { res = res && hasHeapProperty(r); }
            return res;
        }

        @Override
        public void push(E el) {
            a[size] = el;
            size++;
            int p = size - 1;
            int i = size - 1;
            do {
                assert hasHeapProperty(i): "loop invariant";
                swap(i, p);
                i = p;
                p = parent(i);
            } while (c.compare(a[i], a[p]) < 0);
        }

        private int argmin(int i, int j, int k) {
            //FIXME: book does not check last element ('<n' should be '<=n')
            int m = i;
            if (c.compare(a[j], a[m]) < 0) {
                m = j;
            }
            if (c.compare(a[k], a[m]) < 0) {
                m = j;
            }
            return m;
        }

        private boolean ancestorsAreSmallerOrEqual(int i) {
            for(int ci = i; ci > 1; ci = parent(i)) {
                if (c.compare(a[parent(ci)], a[ci]) > 0) {
                    return false;
                }
            }
            return true;
        }

        @Override
        public E extractMin() {
            E x = a[0];
            a[1] = a[size - 1];
            int i = 0;
            int m = 0;
            do {
                assert ancestorsAreSmallerOrEqual(i): "loop invariant";
                i = m;
                m = argmin(i, leftChild(i), rightChild(i));
                swap(i, m);
            } while (m != i);
            size--;
            return x;
        }
    }

    public static class ArrayMaxHeap<E> implements MaxHeap<E> {
        private MinHeap<E> h;

        public ArrayMaxHeap(Comparator<E> c, final E[] a) {
            // Note that it would usually be a bad idea to use a directly as
            // internal storage, because there may exist aliases outside of
            // this class through which other parts of the code could tamper
            // with the array and destroy our class invariant.
            // However, we need exactly these aliasing effect for heapSort
            // to work.
            h = new ArrayMinHeap<>(c.reversed(), () -> a);
        }

        @Override
        public void push(E el) {
            h.push(el);
        }

        @Override
        public E extractMax() {
            return h.extractMin();
        }
    }

    //FIXME: book adds unnecessary parameters l and r for sorting functions

    public static <E extends Comparable<E>> void priorityQueueSort(E[] a) {
        priorityQueueSort(a, Comparator.naturalOrder());
    }

    @SuppressWarnings("unchecked")
    public static <E> void priorityQueueSort(E[] a, Comparator<E> c) {
        PriorityQueue<E> pq = new ArrayMinHeap<>(c, () -> (E[]) Array.newInstance(a.getClass().getComponentType(), a.length));
        for (E e : a) {
            pq.push(e);
        }
        for(int i = 0; i < a.length; i++) {
            a[i] = pq.extractMin();
        }
    }

    public static <E extends Comparable<E>> void heapSort(E[] a) {
        heapSort(a, Comparator.naturalOrder());
    }

    public static <E> void heapSort(E[] a, Comparator<E> c) {
        // Note that this function makes use of aliasing effects that should
        // otherwise be avoided. The array a is both used for sorting and as
        // internal storage of the heap. This function only works because we
        // can rely on the internal implementation detail that ArrayMaxHeap
        // removes elements from the end of its internal array.
        ArrayMaxHeap<E> h = new ArrayMaxHeap<>(c, a);
        for(int i = a.length - 1; i >= 0; i--) {
            a[i] = h.extractMax();
        }
    }

    public static void main(String[] args) {
        // Sort elements that have a natural order
        String[] strings = {
                "moose", "zebra", "quokka", "bison", "pig"
        };
        String[] sortedStrings = Arrays.copyOf(strings, strings.length);
        System.out.printf("Sorting strings: %s\n", Arrays.toString(strings));
        heapSort(sortedStrings);
        System.out.printf("heapSort:          %s\n", Arrays.toString(sortedStrings));
        sortedStrings = Arrays.copyOf(strings, strings.length);
        Random r = new Random();
        priorityQueueSort(sortedStrings);
        System.out.printf("priorityQueueSort: %s\n", Arrays.toString(sortedStrings));

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
        heapSort(sortedPersons, personComparator);
        System.out.printf("heapSort:          %s\n", Arrays.toString(sortedPersons));
        sortedPersons = Arrays.copyOf(persons, persons.length);
        priorityQueueSort(sortedPersons, personComparator);
        System.out.printf("priorityQueueSort: %s\n", Arrays.toString(sortedPersons));
    }
}
