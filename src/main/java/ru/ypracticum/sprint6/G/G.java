package ru.ypracticum.sprint6.G;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class G {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

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
        private List<Integer> distance;
        private final boolean isOriented;

        List<String> colors;

        Comparator<Integer> comparator = Comparator.comparingInt(o -> o);

        public Graph(int vertexes, boolean isOriented) {
            this.isOriented = isOriented;

            adj = new TreeSet[vertexes];

            for (int i = 0; i < vertexes; i++) {
                adj[i] = new TreeSet<>(comparator);
            }

            distance = new ArrayList<>(Collections.nCopies(vertexes, 0));
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
            distance.set(startVertex, 0);

            while (!planned.isEmpty()) {
                Integer u = planned.poll();

                for (int v : adj[u]) {
                    if (colors.get(v).equals(WHITE)) {
                        distance.set(v, distance.get(u) + 1);
                        colors.set(v, GRAY);
                        planned.add(v);
                    }
                }
                colors.set(u, BLACK);
            }
        }

        public void print() {
            int res = 0;
            for (Integer integer : distance) {
                res = Math.max(res, integer);
            }
            System.out.println(res);
        }
    }
}
