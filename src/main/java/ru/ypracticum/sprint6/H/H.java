package ru.ypracticum.sprint6.H;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

public class H {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            int[] input = readArr(reader);
            int n = input[0];
            int m = input[1];

            Graph graph = new Graph(n + 1, true);

            for (int i = 0; i < m; i++) {
                int[] pair = readArr(reader);
                graph.addEdge(pair[0], pair[1]);
            }

            graph.DFS(1);
            graph.print();
        }
    }

    private static int[] readArr(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    private static class Graph {
        private static final String WHITE = "white";
        private static final String GRAY = "gray";
        private static final String BLACK = "black";
        private final TreeSet<Integer>[] adj;
        private final boolean isOriented;
        private int time = 0;

        List<String> colors;
        List<Integer> entry;
        List<Integer> leave;

        Comparator<Integer> comparator = Comparator.comparingInt(o -> o);

        public Graph(int vertexes, boolean isOriented) {
            this.isOriented = isOriented;

            adj = new TreeSet[vertexes];

            for (int i = 0; i < vertexes; i++) {
                adj[i] = new TreeSet<>(comparator);
            }

            colors = new ArrayList<>(Collections.nCopies(vertexes, WHITE));
            entry = new ArrayList<>(Collections.nCopies(vertexes, null));
            leave = new ArrayList<>(Collections.nCopies(vertexes, null));
        }

        public void addEdge(int from, int to) {
            adj[from].add(to);
            if (!isOriented) {
                adj[to].add(from);
            }
        }

        public void DFS(int v) {
            time += 1;
            entry.set(v, time);
            colors.set(v, GRAY);

            for (int w : adj[v]) {
                if (colors.get(w).equals(WHITE)) {
                    DFS(w);
                }
            }
            time += 1;
            leave.set(v, time);
            colors.set(v, BLACK);
        }

        public void print() {
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i < entry.size(); i++) {
                int in = entry.get(i);
                int out = leave.get(i);

                sb.append(in - 1).append(" ").append(out - 1);
                sb.append(System.lineSeparator());
            }

            System.out.println(sb);
        }
    }
}
