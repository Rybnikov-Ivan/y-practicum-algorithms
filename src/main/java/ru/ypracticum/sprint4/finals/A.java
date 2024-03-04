package ru.ypracticum.sprint4.finals;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class A {
    private static int n;
    private static int m;
    private static String[][] documents;
    private static List<Set<String>> requests;

    private static Map<String, Map<Integer, Integer>> buildSearchIndex() {
        Map<String, Map<Integer, Integer>> searchIndex = new HashMap<>();

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < documents[i].length; j++) {
                String word = documents[i][j];
                if (!searchIndex.containsKey(word)) {
                    searchIndex.put(word, new HashMap<>());
                    searchIndex.get(word).put(i, 1);
                } else {
                    Map<Integer, Integer> value = searchIndex.get(word);
                    value.put(i, value.getOrDefault(i, 0) + 1);
                }
            }
        }

        return searchIndex;
    }

    private static List<List<Integer>> getResponses(Map<String, Map<Integer, Integer>> searchIndex) {
        List<List<Integer>> responses = new ArrayList<>(m);

        Comparator<Map.Entry<Integer, Integer>> relevanceComparator = Comparator
                .comparing(Map.Entry<Integer, Integer>::getValue).reversed()
                .thenComparing(Map.Entry::getKey);

        for (Set<String> request : requests) {
            Map<Integer, Integer> map = new HashMap<>();

            for (String word : request) {
                if (searchIndex.containsKey(word)) {

                    Map<Integer, Integer> value = searchIndex.get(word);

                    for (Integer integer : value.keySet()) {
                        map.put(integer, map.getOrDefault(integer, 0) + value.get(integer));
                    }
                }
            }


            responses.add(map
                    .entrySet()
                    .stream()
                    .sorted(relevanceComparator)
                    .limit(5)
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList()));
        }
        return responses;
    }

    public static void main(String[] args) throws IOException {
        read();

        Map<String, Map<Integer, Integer>> searchIndex = buildSearchIndex();

        List<List<Integer>> responses = getResponses(searchIndex);

        write(responses);
    }

    private static void write(List<List<Integer>> responses) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            for (List<Integer> response : responses) {
                for (Integer value : response) {
                    writer.write(value + 1 + " ");
                }
                writer.newLine();
            }
        }
    }

    private static void read() throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            n = readInteger(reader);
            documents = new String[n][];
            for (int i = 0; i < n; i++) {
                documents[i] = readArr(reader);
            }

            m = readInteger(reader);
            requests = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                requests.add(new HashSet<>(List.of(readArr(reader))));
            }
        }
    }

    private static String[] readArr(BufferedReader reader) throws IOException {
        return reader.readLine().split(" ");
    }

    private static int readInteger(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }
}
