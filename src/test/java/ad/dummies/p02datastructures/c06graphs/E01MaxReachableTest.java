package ad.dummies.p02datastructures.c06graphs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static ad.dummies.p02datastructures.c06graphs.E01MaxReachable.*;

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
 * @see E01MaxReachable
 */
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
class E01MaxReachableTest {

    private List<Vertex> vampVertices;
    private Graph vampGraph;

    private List<Vertex> unconnectedVertices;
    private Graph unconnectedGraph;

    private List<Vertex> cyclicVertices;
    private Graph cyclicGraph;

    @BeforeEach
    public void createVampGraph() {
        String[] names = {"Vlad", "Brunhilde", "Olga", "Rüdiger", "Hildegard", "Anna"};
        vampVertices = new ArrayList<>();
        for(String n: names) { vampVertices.add(new Vertex(n)); }
        List<Edge> edges = List.of(
                new Edge(vampVertices.get(0), vampVertices.get(2)),
                new Edge(vampVertices.get(0), vampVertices.get(4)),
                new Edge(vampVertices.get(1), vampVertices.get(3)),
                new Edge(vampVertices.get(2), vampVertices.get(4)),
                new Edge(vampVertices.get(5), vampVertices.get(3)),
                new Edge(vampVertices.get(5), vampVertices.get(4))
        );
        vampGraph = new Graph(vampVertices, edges);
    }

    @BeforeEach
    public void createUnconnectedGraph() {
        unconnectedVertices = List.of(
                new Vertex("1a"),
                new Vertex("1b"),
                new Vertex("2a"),
                new Vertex("2b")
        );
        List<Edge> edges = List.of(
                new Edge(unconnectedVertices.get(0), unconnectedVertices.get(1)),
                new Edge(unconnectedVertices.get(3), unconnectedVertices.get(2))
        );
        unconnectedGraph = new Graph(unconnectedVertices, edges);
    }

    @BeforeEach
    public void createCyclicGraph() {
        cyclicVertices = List.of(
                new Vertex("a"),
                new Vertex("b"),
                new Vertex("c")
        );
        List<Edge> edges = List.of(
                new Edge(cyclicVertices.get(0), cyclicVertices.get(1)),
                new Edge(cyclicVertices.get(1), cyclicVertices.get(2)),
                new Edge(cyclicVertices.get(2), cyclicVertices.get(0))
        );
        cyclicGraph = new Graph(cyclicVertices, edges);
    }

    @Test
    public void travMarksAllVerticesInVampGraphAsVisited() {
        vampGraph.trav();
        for(Vertex v: vampVertices) {
            assertEquals(VisitState.VISITED, v.mark);
        }
    }

    @Test
    public void travSetContainsAllVerticesInVampGraph() {
        Set<Vertex> ts = vampGraph.travSet();
        assertTrue(ts.containsAll(vampVertices));
    }

    @Test
    public void maxReachableFindsMostInfluentialVampire() {
        assertEquals("Vlad", vampGraph.maxReachable().value);
    }

    @Test
    public void travMarksAllVerticesInUnconnectedGraphAsVisited() {
        unconnectedGraph.trav();
        for(Vertex v: unconnectedVertices) {
            assertEquals(VisitState.VISITED, v.mark);
        }
    }

    @Test
    public void travSetContainsAllVerticesInUnconnectedGraph() {
        Set<Vertex> ts = unconnectedGraph.travSet();
        assertTrue(ts.containsAll(unconnectedVertices));
    }

    @Test
    public void maxReachableYieldsCorrectVertexInUnconnectedGraph() {
        System.out.println(unconnectedGraph.maxReachable().value);
        assertTrue(Set.of("1a", "2b").contains(unconnectedGraph.maxReachable().value));
    }

    @Test
    public void travMarksAllVerticesInCyclicGraphAsVisited() {
        cyclicGraph.trav();
        for(Vertex v: cyclicVertices) {
            assertEquals(VisitState.VISITED, v.mark);
        }
    }

    @Test
    public void travSetContainsAllVerticesInCyclicGraph() {
        Set<Vertex> ts = cyclicGraph.travSet();
        assertTrue(ts.containsAll(cyclicVertices));
    }

    @Test
    public void maxReachableYieldsCorrectVertexInCyclicGraph() {
        assertTrue(Set.of("a", "b", "c").contains(cyclicGraph.maxReachable().value));
    }

    @Test
    public void mainMethodDoesNotThrowAnyExceptions() {
        main(new String[0]);
    }
}