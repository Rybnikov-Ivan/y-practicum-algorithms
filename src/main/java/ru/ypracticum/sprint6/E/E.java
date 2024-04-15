package ru.ypracticum.sprint6.E;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class E {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            StringTokenizer tokenizer;
            int[] input = readArr(reader);
            int vertexes = input[0];
            int edges = input[1];

            Graph graph = new Graph(vertexes + 1, false);

            for (int i = 0; i < edges; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                graph.addEdge(Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken()));
            }

            for (int i = 1; i <= vertexes; i++) {
                if (graph.colors.get(i).equals(-1)) {
                    graph.DFS(i);
                    graph.componentCount += 1;
                }
            }

            writer.write(String.valueOf(graph.componentCount - 1));
            writer.newLine();
            int size = graph.colors.size();

            Map<Integer, List<Integer>> map = new TreeMap<>();
            for (int i = 1; i < size; i++) {
                Integer color = graph.colors.get(i);
                if (map.containsKey(color)) {
                    map.get(color).add(i);
                } else {
                    map.put(color, new ArrayList<>());
                    map.get(color).add(i);
                }
            }

            for (List<Integer> value : map.values()) {
                for (Integer integer : value) {
                    writer.write(integer + " ");
                }
                writer.newLine();
            }
        }
    }

    private static int[] readArr(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    private static class Graph {
        private final TreeSet<Integer>[] adj;
        private final boolean isOriented;
        private int componentCount = 1;

        List<Integer> colors;

        Comparator<Integer> comparator = Comparator.comparingInt(o -> o);

        public Graph(int vertexes, boolean isOriented) {
            this.isOriented = isOriented;

            adj = new TreeSet[vertexes];

            for (int i = 0; i < vertexes; i++) {
                adj[i] = new TreeSet<>(comparator);
            }


            colors = new ArrayList<>(Collections.nCopies(vertexes, -1));
        }

        public void addEdge(int from, int to) {
            adj[from].add(to);
            if (!isOriented) {
                adj[to].add(from);
            }
        }

        public void DFS(int v) {
            colors.set(v, componentCount);

            for (int w : adj[v]) {
                if (colors.get(w).equals(-1)) {
                    DFS(w);
                }
            }
            colors.set(v, componentCount);
        }
    }
}
