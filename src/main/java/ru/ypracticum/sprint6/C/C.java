package ru.ypracticum.sprint6.C;

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

public class C {
    private static final String WHITE = "white";
    private static final String GRAY = "gray";
    private static final String BLACK = "black";

    private static int s;
    private static final List<Integer> edges = new ArrayList<>();
    private static final Map<Integer, List<Integer>> map = new HashMap<>();
    private static final List<String> colors = new ArrayList<>();

    private static void DFS(int startVertex) {
        Stack<Integer> stack = new Stack<>();
        stack.push(startVertex);

        while (!stack.isEmpty()) {

            int v = stack.pop();
            if (colors.get(v).equals(WHITE)) {
                colors.set(v, GRAY);
                edges.add(v);
                stack.push(v);

                List<Integer> outgoingEdges = map.get(v);
                if (outgoingEdges != null) {
                    Collections.sort(outgoingEdges);
                    Collections.reverse(outgoingEdges);
                    for (Integer w : outgoingEdges) {
                        if (colors.get(w).equals(WHITE)) {
                            stack.push(w);
                        }
                    }
                }
            } else if (colors.get(v).equals(GRAY)) {
                colors.set(v, BLACK);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        read();

        for (String color : colors) {
            if (color.equals(WHITE)) {
                DFS(s);
            }
        }

        write();
    }

    private static void write() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))){
            for (Integer edge : edges) {
                writer.write(edge + " ");
            }
        }
    }

    private static void read() throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            int[] input = readArr(reader);
            int n = input[0];
            int m = input[1];

            for (int i = 0; i < m; i++) {
                int[] pair = readArr(reader);

                if (map.containsKey(pair[0])) {
                    map.get(pair[0]).add(pair[1]);
                } else {
                    map.put(pair[0], new ArrayList<>());
                    map.get(pair[0]).add(pair[1]);
                }

                if (map.containsKey(pair[1])) {
                    map.get(pair[1]).add(pair[0]);
                } else {
                    map.put(pair[1], new ArrayList<>());
                    map.get(pair[1]).add(pair[0]);
                }
            }

            s = Integer.parseInt(reader.readLine());
            for (int i = 0; i <= n; i++) {
                colors.add(WHITE);
            }
        }
    }

    private static int[] readArr(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}
