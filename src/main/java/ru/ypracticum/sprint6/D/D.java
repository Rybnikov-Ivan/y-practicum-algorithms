package ru.ypracticum.sprint6.D;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class D {
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

            int start = Integer.parseInt(reader.readLine());

            graph.BFS(start);
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
        List<Integer> path;

        List<String> colors;

        Comparator<Integer> comparator = Comparator.comparingInt(o -> o);

        public Graph(int vertexes, boolean isOriented) {
            this.isOriented = isOriented;

            adj = new TreeSet[vertexes];

            for (int i = 0; i < vertexes; i++) {
                adj[i] = new TreeSet<>(comparator);
            }

            path = new ArrayList<>();
            colors = new ArrayList<>(Collections.nCopies(vertexes, WHITE));
        }

        public void addEdge(int from, int to) {
            adj[from].add(to);
            if (!isOriented) {
                adj[to].add(from);
            }
        }

        public void BFS(int startVertex) {
            Queue<Integer> planned = new LinkedList<>();
            planned.add(startVertex);
            colors.set(startVertex, GRAY);
            path.add(startVertex);

            while (!planned.isEmpty()) {
                Integer u = planned.poll();

                for (int v : adj[u]) {
                    if (colors.get(v).equals(WHITE)) {
                        colors.set(v, GRAY);
                        planned.add(v);
                        path.add(v);
                    }
                }
                colors.set(u, BLACK);
            }
        }


        public void print() {
            StringBuilder sb = new StringBuilder();

            for (Integer integer : path) {
                sb.append(integer).append(" ");
            }

            System.out.println(sb);
        }
    }
}
