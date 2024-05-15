package ru.ypracticum.sprint8.H;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class H {

    private static String search(String pattern, StringBuilder line, String t) {
        for (int i = 0; i < line.length(); i++) {
            boolean flag = true;
            for (int j = 0; j < pattern.length(); j++) {
                if (i + j == line.length() || line.charAt(i + j) != pattern.charAt(j)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                StringBuilder start = new StringBuilder(line.substring(0, i));
                line = new StringBuilder(start).append(t).append(line.substring(i + pattern.length()));
                if (pattern.length() <= t.length())
                    i += t.length() - 1;
            }
        }

        return line.toString();
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = reader.readLine();
            String s = reader.readLine();
            String t = reader.readLine();

            System.out.println(search(s, new StringBuilder(line), t));
        }
    }
}
