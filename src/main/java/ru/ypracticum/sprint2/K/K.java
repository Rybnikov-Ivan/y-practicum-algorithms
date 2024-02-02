package ru.ypracticum.sprint2.K;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class K {

    private static int getCommitsValue(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        return getCommitsValue(n - 1) + getCommitsValue(n - 2);
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int internValue = readInt(reader);
            System.out.println(getCommitsValue(internValue));
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }
}
