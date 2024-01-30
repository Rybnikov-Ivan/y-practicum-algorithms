package ru.ypracticum.sprint1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class L {
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    private static char getExcessiveLetter(String s, String t) {
        char[] chars = new char[26];

        if (s.length() > t.length()) {
            setCharsMaxLength(s, chars);
            setCharsMinLength(t, chars);
        } else {
            setCharsMaxLength(t, chars);
            setCharsMinLength(s, chars);
        }

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == 1) {
                return ALPHABET.charAt(i);
            }
        }

        return '1';
    }

    private static void setCharsMaxLength(String maxString, char[] chars) {
        for (int i = 0; i < maxString.length(); i++) {
            chars[maxString.charAt(i) - 97] += 1;
        }
    }

    private static void setCharsMinLength(String minString, char[] chars) {
        for (int i = 0; i < minString.length(); i++) {
            chars[minString.charAt(i) - 97] -= 1;
        }
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String s = reader.readLine();
            String t = reader.readLine();
            System.out.println(getExcessiveLetter(s, t));

        }
    }
}
