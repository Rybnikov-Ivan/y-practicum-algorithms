package ru.ypracticum.sprint1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class E {

    private static String getLongestWord(String text) {
        int max = Integer.MIN_VALUE;
        String maxWord = "";
        for (String s : text.split(" ")) {
            if (s.length() > max) {
                max = s.length();
                maxWord = s;
            }
        }
        return maxWord;
    }
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int textLength = readInt(reader);
            String text = reader.readLine();
            String longestWord = getLongestWord(text);
            System.out.println(longestWord);
            System.out.println(longestWord.length());
        }

    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }
}
