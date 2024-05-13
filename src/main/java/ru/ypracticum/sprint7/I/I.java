package ru.ypracticum.sprint7.I;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class I {

    private static String getWay(int[][] dp, int n, int m) {
        StringBuilder sb = new StringBuilder();

        int i = n - 1;
        int j = m - 1;

        while (i > 0 || j > 0) {
            int upp = i == 0 ? Integer.MIN_VALUE : dp[i - 1][j];
            int right = j == 0 ? Integer.MIN_VALUE : dp[i][j - 1];
            if (upp > right) {
                i = i - 1;
                sb.append("U");
            } else {
                j = j - 1;
                sb.append("R");
            }
        }

        return sb.reverse().toString();
    }

    private static int[][] getPath(int[][] values, int n, int m) {
        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int left = j > 0 ? dp[i][j - 1] : 0;
                int bottom = i > 0 ? dp[i - 1][j] : 0;
                dp[i][j] = Math.max(left, bottom) + values[i][j];
            }
        }
        return dp;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int[] input = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int n = input[0];
            int m = input[1];

            int[][] arr = new int[n][m];
            for (int i = n - 1; i >= 0; i--) {
                String str = reader.readLine();
                for (int j = 0; j < m; j++) {
                    arr[i][j] = Integer.parseInt(String.valueOf(str.charAt(j)));
                }
            }

            int[][] dp = getPath(arr, n, m);

            System.out.println(dp[n - 1][m - 1]);
            String way = getWay(dp, n, m);
            System.out.println(way);
        }
    }
}
