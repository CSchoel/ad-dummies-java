package ad.dummies.p01basics.c03datastructures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.*;
import static ad.dummies.p01basics.c03datastructures.E08StructuralRecursion.*;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
class E08StructuralRecursionTest {

    private static IntList buildIntList(int... ints) {
        IntList res = new Nil();
        for(int x: ints) {
            res = new Cons(x, res);
        }
        return res;
    }

    @Test
    public void listSumNil() {
        IntList lstZero = new Nil();
        assertEquals(0, lstZero.listSum());
    }

    @Test
    public void listSumFive() {
        IntList lstFive = buildIntList(5, 1, -3, 13, -8);
        assertEquals(8, lstFive.listSum());
    }

    @Test
    public void treeSumOfLeaf() {
        IntTree treeLeaf = new Leaf(5);
        assertEquals(5, treeLeaf.treeSum());
    }

    @Test
    public void treeSumOfLeftHeavyTree() {
        IntTree treeLeft = new Node(new Node(new Node(new Leaf(1), new Leaf(2)), new Leaf(3)), new Leaf(4));
        assertEquals(10, treeLeft.treeSum());
    }

    @Test
    public void treeSumOfRightHeavyTree() {
        IntTree treeRight = new Node(new Leaf(1), new Node(new Leaf(2), new Node(new Leaf(3), new Leaf(4))));
        assertEquals(10, treeRight.treeSum());
    }

    @Test
    public void treeSumOfTreeExampleFromBook() {
        IntTree treeBook = new Node(new Node(new Node(new Leaf(1), new Leaf(2)), new Leaf(3)), new Node(new Leaf(4), new Leaf(5)));
        assertEquals(15, treeBook.treeSum());
    }

    @Test
    public void insertionSortEmptyList() {
        assertTrue(new Nil().insSort() instanceof Nil);
    }

    @Test
    public void insertionSortTwoElementsAscending() {
        IntList sorted = buildIntList(1, 2).insSort();

        assertTrue(sorted instanceof Cons);
        assertEquals(1, ((Cons) sorted).value());
        sorted = ((Cons) sorted).next();

        assertTrue(sorted instanceof Cons);
        assertEquals(2, ((Cons) sorted).value());
        sorted = ((Cons) sorted).next();

        assertTrue(sorted instanceof Nil);
    }

    @Test
    public void insertionSortTwoElementsDescending() {
        IntList sorted = buildIntList(2, 1).insSort();

        assertTrue(sorted instanceof Cons);
        assertEquals(1, ((Cons) sorted).value());
        sorted = ((Cons) sorted).next();

        assertTrue(sorted instanceof Cons);
        assertEquals(2, ((Cons) sorted).value());
        sorted = ((Cons) sorted).next();

        assertTrue(sorted instanceof Nil);
    }

    @Test
    public void insertionSortFiveElementsAscending() {
        IntList sorted = buildIntList(-1, 0, 8, 10, 11).insSort();

        assertTrue(sorted instanceof Cons);
        assertEquals(-1, ((Cons) sorted).value());
        sorted = ((Cons) sorted).next();

        assertTrue(sorted instanceof Cons);
        assertEquals(0, ((Cons) sorted).value());
        sorted = ((Cons) sorted).next();

        assertTrue(sorted instanceof Cons);
        assertEquals(8, ((Cons) sorted).value());
        sorted = ((Cons) sorted).next();

        assertTrue(sorted instanceof Cons);
        assertEquals(10, ((Cons) sorted).value());
        sorted = ((Cons) sorted).next();

        assertTrue(sorted instanceof Cons);
        assertEquals(11, ((Cons) sorted).value());
        sorted = ((Cons) sorted).next();

        assertTrue(sorted instanceof Nil);
    }

    @Test
    public void insertionSortFiveElementsDescending() {
        IntList sorted = buildIntList(11, 10, 8, 0, -1).insSort();

        assertTrue(sorted instanceof Cons);
        assertEquals(-1, ((Cons) sorted).value());
        sorted = ((Cons) sorted).next();

        assertTrue(sorted instanceof Cons);
        assertEquals(0, ((Cons) sorted).value());
        sorted = ((Cons) sorted).next();

        assertTrue(sorted instanceof Cons);
        assertEquals(8, ((Cons) sorted).value());
        sorted = ((Cons) sorted).next();

        assertTrue(sorted instanceof Cons);
        assertEquals(10, ((Cons) sorted).value());
        sorted = ((Cons) sorted).next();

        assertTrue(sorted instanceof Cons);
        assertEquals(11, ((Cons) sorted).value());
        sorted = ((Cons) sorted).next();

        assertTrue(sorted instanceof Nil);
    }

    @Test
    public void insertionSortFiveElementsUnordered() {
        IntList sorted = buildIntList( -1, 11, 8, 0, 10).insSort();

        assertTrue(sorted instanceof Cons);
        assertEquals(-1, ((Cons) sorted).value());
        sorted = ((Cons) sorted).next();

        assertTrue(sorted instanceof Cons);
        assertEquals(0, ((Cons) sorted).value());
        sorted = ((Cons) sorted).next();

        assertTrue(sorted instanceof Cons);
        assertEquals(8, ((Cons) sorted).value());
        sorted = ((Cons) sorted).next();

        assertTrue(sorted instanceof Cons);
        assertEquals(10, ((Cons) sorted).value());
        sorted = ((Cons) sorted).next();

        assertTrue(sorted instanceof Cons);
        assertEquals(11, ((Cons) sorted).value());
        sorted = ((Cons) sorted).next();

        assertTrue(sorted instanceof Nil);
    }

    @Test
    public void treeToListLeaf() {
        IntList lst = new Leaf(9).treeToList();

        assertTrue(lst instanceof Cons);
        assertEquals(9, ((Cons) lst).value());
        assertTrue(((Cons) lst).next() instanceof Nil);
    }

    @Test
    public void treeToListNodeWithTwoLeaves() {
        IntList lst = new Node(new Leaf(1), new Leaf(2)).treeToList();

        assertTrue(lst instanceof Cons);
        assertEquals(1, ((Cons) lst).value());
        lst = ((Cons) lst).next();

        assertTrue(lst instanceof Cons);
        assertEquals(2, ((Cons) lst).value());
        lst = ((Cons) lst).next();

        assertTrue(lst instanceof Nil);
    }

    @Test
    public void treeToListExampleTreeFromBook() {
        IntTree treeBook = new Node(new Node(new Node(new Leaf(1), new Leaf(2)), new Leaf(3)), new Node(new Leaf(4), new Leaf(5)));
        IntList lst = treeBook.treeToList();

        assertTrue(lst instanceof Cons);
        assertEquals(1, ((Cons) lst).value());
        lst = ((Cons) lst).next();

        assertTrue(lst instanceof Cons);
        assertEquals(2, ((Cons) lst).value());
        lst = ((Cons) lst).next();

        assertTrue(lst instanceof Cons);
        assertEquals(3, ((Cons) lst).value());
        lst = ((Cons) lst).next();

        assertTrue(lst instanceof Cons);
        assertEquals(4, ((Cons) lst).value());
        lst = ((Cons) lst).next();

        assertTrue(lst instanceof Cons);
        assertEquals(5, ((Cons) lst).value());
        lst = ((Cons) lst).next();

        assertTrue(lst instanceof Nil);
    }
}