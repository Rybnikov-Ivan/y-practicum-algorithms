package ru.ypracticum.sprint7.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class A {

    private static int getProfit(int[] arr) {
        int profit = 0;
        for (int i = 1; i < arr.length; i++) {
            int dif = arr[i] - arr[i - 1];
            if (dif > 0) {
                profit += dif;
            }
        }

        return profit;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = readInteger(reader);
            int[] arr = readArr(reader);

            System.out.println(getProfit(arr));
        }
    }

    private static int readInteger(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static int[] readArr(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}
