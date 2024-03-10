package ru.ypracticum.sprint4.H;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

public class H {

    private static String isMapping(String s, String t) {
        if (s.length() != t.length()) {
            return "NO";
        }

        int len = s.length();

        Map<Character, Character> map = new HashMap<>();

        for (int i = 0; i < len; i++) {
            char sCh = s.charAt(i);
            char tCh = t.charAt(i);

            if (!map.containsKey(sCh)) {
                if (map.containsValue(tCh)) {
                    return "NO";
                }
                map.put(sCh, tCh);

            } else {
                if (map.get(sCh) != tCh) {
                    return "NO";
                }
            }
        }

        return "YES";
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            String s = reader.readLine();
            String t = reader.readLine();

            writer.write(isMapping(s, t));
        }
    }
}
