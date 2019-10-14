package ad.dummies.p02datastructures.c06graphs;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static ad.dummies.p02datastructures.c06graphs.E02MaxReachableAdjacencyList.*;

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
 * @see E02MaxReachableAdjacencyList
 */
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
class E02MaxReachableAdjacencyListTest {

    @Test
    @SuppressWarnings("unchecked")
    public void maxReachableFindsMostInfluentialVampire() {
        String[] names = {"Vlad", "Brunhilde", "Olga", "Rüdiger", "Hildegard", "Anna"};
        List<Vertex>[] adjLists = new List[names.length];
        for(int i = 0; i < names.length; i++) { adjLists[i] = new ArrayList<>(); }
        List<Vertex> vertices = new ArrayList<>();
        for(int i = 0; i < names.length; i++) {
            vertices.add(new Vertex(names[i], adjLists[i]));
        }
        adjLists[0].add(vertices.get(2));
        adjLists[0].add(vertices.get(4));
        adjLists[1].add(vertices.get(3));
        adjLists[2].add(vertices.get(4));
        adjLists[5].add(vertices.get(3));
        adjLists[5].add(vertices.get(4));
        Graph vampFollowers = new Graph(vertices);
        assertEquals("Vlad", vampFollowers.maxReachable().name);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void maxReachableYieldsCorrectVertexInCyclicGraph() {
        int n = 3;
        List<Vertex>[] adjLists = new List[n];
        for(int i = 0; i < n; i++) { adjLists[i] = new ArrayList<>(); }
        List<Vertex> vertices = List.of(
                new Vertex("a", adjLists[0]),
                new Vertex("b", adjLists[1]),
                new Vertex("c", adjLists[2])
        );
        adjLists[0].add(vertices.get(1));
        adjLists[1].add(vertices.get(2));
        adjLists[2].add(vertices.get(0));
        Graph cyclic = new Graph(vertices);
        assertTrue(Set.of("a", "b", "c").contains(cyclic.maxReachable().name));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void maxReachableYieldsCorrectVertexInUnconnectedGraph() {
        int n = 4;
        List<Vertex>[] adjLists = new List[n];
        for(int i = 0; i < n; i++) { adjLists[i] = new ArrayList<>(); }
        List<Vertex> vertices = List.of(
                new Vertex("1a", adjLists[0]),
                new Vertex("1b", adjLists[1]),
                new Vertex("2a", adjLists[2]),
                new Vertex("2b", adjLists[3])
        );
        adjLists[0].add(vertices.get(1));
        adjLists[3].add(vertices.get(2));
        Graph unconnected = new Graph(vertices);
        assertTrue(Set.of("1a", "2b").contains(unconnected.maxReachable().name));
    }

    @Test
    public void mainMethodDoesNotThrowAnyExceptions() {
        main(new String[0]);
    }
}