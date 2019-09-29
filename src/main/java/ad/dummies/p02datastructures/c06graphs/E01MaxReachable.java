package ad.dummies.p02datastructures.c06graphs;

import java.util.*;

public class E01MaxReachable {
    public static enum VisitState { VISITED, UNVISITED }
    public static class Vertex {
        public final int value;
        public VisitState mark;
        public Vertex(int value) {
            this.value = value;
            mark = VisitState.UNVISITED;
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
        public final List<Vertex> vertices;
        public final List<Edge> edges;
        public Graph(List<Vertex> vertices, List<Edge> edges) {
            this.vertices = vertices;
            this.edges = edges;
        }

        public void trav() {
            for(Vertex v: vertices) {
                v.mark = VisitState.UNVISITED;
            }
            for(Vertex v: vertices) {
                if (v.mark == VisitState.UNVISITED) {
                    reach(v);
                }
            }
        }
        public void reach(Vertex v) {
            v.mark = VisitState.VISITED;
            edges.stream().filter(e -> e.from == v).map(e -> e.to).forEach(w -> {
                if (w.mark == VisitState.UNVISITED) {
                    reach(w);
                }
            });
        }
        public List<Vertex> reachList(Vertex v) {
            v.mark = VisitState.VISITED;
            List<Vertex> result = new ArrayList<>();
            result.add(v);
            edges.stream().filter(e -> e.from == v).map(e -> e.to).forEach(w -> {
                if (w.mark == VisitState.UNVISITED) {
                    result.addAll(reachList(w));
                }
            });
            return result;
        }
        public void travSet() {
            Set<Vertex> visited = new HashSet<>();
            for(Vertex v: vertices) {
                if (!visited.contains(v)) {
                    reachSet(v, visited);
                }
            }
        }

        public List<Vertex> reachSet(Vertex v) {
            return reachSet(v, new HashSet<>());
        }

        public List<Vertex> reachSet(Vertex v, Set<Vertex> visited) {
            visited.add(v);
            List<Vertex> result = new ArrayList<>();
            edges.stream().filter(e -> e.from == v).map(e -> e.to).forEach(w -> {
                if (!visited.contains(w)) {
                    result.addAll(reachList(w));
                }
            });
            return result;
        }

        public static class ScoredVertex {
            public final Vertex vertex;
            public final int score;
            public ScoredVertex(Vertex vertex, int score) {
                this.vertex = vertex;
                this.score = score;
            }
        }

        public Vertex maxReachable() {
            return vertices.stream()
                    .map(v -> new ScoredVertex(v, reachSet(v).size()))
                    .max(Comparator.comparing(v -> v.score))
                    .map(v -> v.vertex).orElse(null);
        }
    }
}
