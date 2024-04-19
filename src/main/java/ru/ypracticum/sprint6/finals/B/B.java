package ru.ypracticum.sprint6.finals.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;

public class B {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            StringTokenizer tokenizer;
            int[] input = readArr(reader);
            int vertexes = input[0];

            Graph graph = new Graph(vertexes);

            for (int i = 0; i < vertexes - 1; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                char[] arr = tokenizer.nextToken().toCharArray();
                for (int j = 0; j < arr.length; j++) {
                    graph.addEdge(i, j, arr[j]);
                }
            }

            for (int i = 0; i < graph.graph.size(); i++) {
                graph.dfs(i);
                if (graph.isCycle) {
                    break;
                }
            }
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
        private static final String NO = "NO";
        private static final String YES = "YES";
        private static final char B = 'B';
        private static final char R = 'R';
        private final Map<Integer, List<Integer>> graph;
        private final List<String> colors;
        private boolean isCycle;

        public Graph(int vertexes) {
            graph = new HashMap<>();

            for (int i = 0; i < vertexes; i++) {
                graph.put(i, new ArrayList<>());
            }

            isCycle = false;
            colors = new ArrayList<>(Collections.nCopies(vertexes, WHITE));
        }

        public void addEdge(int i, int j, char type) {
            int val = i + j + 1;
            if (type == B) {
                graph.get(i).add(val);
            } else if (type == R){
                graph.get(val).add(i);
            }
        }

        public void dfs(int startVertex) {
            Stack<Integer> stack = new Stack<>();
            stack.push(startVertex);

            while (!stack.isEmpty()) {
                Integer v = stack.pop();
                if (colors.get(v).equals(WHITE)) {
                    colors.set(v, GRAY);
                    stack.push(v);

                    for (int w : graph.get(v)) {
                        String color = colors.get(w);
                        if (color.equals(WHITE)) {
                            stack.push(w);
                        } else if (color.equals(GRAY)) {
                            isCycle = true;
                            break;
                        }
                    }
                } else if (colors.get(v).equals(GRAY)) {
                    colors.set(v, BLACK);
                }
            }
        }

        public void print() {
            if (isCycle) {
                System.out.println(NO);
            } else {
                System.out.println(YES);
            }
        }
    }
}
