package ad.dummies.p02datastructures.c05trees;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.*;
import static ad.dummies.p02datastructures.c05trees.E01BinTree.*;

/**
 * <p>Unit tests for an example from the german book "Algorithms and data
 * structures for dummies":</p>
 *
 * <p>A. Gogol-Döring and T. Letschert, <i>Algorithmen und Datenstrukturen für
 * Dummies</i>. Weinheim, Germany: Wiley-VCH, 2019.</p>
 *
 * <p>The current version of these examples with unit tests and benchmarks can
 * be found <a href="https://github.com/CSchoel/ad-dummies-java">on GitHub</a>.
 * </p>
 *
 * @author Christopher Schölzel
 * @see E01BinTree
 */
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
class E01BinTreeTest {
    @Test
    public void treeFromEmptyListIsEmpty() {
        ImmutableArrayList lst = new ImmutableArrayList(0);
        assertTrue(lst.toBTree() instanceof Empty);
    }

    @Test
    public void treeFromOneElementListContainsOneElement() {
        ImmutableArrayList lst = new ImmutableArrayList(1);
        lst = lst.add(5);
        BinTree tree = lst.toBTree();
        assertTrue(tree instanceof Node);
        Node treeNode = (Node) tree;
        assertEquals(5, treeNode.value);
        assertTrue(treeNode.left instanceof Empty);
        assertTrue(treeNode.right instanceof Empty);
    }

    @Test
    public void treeFromTwoElementListIsLeftHeavy() {
        ImmutableArrayList lst = new ImmutableArrayList(2);
        lst = lst.add(5);
        lst = lst.add(2);
        BinTree tree = lst.toBTree();
        Node treeNode = (Node) tree;
        assertEquals(5, treeNode.value);
        assertTrue(treeNode.right instanceof Empty);
        treeNode = (Node) treeNode.left;
        assertEquals(2, treeNode.value);
        assertTrue(treeNode.left instanceof Empty);
        assertTrue(treeNode.right instanceof Empty);
    }

    @Test
    public void treeFromFiveElementListSplitsListInMiddle() {
        ImmutableArrayList lst = new ImmutableArrayList(5);
        lst = lst.add(1);
        lst = lst.add(2);
        lst = lst.add(3);
        lst = lst.add(4);
        lst = lst.add(5);
        BinTree tree = lst.toBTree();
        Node treeNode1 = (Node) tree;
        assertEquals(1, treeNode1.value);
        Node treeNode2 = (Node) treeNode1.left;
        assertEquals(2, treeNode2.value);
        Node treeNode4 = (Node) treeNode1.right;
        assertEquals(4, treeNode4.value);
        Node treeNode3 = (Node) treeNode2.left;
        assertEquals(3, treeNode3.value);
        Node treeNode5 = (Node) treeNode4.left;
        assertEquals(5, treeNode5.value);
    }

    @Test
    public void sumOfEmptyTreeIsZero() {
        assertEquals(0, new Empty().sum());
    }

    @Test
    public void sumOfOneElementTreeIsValueOfElement() {
        assertEquals(5, new Node(5, new Empty(), new Empty()).sum());
    }

    @Test
    public void sumOfFiveElementTreeIsSumOfValues() {
        Empty e = new Empty();
        BinTree t = new Node(
                1,
                new Node(
                        2,
                        new Node(3, e, e),
                        e
                ),
                new Node(
                        4,
                        e,
                        new Node(5, e, e)
                )
        );
        assertEquals(15, t.sum());
    }

    @Test
    public void depthOfEmptyTreeIsZero() {
        assertEquals(0, new Empty().depth());
    }

    @Test
    public void depthOfOneElementTreeIsOne() {
        assertEquals(1, new Node(5, new Empty(), new Empty()).depth());
    }

    @Test
    public void depthOfBalancedFiveElementTreeIsThree() {
        Empty e = new Empty();
        BinTree t = new Node(
                1,
                new Node(
                        2,
                        new Node(3, e, e),
                        e
                ),
                new Node(
                        4,
                        e,
                        new Node(5, e, e)
                )
        );
        assertEquals(3, t.depth());
    }


    @Test
    public void mainMethodDoesNotThrowAnyExceptions() {
        main(new String[0]);
    }
}