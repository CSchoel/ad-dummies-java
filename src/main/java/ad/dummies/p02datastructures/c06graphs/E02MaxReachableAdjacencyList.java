package ad.dummies.p02datastructures.c06graphs;

import java.util.*;

public class E02MaxReachableAdjacencyList {
    public static class Vertex {
        private String name;
        private List<Vertex> adjacent;
    }

    public static class Graph {
        public final List<Vertex> vertices;
        public Graph(List<Vertex> vertices) {
            this.vertices = vertices;
        }

        public List<Vertex> reachList(Vertex v) {
            return reachList(v, new ArrayList<>());
        }

        public List<Vertex> reachList(Vertex v, List<Vertex> visited) {
            visited.add(v);
            List<Vertex> result = new ArrayList<>();
            for (Vertex w: v.adjacent) {
                if (!visited.contains(w)) {
                    result.addAll(reachList(w));
                }
            }
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
                    .map(v -> new ScoredVertex(v, reachList(v).size()))
                    .max(Comparator.comparing(v -> v.score))
                    .map(v -> v.vertex).orElse(null);
        }
    }
}
