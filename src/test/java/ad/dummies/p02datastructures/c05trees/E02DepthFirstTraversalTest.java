package ad.dummies.p02datastructures.c05trees;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static ad.dummies.p02datastructures.c05trees.E02DepthFirstTraversal.*;
import static org.junit.jupiter.api.Assertions.*;

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
 * @see E02DepthFirstTraversal
 */
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
class E02DepthFirstTraversalTest {

    @Test
    public void preorderTraversalOfEmptyTree() {
        assertFalse(new Empty().preorder().iterator().hasNext());
    }

    @Test
    public void preorderTraversalOfOneElementTree() {
        Iterator<Integer> it = new Node(4, new Empty(), new Empty()).preorder().iterator();
        assertTrue(it.hasNext());
        assertEquals(4, it.next());
        assertFalse(it.hasNext());
    }

    @Test
    public void preorderTraversalOfFiveElementTree() {
        Empty e = new Empty();
        Iterator<Integer> it = new Node(
                1,
                new Node(2, new Node(3, e, e), e),
                new Node(4, e, new Node(5, e, e))
        ).preorder().iterator();
        assertEquals(1, it.next());
        assertEquals(2, it.next());
        assertEquals(3, it.next());
        assertEquals(4, it.next());
        assertEquals(5, it.next());
        assertFalse(it.hasNext());
    }

    @Test
    public void inorderTraversalOfEmptyTree() {
        assertFalse(new Empty().inorder().iterator().hasNext());
    }

    @Test
    public void inorderTraversalOfOneElementTree() {
        Iterator<Integer> it = new Node(4, new Empty(), new Empty()).inorder().iterator();
        assertTrue(it.hasNext());
        assertEquals(4, it.next());
        assertFalse(it.hasNext());
    }

    @Test
    public void inorderTraversalOfFiveElementTree() {
        Empty e = new Empty();
        Iterator<Integer> it = new Node(
                1,
                new Node(2, new Node(3, e, e), e),
                new Node(4, e, new Node(5, e, e))
        ).inorder().iterator();
        assertEquals(3, it.next());
        assertEquals(2, it.next());
        assertEquals(1, it.next());
        assertEquals(4, it.next());
        assertEquals(5, it.next());
        assertFalse(it.hasNext());
    }

    @Test
    public void postorderTraversalOfEmptyTree() {
        assertFalse(new Empty().postorder().iterator().hasNext());
    }

    @Test
    public void postorderTraversalOfOneElementTree() {
        Iterator<Integer> it = new Node(4, new Empty(), new Empty()).postorder().iterator();
        assertTrue(it.hasNext());
        assertEquals(4, it.next());
        assertFalse(it.hasNext());
    }

    @Test
    public void postorderTraversalOfFiveElementTree() {
        Empty e = new Empty();
        Iterator<Integer> it = new Node(
                1,
                new Node(2, new Node(3, e, e), e),
                new Node(4, e, new Node(5, e, e))
        ).postorder().iterator();
        assertEquals(3, it.next());
        assertEquals(2, it.next());
        assertEquals(5, it.next());
        assertEquals(4, it.next());
        assertEquals(1, it.next());
        assertFalse(it.hasNext());
    }

    @Test
    public void iteratorTraversalOfEmptyTree() {
        assertFalse(new Empty().iterator().hasNext());
    }

    @Test
    public void iteratorTraversalOfOneElementTree() {
        Iterator<Integer> it = new Node(4, new Empty(), new Empty()).iterator();
        assertTrue(it.hasNext());
        assertEquals(4, it.next());
        assertFalse(it.hasNext());
    }

    @Test
    public void iteratorTraversalOfFiveElementTree() {
        Empty e = new Empty();
        Iterator<Integer> it = new Node(
                1,
                new Node(2, new Node(3, e, e), e),
                new Node(4, e, new Node(5, e, e))
        ).iterator();
        assertEquals(3, it.next());
        assertEquals(2, it.next());
        assertEquals(1, it.next());
        assertEquals(4, it.next());
        assertEquals(5, it.next());
        assertFalse(it.hasNext());
    }

    @Test
    public void inorderProcessTraversalOfEmptyTree() {
        List<Integer> lst = new ArrayList<>();
        new Empty().inorderProcess(lst::add);
        assertEquals(List.of(), lst);
    }

    @Test
    public void inorderProcessTraversalOfOneElementTree() {
        List<Integer> lst = new ArrayList<>();
        new Node(4, new Empty(), new Empty()).inorderProcess(lst::add);
        assertEquals(List.of(4), lst);
    }

    @Test
    public void inorderProcessTraversalOfFiveElementTree() {
        List<Integer> lst = new ArrayList<>();
        Empty e = new Empty();
        new Node(
                1,
                new Node(2, new Node(3, e, e), e),
                new Node(4, e, new Node(5, e, e))
        ).inorderProcess(lst::add);
        assertEquals(List.of(3, 2, 1, 4, 5), lst);
    }

    @Test
    public void inorderStack1TraversalOfEmptyTree() {
        List<Integer> lst = new ArrayList<>();
        new Empty().inorderStack1(lst::add);
        assertEquals(List.of(), lst);
    }

    @Test
    public void inorderStack1TraversalOfOneElementTree() {
        List<Integer> lst = new ArrayList<>();
        new Node(4, new Empty(), new Empty()).inorderStack1(lst::add);
        assertEquals(List.of(4), lst);
    }

    @Test
    public void inorderStack1TraversalOfFiveElementTree() {
        List<Integer> lst = new ArrayList<>();
        Empty e = new Empty();
        new Node(
                1,
                new Node(2, new Node(3, e, e), e),
                new Node(4, e, new Node(5, e, e))
        ).inorderStack1(lst::add);
        assertEquals(List.of(3, 2, 1, 4, 5), lst);
    }

    @Test
    public void inorderStack2TraversalOfEmptyTree() {
        List<Integer> lst = new ArrayList<>();
        new Empty().inorderStack2(lst::add);
        assertEquals(List.of(), lst);
    }

    @Test
    public void inorderStack2TraversalOfOneElementTree() {
        List<Integer> lst = new ArrayList<>();
        new Node(4, new Empty(), new Empty()).inorderStack2(lst::add);
        assertEquals(List.of(4), lst);
    }

    @Test
    public void inorderStack2TraversalOfFiveElementTree() {
        List<Integer> lst = new ArrayList<>();
        Empty e = new Empty();
        new Node(
                1,
                new Node(2, new Node(3, e, e), e),
                new Node(4, e, new Node(5, e, e))
        ).inorderStack2(lst::add);
        assertEquals(List.of(3, 2, 1, 4, 5), lst);
    }

    @Test
    public void mainMethodDoesNotThrowAnyExceptions() {
        main(new String[0]);
    }

}