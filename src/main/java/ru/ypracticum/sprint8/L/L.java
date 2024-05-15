package ru.ypracticum.sprint8.L;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class L {

    private static int[] getPrefixFunction(String line) {
        int n = line.length();
        int[] pi = new int[n];

        for (int i = 1; i < n; i++) {
            int k = pi[i - 1];
            while (k > 0 && line.charAt(k) != line.charAt(i)) {
                k = pi[k - 1];
            }
            if (line.charAt(k) == line.charAt(i)) {
                k += 1;
            }
            pi[i] = k;
        }

        return pi;
    }
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            String line = reader.readLine();

            for (int i : getPrefixFunction(line)) {
                writer.write(i + " ");
            }

        }
    }
}
