package ru.ypracticum.sprint7.F;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class F {

    private static int getCount(int n, int k) {
        int mod = 1_000_000_007;
        int[] dp = new int[n];
        dp[0] = 1;

        for (int i = 1; i < n; i++) {
            for (int j = Math.max(i - k, 0); j < i; j++) {
                dp[i] = (dp[i] + dp[j]) % mod;
            }
        }

        return dp[n - 1];
    }
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int[] arr = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int n = arr[0];
            int k = arr[1];

            System.out.println(getCount(n, k));
        }
    }

    private static int readInteger(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }
}
