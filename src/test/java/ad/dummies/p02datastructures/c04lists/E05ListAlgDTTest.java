package ad.dummies.p02datastructures.c04lists;

import ad.dummies.p01basics.c03datastructures.E05ListSumAlgDT;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

import static ad.dummies.p02datastructures.c04lists.E05ListAlgDT.*;

class E05ListAlgDTTest {

    @Test
    void iteratorOverNilHasNoElements() {
        List<Integer> lst = new Nil<>();
        assertFalse(lst.iterator().hasNext());
    }

    @Test
    void iteratorOverSingleConsHasOneElement() {
        List<Integer> lst = new Cons<>(2, new Nil<>());
        Iterator<Integer> it = lst.iterator();
        assertTrue(it.hasNext());
        assertEquals(2, it.next());
        assertFalse(it.hasNext());
    }

    @Test
    void iteratorOverTwoElementListPreservesOrder() {
        List<Integer> lst = new Cons<>(2, new Cons<>(4, new Nil<>()));
        Iterator<Integer> it = lst.iterator();
        assertTrue(it.hasNext());
        assertEquals(2, it.next());
        assertTrue(it.hasNext());
        assertEquals(4, it.next());
        assertFalse(it.hasNext());
    }

    @Test
    void nilHasLength0() {
        List<Integer> lst = new Nil<>();
        assertEquals(0, lst.length());
    }

    @Test
    void singleConsHasLength1() {
        List<Integer> lst = new Cons<>(-1, new Nil<>());
        assertEquals(1, lst.length());
    }

    @Test
    void twoElementListHasLength2() {
        List<Integer> lst = new Cons<>(-1, new Cons<>(17, new Nil<>()));
        assertEquals(2, lst.length());
    }

    @Test
    void sumOfNilIs0() {
        List<Integer> lst = new Nil<>();
        assertEquals(0, sum(lst));
    }

    @Test
    void sumOfSingleConsEqualsElementValue() {
        List<Integer> lst = new Cons<>(2, new Nil<>());
        assertEquals(2, sum(lst));
    }

    @Test
    void sumOfTwoElementListIsSumOfElements() {
        List<Integer> lst = new Cons<>(2, new Cons<>(4, new Nil<>()));
        assertEquals(6, sum(lst));
    }

    @Test
    public void appendingNilToNilYieldsNil() {
        List<Integer> lst = new Nil<Integer>().append(new Nil<>());
        assertTrue(lst instanceof Nil);
    }

    @Test
    public void appendingListToNilYieldsThatList() {
        List<Integer> lst = new Cons<>(1, new Cons<>(2, new Nil<>()));
        List<Integer> lst2 = new Nil<Integer>().append(lst);
        Iterator<Integer> it2 = lst2.iterator();
        assertEquals(1, it2.next());
        assertEquals(2, it2.next());
        assertFalse(it2.hasNext());
    }

    @Test
    public void appendingListToListYieldsConcatenation() {
        List<Integer> lst = new Cons<>(1, new Cons<>(2, new Nil<>()));
        List<Integer> lst2 = new Cons<>(3, new Cons<>(4, new Nil<>()));
        lst2 = lst.append(lst2);
        Iterator<Integer> it = lst2.iterator();
        assertEquals(1, it.next());
        assertEquals(2, it.next());
        assertEquals(3, it.next());
        assertEquals(4, it.next());
    }

    private static List<Integer> buildIntList(int... ints) {
        List<Integer> res = new Nil<>();
        for(int x: ints) {
            res = new Cons<>(x, res);
        }
        return res;
    }

    @Test
    public void quicksortEmptyList() {
        assertTrue(new Nil<Integer>().quicksort(Integer::compareTo) instanceof Nil);
    }

    @Test
    public void quicksortTwoElementsAscending() {
        List<Integer> sorted = buildIntList(1, 2).quicksort(Integer::compareTo);

        Iterator<Integer> it = sorted.iterator();
        assertEquals(1, it.next());
        assertEquals(2, it.next());
        assertFalse(it.hasNext());
    }

    @Test
    public void quicksortTwoElementsDescending() {
        List<Integer> sorted = buildIntList(2, 1).quicksort(Integer::compareTo);

        Iterator<Integer> it = sorted.iterator();
        assertEquals(1, it.next());
        assertEquals(2, it.next());
        assertFalse(it.hasNext());
    }

    @Test
    public void quicksortFiveElementsAscending() {
        List<Integer> sorted = buildIntList(-1, 0, 8, 10, 11).quicksort(Integer::compareTo);

        Iterator<Integer> it = sorted.iterator();
        assertEquals(-1, it.next());
        assertEquals(0, it.next());
        assertEquals(8, it.next());
        assertEquals(10, it.next());
        assertEquals(11, it.next());
        assertFalse(it.hasNext());
    }

