package ad.dummies.p02datastructures.c06graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class E03TopologicalSort {
    public interface ImmutableLinkedList<E> { }
    public static class Nil<E> implements ImmutableLinkedList<E> { }
    public static class Cons<E> implements ImmutableLinkedList<E> {
        public final E head;
        public final ImmutableLinkedList<E> tail;
        public Cons(E head, ImmutableLinkedList<E> tail) {
            this.head = head;
            this.tail = tail;
        }
    }

    public static class Vertex {
        public final String value;
        public Vertex(String value) {
            this.value = value;
        }
    }
    public static class Edge {
        public final Vertex from;
        public final Vertex to;
        public Edge(Vertex from, Vertex to) {
            this.from = from;
            this.to = to;
        }
    }

    public static class Graph {
        private final List<Vertex> vertices;
        private final List<Edge> edges;
        public Graph(List<Vertex> vertices, List<Edge> edges) {
            this.vertices = vertices;
            this.edges = edges;
        }
        public ImmutableLinkedList<Vertex> topoSortFunc() {
            if (vertices.size() == 0) {
                return new Nil<>();
            }
            Vertex v = findSource();
            return new Cons<>(v, withoutSource(v).topoSortFunc());
        }

        private Graph withoutSource(Vertex v) {
            assert edges.stream().noneMatch(e -> e.to == v): "v must be a source";
            return new Graph(
                    vertices.stream().filter(w -> w != v).collect(Collectors.toList()),
                    edges.stream().filter(e -> e.from != v).collect(Collectors.toList())
            );
        }

        private Vertex findSource() {
            for(Vertex v: vertices) {
                if (incomingEdges(v).size() == 0) {
                    return v;
                }
            }
            return null; // no sources => we have a circular graph
        }

        public List<Edge> incomingEdges(Vertex v) {
            // FIXME: error in set notation in book
            // wrong:   {(x, v) | x \in V}
            // correct: {(x, v) \in E | x \in V}
            return edges.stream().filter(e -> e.to == v).collect(Collectors.toList());
        }
        public boolean[][] buildAdjacencyMatrix() {
            boolean[][] adjMatrix = new boolean[vertices.size()][vertices.size()];
            // FIXME: book version is unecessarily complicated with additional loop
            for (Edge e: edges) {
                adjMatrix[vertices.indexOf(e.from)][vertices.indexOf(e.to)] = true;
            }
            return adjMatrix;
        }

        private static boolean columnIsFalse(boolean[][] adjMatrix, int col) {
            for (int x = 0; x < adjMatrix.length; x++) {
                if (adjMatrix[x][col]) {
                    return false;
                }
            }
            return true;
        }
        private static void setRowToFalse(boolean[][] adjMatrix, int row) {
            for(int j = 0; j < adjMatrix[row].length; j++) {
                adjMatrix[row][j] = false;
            }
        }
        private static boolean anyTrue(boolean[] ar) {
            for(boolean b: ar) {
                if(b) return true;
            }
            return false;
        }

        public List<Vertex> topoSortImp() {
            boolean[][] adjMatrix = buildAdjacencyMatrix();
            boolean[] vRemaining = new boolean[vertices.size()];
            Arrays.fill(vRemaining, true);
            List<Vertex> result = new ArrayList<>();
            while (anyTrue(vRemaining)) {
                for(int v = 0; v < vertices.size(); v++) {
                    if (!vRemaining[v]) { continue; }
                    if (!columnIsFalse(adjMatrix, v)) { continue; }
                    vRemaining[v] = false;
                    result.add(vertices.get(v));
                    setRowToFalse(adjMatrix, v);
                }
            }
            return result;
        }
    }

    public static void main(String[] args) {
        String[] names = {"glasses", "undershirt", "shirt", "watch", "suspenders", "jacket", "socks", "underpants", "pants", "shoes"};
        List<Vertex> vertices = Arrays.stream(names).map(Vertex::new).collect(Collectors.toList());
        List<Edge> edges = List.of(
                new Edge(vertices.get(1), vertices.get(0)),
                new Edge(vertices.get(1), vertices.get(2)),
                new Edge(vertices.get(2), vertices.get(4)),
                new Edge(vertices.get(2), vertices.get(5)),
                new Edge(vertices.get(4), vertices.get(5)),
                new Edge(vertices.get(6), vertices.get(9)),
                new Edge(vertices.get(7), vertices.get(8)),
                new Edge(vertices.get(8), vertices.get(9))
        );
        Graph clothing = new Graph(vertices, edges);
        ImmutableLinkedList<Vertex> topo = clothing.topoSortFunc();
        List<String> topoLst = new ArrayList<>();
        while(topo instanceof Cons) {
            Cons<Vertex> topoCons = (Cons<Vertex>) topo;
            topoLst.add(topoCons.head.value);
            topo = topoCons.tail;
        }
        System.out.printf("g.topoSortFunc() = %s\n", topoLst);
        System.out.printf(" g.topoSortImp() = %s\n", clothing.topoSortImp().stream().map(v -> v.value).collect(Collectors.toList()));
    }
}
