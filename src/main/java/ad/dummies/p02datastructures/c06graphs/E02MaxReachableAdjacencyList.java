package ad.dummies.p02datastructures.c06graphs;

import java.util.*;
import java.util.stream.Collectors;

public class E02MaxReachableAdjacencyList {
    public static class Vertex {
        private String name;
        private List<Vertex> adjacent;
        public Vertex(String name, List<Vertex> adjacent) {
            this.name = name;
            this.adjacent = adjacent;
        }
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

    public static void main(String[] args) {
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
        System.out.printf(
                "g.maxReachable() = %s\n",
                vampFollowers.maxReachable().name
        );
    }
}