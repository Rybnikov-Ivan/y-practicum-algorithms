package ru.ypracticum.sprint3.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

public class B {
    private static final Map<Integer, char[]> map = Map.of(
            2, new char[]{'a', 'b', 'c'},
            3, new char[]{'d', 'e', 'f'},
            4, new char[]{'g', 'h', 'i'},
            5, new char[]{'j', 'k', 'l'},
            6, new char[]{'m', 'n', 'o'},
            7, new char[]{'p', 'q', 'r', 's'},
            8, new char[]{'t', 'u', 'v'},
            9, new char[]{'w', 'x', 'y', 'z'}
    );

    private static void sequenceGenerator(int[] arr, String result, int index) {
        if (result.length() == arr.length) {
            System.out.print(result + " ");
        } else {
            char[] chars = map.get(arr[index]);
            for (char aChar : chars) {
                sequenceGenerator(arr, result + aChar, index + 1);
            }
        }
    }
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = reader.readLine();
            int[] arr = new int[line.length()];
            for (int i = 0; i < line.length(); i++) {
                arr[i] = Integer.parseInt(String.valueOf(line.charAt(i)));
            }

            sequenceGenerator(arr, "", 0);
        }
    }
}
