package ru.ypracticum.sprint2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class A {
    private static int[][] getTransposeMatrix(int n, int m, int[][] arr) {
        int[][] result = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = arr[j][i];
            }
        }

        return result;
    }
    public static void main(String[] args) throws Exception {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = readInt(reader);
            int m = readInt(reader);
            int[][] arr = new int[n][];
            for (int i = 0; i < n; i++) {
                arr[i] = readArr(reader);
            }

            int[][] transposeMatrix = getTransposeMatrix(n, m, arr);
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    writer.write(transposeMatrix[i][j] + " ");
                }
                writer.newLine();
            }
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static int[] readArr(BufferedReader reader) throws IOException {
        String[] arr = reader.readLine().split(" ");
        return Arrays.stream(arr).mapToInt(elem -> Integer.parseInt(elem)).toArray();
    }
}
