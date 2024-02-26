package ru.ypracticum.sprint3.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class C {
    private static String isSubString(char[] s, char[] t) {
        int nextIndex = 0;
        int k = 1;
        for (int i = 0; i < s.length; i++) {
            for (int j = nextIndex; j < t.length; j++) {
                if (s[i] == t[j]) {
                    if (i == s.length - 1 && k == s.length) {
                        return "True";
                    }
                    k += 1;
                    nextIndex = j + 1;
                    break;
                }
            }
        }

        return "False";
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String s = reader.readLine();
            String t = reader.readLine();

            System.out.println(isSubString(s.toCharArray(), t.toCharArray()));
        }
    }
}
