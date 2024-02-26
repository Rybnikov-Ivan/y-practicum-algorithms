package ru.ypracticum.sprint4.A;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class A {

    private static long getHash(long a, long m, String s) {
        long res = 0;

        int n = s.length();
        for (int i = 0; i < n; i++) {
            res = res * a % m + s.charAt(i);
        }

        return res % m;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            long a = readLong(reader);
            long m = readLong(reader);
            String s = reader.readLine();

            writer.write(String.valueOf(getHash(a, m, s)));
        }
    }

    private static long readLong(BufferedReader reader) throws IOException {
        return Long.parseLong(reader.readLine());
    }
}
