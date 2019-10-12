package ad.dummies.p02datastructures.c04lists;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Iterator;

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
public class E06InsertionSort {
    public static void insertionSortI(int[] a) {
        for (int j = 1; j < a.length; j++) {
            int key = a[j];
            int i;
            for(i = j - 1; i > -1 && a[i] > key; i--) {
                a[i + 1] = a[i];
            }
            a[i + 1] = key;
        }
    }
    public static int[] insertionSortF(int[] a) {
        insertionSortI(a);
        return a;
    }

    public static boolean isSorted(int[] a) {
        for(int i = 0; i < a.length - 1; i++) {
            if (a[i] > a[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public static int[] sortP(int[] a) {
        for(Iterator<int[]> permIter = new PermutationIterator(a); permIter.hasNext();) {
            int[] perm = permIter.next();
            if(isSorted(perm)) {
                return perm;
            }
        }
        return null; // should never happen
    }

    public static int[] insertionSortFA(int[] a) {
        if(a.length == 0) {
            return new int[0];
        }
        int[] r = insertionSortFA(withoutFirst(a));
        return insert(r, a[0]);
    }
    public static int[] withoutFirst(int[] a) {
        int[] res = new int[a.length - 1];
        System.arraycopy(a, 1, res, 0, a.length - 1);
        return res;
    }
    public static int[] insert(int[] a, int x) {
        if (a.length == 0) {
            return new int[]{x};
        }
        int[] res = new int[a.length + 1];
        if (x < a[0]) {
            res[0] = x;
            // FIXME: in the book i reaches res.length
            for(int i = 0; i < res.length - 1; i++) {
                res[i + 1] = a[i];
            }
        } else {
            int[] a1 = insert(withoutFirst(a), x); // FIXME: typo in book: v -> x
            res[0] = a[0];
            // FIXME: in the book i reaches res.length
            for(int i = 0; i < res.length - 1; i++) {
                res[i + 1] = a1[i];
            }
        }
        return res;
    }

    public interface IntList {
        IntList insertionSortFL();
        IntList insert(int x);
    }
    public static class Nil implements IntList {

        @Override
        public IntList insertionSortFL() {
            return this;
        }

        @Override
        public IntList insert(int x) {
            return new Cons(x, this);
        }
    }
    public static class Cons implements IntList {
        public final int head;
        public final IntList tail;
        public Cons(int value, IntList next) {
            this.head = value;
            this.tail = next;
        }

        @Override
        public IntList insertionSortFL() {
            return tail.insertionSortFL().insert(head);
        }

        @Override
        public IntList insert(int x) {
            if (x < head) {
                return new Cons(x, this); // FIXME: typo in book: a -> lst
            } else {
                return new Cons(head, tail.insert(x));
            }
        }
    }

    public static class PermutationIterator implements Iterator<int[]> {
        int[] base;
        BigInteger nPerm;
        BigInteger curPerm;
        public PermutationIterator(int[] base) {
            this.base = base;
            this.nPerm = bigFac(base.length);
            this.curPerm = BigInteger.ZERO;
        }
        private static BigInteger bigFac(int n) {
            BigInteger f = BigInteger.ONE;
            for(int i = 1; i <= n; i++) {
                f = f.multiply(BigInteger.valueOf(i));
            }
            return f;
        }
        public boolean hasNext() {
            return curPerm.compareTo(nPerm) < 0;
        }
        public int[] next() {
            int[] perm = Arrays.copyOf(base, base.length);
            BigInteger rest = curPerm;
            for(int i = 0; i < perm.length; i++) {
                int mod = base.length - i;
                BigInteger[] divWithRest = rest.divideAndRemainder(BigInteger.valueOf(mod));
                int j = i + divWithRest[1].intValue();
                int tmp = perm[i];
                perm[i] = perm[j];
                perm[j] = tmp;
                rest = divWithRest[0];
            }
            curPerm = curPerm.add(BigInteger.ONE);
            return perm;
        }
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3};
        System.out.printf("Permutations of %s: \n", Arrays.toString(a));
        for(Iterator<int[]> permIter = new PermutationIterator(a); permIter.hasNext();) {
            int[] perm = permIter.next();
            System.out.println(Arrays.toString(perm));
        }

        System.out.println();
        int[] data = {7, 4, 1, 5, 1, 9, 8, 10, 0, 2};
        System.out.printf("Sorting %s:\n", Arrays.toString(data));
        int[] sorted = Arrays.copyOf(data, data.length);
        insertionSortI(sorted);
        System.out.printf(" insertionSortI: %s\n", Arrays.toString(sorted));
        sorted = Arrays.copyOf(data, data.length);
        System.out.printf(" insertionSortF: %s\n", Arrays.toString(insertionSortF(sorted)));
        // TODO: result is incorrect
        System.out.printf("insertionSortFA: %s\n", Arrays.toString(insertionSortFA(data)));
        IntList dataL = new Nil();
        for(int i = data.length - 1; i >= 0; i--) { dataL = new Cons(data[i], dataL); }
        IntList res = dataL.insertionSortFL();
        sorted = new int[data.length];
        for(int i = 0; i < data.length; i++) {
            Cons resCons = (Cons) res;
            sorted[i] = resCons.head;
            res = resCons.tail;
        }
        System.out.printf("insertionSortFL: %s\n", Arrays.toString(sorted));
        System.out.printf("          sortP: %s\n", Arrays.toString(sortP(data)));
    }
}
