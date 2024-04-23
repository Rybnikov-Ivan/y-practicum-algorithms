package ru.ypracticum.sprint6.B;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class B {
    private static int n;
    private static int m;
    private static int[][] arr;

    private static void change(int n, int m) {
        arr[n - 1][m - 1] = 1;
    }

    public static void main(String[] args) throws IOException {
        read();

        write();
    }

    private static void write() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))){
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    writer.write(arr[i][j] + " ");
                }
                writer.newLine();
            }
        }
    }

    private static void read() throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            int[] input = readArr(reader);
            n = input[0];
            arr = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    arr[i][j] = 0;
                }
            }

            m = input[1];
            for (int i = 0; i < m; i++) {
                int[] pair = readArr(reader);
                change(pair[0], pair[1]);
            }
        }
    }

    private static int[] readArr(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}
