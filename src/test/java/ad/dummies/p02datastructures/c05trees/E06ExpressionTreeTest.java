package ad.dummies.p02datastructures.c05trees;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.*;
import static ad.dummies.p02datastructures.c05trees.E06ExpressionTree.*;

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
 * @see E06ExpressionTree
 */
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
class E06ExpressionTreeTest {

    @Test
    public void evaluateOfValueObjectYieldsValue() {
        Value v = new Value(5);
        assertEquals(5, v.evaluate());
    }

    @Test
    public void evaluateOfAddObjectWithTwoValueObjectsYieldsSum() {
        Exp e = new Add(new Value(5), new Value(3));
        assertEquals(8, e.evaluate());
    }

    @Test
    public void evaluateOfSubObjectWithTwoValueObjectsYieldsDifference() {
        Exp e = new Sub(new Value(5), new Value(3));
        assertEquals(2, e.evaluate());
    }

    @Test
    public void evaluateOfMultObjectWithTwoValueObjectsYieldsProduct() {
        Exp e = new Mult(new Value(5), new Value(3));
        assertEquals(15, e.evaluate());
    }

    @Test
    public void evaluateOfMixedExpressionObjectsYieldsCorrectResult() {
        Exp e = new Mult(
                new Sub(new Value(5), new Value(6)),
                new Add(new Value(1), new Value(8))
        );
        assertEquals(-9, e.evaluate());
    }

    @Test
    public void evalNodesOfValueObjectYieldsValue() {
        Value v = new Value(5);
        assertEquals(5, evalNodes(v.evalToNodes()));
    }

    @Test
    public void evalNodesOfAddObjectWithTwoValueObjectsYieldsSum() {
        Exp e = new Add(new Value(5), new Value(3));
        assertEquals(8, evalNodes(e.evalToNodes()));
    }

    @Test
    public void evalNodesOfSubObjectWithTwoValueObjectsYieldsDifference() {
        Exp e = new Sub(new Value(5), new Value(3));
        assertEquals(2, evalNodes(e.evalToNodes()));
    }

    @Test
    public void evalNodesOfMultObjectWithTwoValueObjectsYieldsProduct() {
        Exp e = new Mult(new Value(5), new Value(3));
        assertEquals(15, evalNodes(e.evalToNodes()));
    }

    @Test
    public void evalNodesOfMixedExpressionObjectsYieldsCorrectResult() {
        Exp e = new Mult(
                new Sub(new Value(5), new Value(6)),
                new Add(new Value(1), new Value(8))
        );
        assertEquals(-9, evalNodes(e.evalToNodes()));
    }

    @Test
    public void evalNodesIterOfValueObjectYieldsValue() {
        Value v = new Value(5);
        assertEquals(5, evalNodesIter(v.evalToNodes()));
    }

    @Test
    public void evalNodesIterOfAddObjectWithTwoValueObjectsYieldsSum() {
        Exp e = new Add(new Value(5), new Value(3));
        assertEquals(8, evalNodesIter(e.evalToNodes()));
    }

    @Test
    public void evalNodesIterOfSubObjectWithTwoValueObjectsYieldsDifference() {
        Exp e = new Sub(new Value(5), new Value(3));
        assertEquals(2, evalNodesIter(e.evalToNodes()));
    }

    @Test
    public void evalNodesIterOfMultObjectWithTwoValueObjectsYieldsProduct() {
        Exp e = new Mult(new Value(5), new Value(3));
        assertEquals(15, evalNodesIter(e.evalToNodes()));
    }

    @Test
    public void evalNodesIterOfMixedExpressionObjectsYieldsCorrectResult() {
        Exp e = new Mult(
                new Sub(new Value(5), new Value(6)),
                new Add(new Value(1), new Value(8))
        );
        assertEquals(-9, evalNodesIter(e.evalToNodes()));
    }

    @Test
    public void mainMethodDoesNotThrowAnyExceptions() {
        main(new String[0]);
    }
}