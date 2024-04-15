package ru.ypracticum.sprint6.J;

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
import java.util.Stack;
import java.util.StringTokenizer;

public class J {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            StringTokenizer tokenizer;
            int[] input = readArr(reader);
            int n = input[0];
            int m = input[1];

            Graph graph = new Graph(n + 1);

            for (int i = 0; i < m; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                graph.addEdge(Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken()));
            }

            for (int i = 1; i <= n; i++) {
                if (graph.colors.get(i).equals(Graph.WHITE)) {
                    graph.DFS(i);
                }
            }

            while (!graph.stack.isEmpty()) {
                writer.write(graph.stack.pop() + " ");
            }
        }
    }

    private static int[] readArr(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    private static class Graph {
        private static final String WHITE = "white";
        private static final String GRAY = "gray";
        private static final String BLACK = "black";
        private final Map<Integer, List<Integer>> adj;

        List<String> colors;
        Stack<Integer> stack = new Stack<>();

        public Graph(int vertexes) {

            adj = new HashMap<>();
            colors = new ArrayList<>(Collections.nCopies(vertexes, WHITE));
        }

        public void addEdge(int from, int to) {
            if (adj.containsKey(from)) {
                adj.get(from).add(to);
            } else {
                adj.put(from, new ArrayList<>());
                adj.get(from).add(to);
            }
        }

        public void DFS(int v) {
            colors.set(v, GRAY);
            List<Integer> outEdges = adj.get(v);
            if (outEdges != null) {
                for (int w : outEdges) {
                    if (colors.get(w).equals(WHITE)) {
                        DFS(w);
                    }
                }
            }
            colors.set(v, BLACK);
            stack.add(v);
        }
    }
}
