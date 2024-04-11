package ru.ypracticum.sprint5.I;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class I {
    private static int result = 0;
    private static int getCountTrees(int n) {
        getCountTrees(n, 0, 0, 0);
        return result;
    }

    private static void getCountTrees(int n, int left, int right, int count) {
        if (count == n * 2) {
            result += 1;
        } else {
            if (left < n) {
                getCountTrees(n, left + 1, right, count + 1);
            }

            if (right < left) {
                getCountTrees(n, left, right + 1, count + 1);
            }
        }
    }
    public static void main(String[] args) throws IOException {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = readInteger(reader);
            System.out.println(getCountTrees(n));
        }
    }

    private static int readInteger(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }
}
