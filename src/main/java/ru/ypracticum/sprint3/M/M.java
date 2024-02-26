package ru.ypracticum.sprint3.M;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class M {

    private static double getMedian(int n, int[] lArr, int m, int[] rArr) {
        int[] resArr = new int[n + m];

        for (int i = 0; i < n; i++) {
            resArr[i] = lArr[i];
        }

        for (int i = n; i < n + m; i++) {
            resArr[i] = rArr[i - n];
        }

        Arrays.sort(resArr);

        int mid = (n + m) / 2;
        if (resArr.length % 2 == 0) {
            return (double) (resArr[mid] + resArr[mid - 1]) / 2;
        } else {
            return resArr[mid];
        }
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int n = readInt(reader);
            int m = readInt(reader);
            int[] fArr = readArr(reader);
            int[] sArr = readArr(reader);

            writer.write(String.valueOf(getMedian(n, fArr, m, sArr)));
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
