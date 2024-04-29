package ru.ypracticum.sprint7.H;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class H {
    private static int getCount(int[][] arr, int n, int m) {
        int[][] dp = new int[n + 1][m + 1];


        for (int i = n; i > 0; i--) {
            for (int j = 0; j < m; j++) {
                dp[i - 1][j + 1] = arr[i - 1][j] + Math.max(dp[i - 1][j], dp[i][j + 1]);
            }
        }

        return dp[0][m];
    }
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int[] input = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int n = input[0];
            int m = input[1];

            int[][] arr = new int[n][];
            for (int i = 0; i < n; i++) {
                String str = reader.readLine();
                int[] tmp = new int[m];
                for (int j = 0; j < str.length(); j++) {
                    tmp[j] = Integer.parseInt(String.valueOf(str.charAt(j)));
                }
                arr[i] = tmp;
            }
            System.out.println(getCount(arr, n, m));
        }
    }
}
