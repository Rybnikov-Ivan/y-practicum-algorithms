package ru.ypracticum.sprint4.F;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class F {

    private static List<List<Integer>> getIndexesOfAnagram(String[] arr) {

        Map<String, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            String sortedString = getSortedString(arr[i].toCharArray());

            if (map.containsKey(sortedString)) {
                map.get(sortedString).add(i);
            } else {
                map.put(sortedString, new ArrayList<>());
                map.get(sortedString).add(i);
            }
        }

        return new ArrayList<>(map.values());
    }

    private static String getSortedString(char[] chars) {
        Arrays.sort(chars);
        StringBuilder sb = new StringBuilder();
        for (char aChar : chars) {
            sb.append(aChar);
        }

        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int n = readInteger(reader);
            String[] arr = reader.readLine().split(" ");

            List<List<Integer>> result = getIndexesOfAnagram(arr);
            result.sort(Comparator.comparing(o -> o.get(0)));

            for (List<Integer> integers : result) {
                for (Integer integer : integers) {
                    writer.write(integer + " ");
                }

                writer.newLine();
            }

        }
    }

    private static int readInteger(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }
}
