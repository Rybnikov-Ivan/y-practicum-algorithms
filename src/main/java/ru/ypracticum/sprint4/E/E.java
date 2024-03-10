package ru.ypracticum.sprint4.E;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

public class E {
    private static int getMaxSubstring(char[] chars) {
        Map<Character, Integer> map = new HashMap<>();
        int max = 0;

        for (int left = 0; left < chars.length; left++) {
            map.put(chars[left], left);

            for (int right = left + 1; right < chars.length; right++) {
                char key = chars[right];
                if (map.containsKey(key)) {
                    max = Math.max(max, map.size());
                    left = map.get(key);
                    map.clear();

                    break;
                }
                map.put(key, right);
            }
        }

        return Math.max(max, map.size());
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            String line = reader.readLine();
            writer.write(String.valueOf(getMaxSubstring(line.toCharArray())));
        }
    }
}
