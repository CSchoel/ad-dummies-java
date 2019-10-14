package ad.dummies.p02datastructures.c06graphs;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static ad.dummies.p02datastructures.c06graphs.E03TopologicalSort.*;

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
 * @see E03TopologicalSort
 */
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
class E03TopologicalSortTest {

    @Test
    public void topoSortFuncOfEmptyGraphYieldsEmptyList() {
        Graph g = new Graph(new ArrayList<>(), new ArrayList<>());
        assertTrue(g.topoSortFunc() instanceof Nil);
    }

    @Test
    public void topoSortFuncOfSingleVertexGraphYieldsThatVertex() {
        Graph g = new Graph(List.of(new Vertex("a")), new ArrayList<>());
        ImmutableLinkedList<Vertex> lst = g.topoSortFunc();
        assertTrue(lst instanceof Cons);
        Cons<Vertex> lCons = (Cons<Vertex>) lst;
        assertEquals("a", lCons.head.value);
        assertTrue(lCons.tail instanceof Nil);
    }

    @Test
    public void topoSortFuncOfTwoConnectedVerticesYieldsSourceFirst() {
        List<Vertex> vertices = List.of(new Vertex("sink"), new Vertex("source"));
        List<Edge> edges = List.of(
                new Edge(vertices.get(1), vertices.get(0))
        );
        Graph g = new Graph(vertices, edges);
        ImmutableLinkedList<Vertex> lst = g.topoSortFunc();
        Cons<Vertex> lCons = (Cons<Vertex>) lst;
        assertEquals("source", lCons.head.value);
        lCons = (Cons<Vertex>) lCons.tail;
        assertEquals("sink", lCons.head.value);
        assertTrue(lCons.tail instanceof Nil);
    }

    @Test
    public void topoSortFuncOfThreeConnectedVerticesYieldsSourceFirst() {
        List<Vertex> vertices = List.of(
                new Vertex("sink"),
                new Vertex("intermediate"),
                new Vertex("source")
        );
        List<Edge> edges = List.of(
                new Edge(vertices.get(2), vertices.get(1)),
                new Edge(vertices.get(1), vertices.get(0))
        );
        Graph g = new Graph(vertices, edges);
        ImmutableLinkedList<Vertex> lst = g.topoSortFunc();
        Cons<Vertex> lCons = (Cons<Vertex>) lst;
        assertEquals("source", lCons.head.value);
        lCons = (Cons<Vertex>) lCons.tail;
        assertEquals("intermediate", lCons.head.value);
        lCons = (Cons<Vertex>) lCons.tail;
        assertEquals("sink", lCons.head.value);
        assertTrue(lCons.tail instanceof Nil);
    }

    @Test
    public void topoSortFuncOfTwoSourcesAndOneSinkYieldsSinkLast() {
        List<Vertex> vertices = List.of(
                new Vertex("sink"),
                new Vertex("source1"),
                new Vertex("source2")
        );
        List<Edge> edges = List.of(
                new Edge(vertices.get(1), vertices.get(0)),
                new Edge(vertices.get(2), vertices.get(0))
        );
        Graph g = new Graph(vertices, edges);
        ImmutableLinkedList<Vertex> lst = g.topoSortFunc();
        Cons<Vertex> lCons = (Cons<Vertex>) lst;
        assertEquals("source", lCons.head.value.substring(0, 6));
        lCons = (Cons<Vertex>) lCons.tail;
        assertEquals("source", lCons.head.value.substring(0, 6));
        lCons = (Cons<Vertex>) lCons.tail;
        assertEquals("sink", lCons.head.value);
        assertTrue(lCons.tail instanceof Nil);
    }

    @Test
    public void topoSortFuncOfUnconnectedGraphFindsAllVertices() {
        List<Vertex> vertices = List.of(
                new Vertex("sink"),
                new Vertex("source1"),
                new Vertex("source2")
        );
        List<Edge> edges = List.of(
                new Edge(vertices.get(1), vertices.get(0))
        );
        Graph g = new Graph(vertices, edges);
        ImmutableLinkedList<Vertex> topoRes = g.topoSortFunc();
        List<String> nameLst = new ArrayList<>();
        while (topoRes instanceof Cons) {
            Cons<Vertex> topoResCons = (Cons<Vertex>) topoRes;
            nameLst.add(topoResCons.head.value);
            topoRes = topoResCons.tail;
        }
        assertTrue(nameLst.containsAll(List.of("sink", "source1", "source2")));
    }

    @Test
    public void topoSortImpOfEmptyGraphYieldsEmptyList() {
        Graph g = new Graph(new ArrayList<>(), new ArrayList<>());
        assertEquals(0, g.topoSortImp().size());
    }

    @Test
    public void topoSortImpOfSingleVertexGraphYieldsThatVertex() {
        Graph g = new Graph(List.of(new Vertex("a")), new ArrayList<>());
        List<Vertex> lst = g.topoSortImp();
        assertEquals(List.of("a"), lst.stream().map(x -> x.value).collect(Collectors.toList()));
    }

    @Test
    public void topoSortImpOfTwoConnectedVerticesYieldsSourceFirst() {
        List<Vertex> vertices = List.of(new Vertex("sink"), new Vertex("source"));
        List<Edge> edges = List.of(
                new Edge(vertices.get(1), vertices.get(0))
        );
        Graph g = new Graph(vertices, edges);
        List<Vertex> lst = g.topoSortImp();
        assertEquals(List.of("source", "sink"), lst.stream().map(x -> x.value).collect(Collectors.toList()));
    }

    @Test
    public void topoSortImpOfThreeConnectedVerticesYieldsSourceFirst() {
        List<Vertex> vertices = List.of(
                new Vertex("sink"),
                new Vertex("intermediate"),
                new Vertex("source")
        );
        List<Edge> edges = List.of(
                new Edge(vertices.get(2), vertices.get(1)),
                new Edge(vertices.get(1), vertices.get(0))
        );
        Graph g = new Graph(vertices, edges);
        List<Vertex> lst = g.topoSortImp();
        assertEquals(List.of("source", "intermediate", "sink"), lst.stream().map(x -> x.value).collect(Collectors.toList()));
    }

    @Test
    public void topoSortImpOfTwoSourcesAndOneSinkYieldsSinkLast() {
        List<Vertex> vertices = List.of(
                new Vertex("sink"),
                new Vertex("source1"),
                new Vertex("source2")
        );
        List<Edge> edges = List.of(
                new Edge(vertices.get(1), vertices.get(0)),
                new Edge(vertices.get(2), vertices.get(0))
        );
        Graph g = new Graph(vertices, edges);
        List<Vertex> lst = g.topoSortImp();
        assertEquals("source", lst.get(0).value.substring(0, 6));
        assertEquals("source", lst.get(1).value.substring(0, 6));
        assertEquals("sink", lst.get(2).value);
    }

    @Test
    public void topoSortImpOfUnconnectedGraphFindsAllVertices() {
        List<Vertex> vertices = List.of(
                new Vertex("sink"),
                new Vertex("source1"),
                new Vertex("source2")
        );
        List<Edge> edges = List.of(
                new Edge(vertices.get(1), vertices.get(0))
        );
        Graph g = new Graph(vertices, edges);
        List<String> topoRes = g.topoSortImp().stream().map(x -> x.value).collect(Collectors.toList());
        assertTrue(topoRes.containsAll(List.of("sink", "source1", "source2")));
    }

    @Test
    public void mainMethodDoesNotThrowAnyExceptions() {
        main(new String[0]);
    }
}