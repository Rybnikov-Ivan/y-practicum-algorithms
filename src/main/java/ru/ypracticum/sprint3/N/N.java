package ru.ypracticum.sprint3.N;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class N {
    private static List<int[]> getCoordinate(int[][] arr) {
        List<int[]> res = new ArrayList<>();
        Arrays.sort(arr, Comparator.comparing(o -> o[0]));

        int left = arr[0][0];
        int right = arr[0][1];
        for (int i = 1; i < arr.length; i++) {
            if (right >= arr[i][0]) {
                right = Math.max(right, arr[i][1]);
            } else {
                res.add(new int[]{left, right});
                left = arr[i][0];
                right = arr[i][1];
            }

        }

        res.add(new int[]{left, right});

        return res;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int n = readInt(reader);
            int[][] arr = new int[n][2];
            for (int i = 0; i < n; i++) {
                arr[i] = readArr(reader);
            }

            for (int[] coordinates : getCoordinate(arr)) {
                writer.write(String.valueOf(coordinates[0]));
                writer.write(" ");
                writer.write(String.valueOf(coordinates[1]));
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
