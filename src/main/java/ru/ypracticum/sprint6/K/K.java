package ru.ypracticum.sprint6.K;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class K {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

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

            for (int i = 1; i <= vertexes; i++) {
                graph.dijkstra(i);
                for (int j = 1; j <= vertexes; j++) {
                    int val = (graph.distance.get(j) == Integer.MAX_VALUE) ? -1 : graph.distance.get(j);
                    writer.write(val + " ");
                }
                writer.newLine();
            }
        }
    }

    private static int[] readArr(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    private static class Graph {
        private Map<Integer, List<Pair>> adj;
        private List<Integer> distance;
        private List<Boolean> visited;
        private List<Integer> previous;
        private final int vertexes;

        public Graph(int vertexes) {
            adj = new HashMap<>();
            this.vertexes = vertexes;

            this.distance = new ArrayList<>(Collections.nCopies(vertexes, 0));
            this.visited = new ArrayList<>(Collections.nCopies(vertexes, false));
            this.previous = new ArrayList<>(Collections.nCopies(vertexes, null));
        }

        public void addEdge(int from, int to, int weight) {
            if (!adj.containsKey(from)) {
                adj.put(from, new ArrayList<>());
            }
            adj.get(from).add(new Pair(to, weight));

            if (!adj.containsKey(to)) {
                adj.put(to, new ArrayList<>());
            }
            adj.get(to).add(new Pair(from, weight));
        }

        public void dijkstra(int s) {
            for (int v = 1; v < vertexes; v++) {
                distance.set(v, Integer.MAX_VALUE);
                previous.set(v, null);
                visited.set(v, false);
            }

            distance.set(s, 0);

            while (true) {
                Integer u = getMinDist();

                if (u == null || distance.get(u) == Integer.MAX_VALUE) {
                    break;
                }

                visited.set(u, true);

                List<Pair> pairs = adj.get(u);
                if (pairs != null) {
                    for (Pair pair : pairs) {
                        relax(u, pair);
                    }
                }
            }
        }

        private Integer getMinDist() {
            Integer currMin = Integer.MAX_VALUE;
            Integer currMinVertex = null;

            for (int v = 1; v < vertexes; v++) {
                if (!visited.get(v) && distance.get(v) < currMin) {
                    currMin = distance.get(v);
                    currMinVertex = v;
                }
            }

            return currMinVertex;
        }

        private void relax(int u, Pair pair) {
            if (distance.get(pair.v) > distance.get(u) + pair.weight) {
                distance.set(pair.v, distance.get(u) + pair.weight);
                previous.set(pair.v, u);
            }
        }
    }

    private static class Pair {
        int v;
        int weight;

        public Pair(int v, int weight) {
            this.v = v;
            this.weight = weight;
        }
    }
}