    @Test
    public void quicksortFiveElementsDescending() {
        List<Integer> sorted = buildIntList(11, 10, 8, 0, -1).quicksort(Integer::compareTo);

        Iterator<Integer> it = sorted.iterator();
        assertEquals(-1, it.next());
        assertEquals(0, it.next());
        assertEquals(8, it.next());
        assertEquals(10, it.next());
        assertEquals(11, it.next());
        assertFalse(it.hasNext());
    }

    @Test
    public void quicksortFiveElementsUnordered() {
        List<Integer> sorted = buildIntList( -1, 11, 8, 0, 10).quicksort(Integer::compareTo);

        Iterator<Integer> it = sorted.iterator();
        assertEquals(-1, it.next());
        assertEquals(0, it.next());
        assertEquals(8, it.next());
        assertEquals(10, it.next());
        assertEquals(11, it.next());
        assertFalse(it.hasNext());
    }

    @Test
    public void filteringNilYieldsNil() {
        assertTrue(new Nil<Integer>().filter(x -> x % 2 == 0) instanceof Nil);
    }

    @Test
    public void filteringOutAllElementsYieldsNil() {
        assertTrue(buildIntList(1, 3).filter(x -> x % 2 == 0) instanceof Nil);
    }

    @Test
    public void filteringSomeElementsLeavesCorrectElements() {
        List<Integer> filtered = buildIntList(2, 1, 4, 3).filter(x -> x % 2 == 0);

        Iterator<Integer> it = filtered.iterator();
        assertEquals(4, it.next());
        assertEquals(2, it.next());
    }

    @Test
    public void mappingNilYieldsNil() {
        assertTrue(new Nil<Integer>().map(x -> x + 1) instanceof Nil);
    }

    @Test
    public void mappingThreeElementListToSameType() {
        List<Integer> mapped = buildIntList(2, 1, 4).map(x -> x + 1);

        Iterator<Integer> it = mapped.iterator();
        assertEquals(5, it.next());
        assertEquals(2, it.next());
        assertEquals(3, it.next());
    }

    @Test
    public void mappingThreeElementListToDifferentType() {
        List<String> mapped = buildIntList(2, 1, 4).map(x -> x + "1");

        Iterator<String> it = mapped.iterator();
        assertEquals("41", it.next());
        assertEquals("11", it.next());
        assertEquals("21", it.next());
    }

    @Test
    public void permOfNilIsOneElementListContainingNil() {
        List<List<Integer>> perms = new Nil<Integer>().perm();
        assertEquals(1, perms.length());
        assertTrue(perms.iterator().next() instanceof Nil);
    }

    private int[] intListToArray(List<Integer> lst) {
        int[] ar = new int[lst.length()];
        Iterator<Integer> it = lst.iterator();
        for(int i = 0; i < ar.length; i++) {
            ar[i] = it.next();
        }
        return ar;
    }

    private boolean contains(List<List<Integer>> haystack, List<Integer> needle) {
        for(List<Integer> lst: haystack) {
            boolean equal = true;
            Iterator<Integer> itL = lst.iterator();
            Iterator<Integer> itN = needle.iterator();
            while(itL.hasNext() && itN.hasNext()) {
                if (!itL.next().equals(itN.next())) {
                    equal = false;
                }
            }
            equal = (!itL.hasNext() && !itN.hasNext());
            if (equal) { return true; }
        }
        return false;
    }

    @Test
    public void permOfOneElement() {
        List<List<Integer>> perms = buildIntList(1).perm();

        assertTrue(contains(perms, buildIntList(1)));
        assertEquals(1, perms.length());
    }

    @Test
    public void permOfTwoElements() {
        List<List<Integer>> perms = buildIntList(1, 2).perm();

        assertTrue(contains(perms, buildIntList(1, 2)));
        assertTrue(contains(perms, buildIntList(2, 1)));
        assertEquals(2, perms.length());
    }

    @Test
    public void permOfThreeElements() {
        List<List<Integer>> perms = buildIntList(1, 2, 3).perm();

        assertTrue(contains(perms, buildIntList(1, 2, 3)));
        assertTrue(contains(perms, buildIntList(1, 3, 2)));
        assertTrue(contains(perms, buildIntList(2, 1, 3)));
        assertTrue(contains(perms, buildIntList(2, 3, 1)));
        assertTrue(contains(perms, buildIntList(3, 1, 2)));
        assertTrue(contains(perms, buildIntList(3, 2, 1)));
        assertEquals(6, perms.length());
    }


    @Test
    public void mainMethodDoesNotThrowAnyExceptions() {
        main(new String[0]);
    }
}