package ru.ypracticum.sprint4.C;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class C {
    private static long[] prefHash;
    private static long[] pow;

    private static void getHash(long a, long m, String s) {
        int n = s.length();
        prefHash = new long[n + 1];
        pow = new long[n + 1];

        pow[0] = 1;

        for (int i = 1; i < n + 1; i++) {
            prefHash[i] = (prefHash[i - 1] * a + s.charAt(i - 1)) % m;
            pow[i] = pow[i - 1] * a % m;
        }
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int a = readInteger(reader);
            int m = readInteger(reader);
            String s = reader.readLine();
            getHash(a, m, s);
            int t = readInteger(reader);

            for (int i = 0; i < t; i++) {
                int[] indexes = readArr(reader);
                int start = indexes[0];
                int end = indexes[1];
                long res = (prefHash[end] - prefHash[start - 1] * pow[end - start + 1] % m + m) % m;
                writer.write(String.valueOf(res));
                writer.newLine();
            }
        }
    }

    private static int readInteger(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static int[] readArr(BufferedReader reader) throws IOException {
        String[] arr = reader.readLine().split(" ");
        return Arrays.stream(arr).mapToInt(elem -> Integer.parseInt(elem)).toArray();
    }
}
