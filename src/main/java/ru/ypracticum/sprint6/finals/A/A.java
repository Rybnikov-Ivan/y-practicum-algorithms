package ru.ypracticum.sprint6.finals.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class A {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            StringTokenizer tokenizer;
            int[] input = readArr(reader);
            int vertexes = input[0];
            int edges = input[1];

            Graph graph = new Graph(vertexes + 1);

            for (int i = 0; i < edges; i++) {
                tokenizer = new StringTokenizer(reader.readLine());

                int v = Integer.parseInt(tokenizer.nextToken());
                int u = Integer.parseInt(tokenizer.nextToken());
                int weight = Integer.parseInt(tokenizer.nextToken());
                graph.addEdge(v, u, weight);
            }

            graph.findMST();
            graph.print();
        }
    }

    private static int[] readArr(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    private static class Graph {
        private static final String NO_TREE = "Oops! I did it again";
        private final Map<Integer, List<Vertex>> graph;
        private final List<Vertex> MST; // Максимальное остовное дерево
        private final List<Boolean> notAdded; // Множество вершин, ещё не добавленных в остов.
        private final Queue<Vertex> edges; // Массив рёбер, исходящих из остовного дерева.

        public Graph(int vertexes) {
            graph = new HashMap<>();

            for (int i = 1; i <= vertexes; i++) {
                graph.put(i, new ArrayList<>());
            }

            this.MST = new ArrayList<>();
            this.notAdded = new ArrayList<>(Collections.nCopies(vertexes, false));

            Comparator<Vertex> comparator = Comparator.comparing(Vertex::getWeight).reversed();
            this.edges = new PriorityQueue<>(comparator);
        }

        public void addEdge(int from, int to, int weight) {
            graph.get(from).add(new Vertex(to, weight));
            graph.get(to).add(new Vertex(from, weight));
        }

        public void findMST() {
            edges.add(new Vertex(1, 0));
            while (!edges.isEmpty()) {
                Vertex maximum = getMaximum();
                int v = maximum.getV();
                if (notAdded.get(v)) {
                    continue;
                }

                int weight = maximum.getWeight();
                MST.add(new Vertex(v, weight));

                notAdded.set(v, true);
                for (Vertex vertex : graph.get(v)) {
                    if (!notAdded.get(vertex.v)) {
                        edges.add(new Vertex(vertex.getV(), vertex.getWeight()));
                    }
                }
            }
        }

        private Vertex getMaximum() {
            return edges.poll();
        }

        public void print() {
            int countVertex = graph.size();
            long countVisited = notAdded.stream()
                    .filter(o -> o)
                    .count();

            if (countVertex - 1 != countVisited) {
                System.out.println(NO_TREE);
            } else {
                int sum = MST.stream().mapToInt(Vertex::getWeight).sum();
                System.out.println(sum);
            }
        }
    }

    private static class Vertex {
        int v;
        int weight;

        public Vertex(int v, int weight) {
            this.v = v;
            this.weight = weight;
        }

        public int getV() {
            return v;
        }

        public int getWeight() {
            return weight;
        }
    }
}
