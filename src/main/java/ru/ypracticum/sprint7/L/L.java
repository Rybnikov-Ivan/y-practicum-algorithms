package ru.ypracticum.sprint7.L;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class L {

    private static int getMaxWeight(int n, int m, int[] weights) {
        int[] dp = new int[m + 1];

        for (int weight : weights) {
            for (int i = m; i >= weight; i--) {
                dp[i] = Math.max(dp[i], weight + dp[i - weight]);
            }
        }


        return dp[m];
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int[] nm = readArr(reader);

            int n = nm[0];
            int m = nm[1];

            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int[] weights = new int[n + 1];
            for (int i = 1; i < n + 1; i++) {
                weights[i] = Integer.parseInt(tokenizer.nextToken());
            }
            System.out.println(getMaxWeight(n, m, weights));
        }
    }


    private static int[] readArr(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

}
