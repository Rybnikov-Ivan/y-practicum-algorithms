package ru.ypracticum.sprint4.J;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class J {

    private static Set<List<Long>> getResult(int s, long[] arr) {
        int len = arr.length;
        Map<Long, List<Long>> history = new HashMap<>();
        Arrays.sort(arr);

        Set<List<Long>> fours = new TreeSet<>(((o1, o2) -> {
            for (int i = 0; i < o1.size(); i++) {
                int compare = Long.compare(o1.get(i), o2.get(i));
                if (compare != 0)
                    return compare;
            }
            return 0;
        }));

        for (int i = 1; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                long target = s - arr[i] - arr[j];
                if (history.containsKey(target)) {
                    List<Long> pair = history.get(target);
                    List<Long> four = List.of(pair.get(0), pair.get(1), arr[i], arr[j]);

                    fours.add(four);
                }
            }

            for (int j = 0; j < i; j++) {
                long sum = arr[i] + arr[j];
                history.put(sum, List.of((long) arr[j], (long) arr[i]));
            }
        }

        return fours;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int n = readInteger(reader);
            if (n == 0) {
                writer.write(String.valueOf(0));
                return;
            }
            int s = readInteger(reader);
            long[] arr = readArr(reader);


            Set<List<Long>> result = getResult(s, arr);
            writer.write(String.valueOf(result.size()));
            writer.newLine();

            for (List<Long> values : result) {
                for (long value : values) {
                    writer.write(value + " ");
                }
                writer.newLine();
            }
        }
    }

    private static int readInteger(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static long[] readArr(BufferedReader reader) throws IOException {
        String[] arr = reader.readLine().split(" ");
        return Arrays.stream(arr).mapToLong(elem -> Long.parseLong(elem)).toArray();
    }
}
