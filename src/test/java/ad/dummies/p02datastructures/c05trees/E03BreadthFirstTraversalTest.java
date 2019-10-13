package ad.dummies.p02datastructures.c05trees;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static ad.dummies.p02datastructures.c05trees.E03BreadthFirstTraversal.*;

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
 * @see E03BreadthFirstTraversal
 */
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
class E03BreadthFirstTraversalTest {

    @Test
    public void breadthFirstTraversalOfEmptyTree() {
        List<Integer> lst = new ArrayList<>();
        breadthFirst(new Empty(), lst::add);
        assertEquals(List.of(), lst);
    }

    @Test
    public void breadthFirstTraversalOfOneElementTree() {
        List<Integer> lst = new ArrayList<>();
        breadthFirst(new Node(4, new Empty(), new Empty()), lst::add);
        assertEquals(List.of(4), lst);
    }

    @Test
    public void breadthFirstTraversalOfFiveElementTree() {
        List<Integer> lst = new ArrayList<>();
        Empty e = new Empty();
        breadthFirst(new Node(
                    1,
                    new Node(2, new Node(3, e, e), e),
                    new Node(4, e, new Node(5, e, e))
                ),
                lst::add
        );
        assertEquals(List.of(1, 2, 4, 3, 5), lst);
    }

    @Test
    public void breadthFirst2TraversalOfEmptyTree() {
        List<Integer> lst = new ArrayList<>();
        breadthFirst2(new Empty(), lst::add);
        assertEquals(List.of(), lst);
    }

    @Test
    public void breadthFirst2TraversalOfOneElementTree() {
        List<Integer> lst = new ArrayList<>();
        breadthFirst2(new Node(4, new Empty(), new Empty()), lst::add);
        assertEquals(List.of(4), lst);
    }

    @Test
    public void breadthFirst2TraversalOfFiveElementTree() {
        List<Integer> lst = new ArrayList<>();
        Empty e = new Empty();
        breadthFirst2(new Node(
                        1,
                        new Node(2, new Node(3, e, e), e),
                        new Node(4, e, new Node(5, e, e))
                ),
                lst::add
        );
        assertEquals(List.of(1, 2, 4, 3, 5), lst);
    }

    @Test
    public void breadthFirstIteratorTraversalOfEmptyTree() {
        List<Integer> lst = new ArrayList<>();
        new Empty().iterator().forEachRemaining(lst::add);
        assertEquals(List.of(), lst);
    }

    @Test
    public void breadthFirstIteratorTraversalOfOneElementTree() {
        List<Integer> lst = new ArrayList<>();
        new Node(4, new Empty(), new Empty()).iterator().forEachRemaining(lst::add);
        assertEquals(List.of(4), lst);
    }

    @Test
    public void breadthFirstIteratorTraversalOfFiveElementTree() {
        List<Integer> lst = new ArrayList<>();
        Empty e = new Empty();
        new Node(
                1,
                new Node(2, new Node(3, e, e), e),
                new Node(4, e, new Node(5, e, e))
        ).iterator().forEachRemaining(lst::add);
        assertEquals(List.of(1, 2, 4, 3, 5), lst);
    }

    @Test
    public void breadthFirstIterator2TraversalOfEmptyTree() {
        List<Integer> lst = new ArrayList<>();
        new BreadthFirstIterator2(new Empty()).forEachRemaining(lst::add);
        assertEquals(List.of(), lst);
    }

    @Test
    public void breadthFirstIterator2TraversalOfOneElementTree() {
        List<Integer> lst = new ArrayList<>();
        new BreadthFirstIterator2(new Node(4, new Empty(), new Empty())).forEachRemaining(lst::add);
        assertEquals(List.of(4), lst);
    }

    @Test
    public void breadthFirstIterator2TraversalOfFiveElementTree() {
        List<Integer> lst = new ArrayList<>();
        Empty e = new Empty();
        new BreadthFirstIterator2(new Node(
                1,
                new Node(2, new Node(3, e, e), e),
                new Node(4, e, new Node(5, e, e))
        )).forEachRemaining(lst::add);
        assertEquals(List.of(1, 2, 4, 3, 5), lst);
    }

    @Test
    public void mainMethodDoesNotThrowAnyExceptions() {
        main(new String[0]);
    }
}