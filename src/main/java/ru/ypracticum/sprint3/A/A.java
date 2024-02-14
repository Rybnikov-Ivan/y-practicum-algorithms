package ru.ypracticum.sprint3.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class A {
    private static void bracketSequenceGenerator(int n, String str, int left, int right) {
        if (str.length() == 2 * n) {
            System.out.println(str);
        } else {
            if (left < n) {
                bracketSequenceGenerator(n, str + "(", left + 1, right);
            }
            if (right < left) {
                bracketSequenceGenerator(n, str + ")", left, right + 1);
            }
        }
    }
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            int n = readInt(reader);
            bracketSequenceGenerator(n, "", 0, 0);
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }
}
