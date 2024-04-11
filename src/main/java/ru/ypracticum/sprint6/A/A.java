package ru.ypracticum.sprint6.A;

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

public class A {

    private static int n;
    private static int m;
    private static final Map<Integer, List<Integer>> map = new HashMap<>();

    private static void change(int n, int m) {
        if (map.containsKey(n)) {
            map.get(n).add(m);
        } else {
            map.put(n, new ArrayList<>());
            map.get(n).add(m);
        }
    }

    public static void main(String[] args) throws IOException {
        read();

        write();
    }

    private static void write() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))){
            for (int i = 1; i <= n; i++) {
                if (map.containsKey(i)) {
                    List<Integer> edges = map.get(i);
                    int size = edges.size();
                    writer.write(size + " ");
                    Collections.sort(edges);
                    for (Integer edge : edges) {
                        writer.write(edge + " ");
                    }
                } else {
                    writer.write(String.valueOf(0));
                }

                writer.newLine();
            }
        }
    }
    private static void read() throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            int[] input = readArr(reader);
            n = input[0];
            m = input[1];
            for (int i = 0; i < m; i++) {
                int[] pair = readArr(reader);
                change(pair[0], pair[1]);
            }
        }
    }

    private static int[] readArr(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}
