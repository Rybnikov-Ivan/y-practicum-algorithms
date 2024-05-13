package ru.ypracticum.sprint8.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

public class E {
    private static String input;
    private static Map<Integer, String> map;

    private static String getStringAfterPuts() {
        StringBuilder res = new StringBuilder();
        int prev = 0;
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            res.append(input, prev, entry.getKey());
            res.append(entry.getValue());

            prev = entry.getKey();
        }

        res.append(input.substring(prev));

        return res.toString();
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            input = reader.readLine();
            int n = Integer.parseInt(reader.readLine());
            map = new TreeMap<>();
            for (int i = 0; i < n; i++) {
                String[] arr = reader.readLine().split(" ");
                map.put(Integer.parseInt(arr[1]), arr[0]);
            }

            System.out.println(getStringAfterPuts());
        }
    }
}
