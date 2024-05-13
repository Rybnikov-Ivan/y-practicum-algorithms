package ru.ypracticum.sprint7.K;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class K {
    private static int n;
    private static int[] fArr;
    private static int m;
    private static int[] sArr;
    private static List<Integer> fSeq;
    private static List<Integer> sSeq;

    private static int[][] getDp() {
        int[][] dp = new int[n + 1][m + 1];

        for (int i = 0; i < n; i++) {
            int fVal = fArr[i];

            for (int j = 0; j < m; j++) {
                int sVal = sArr[j];
                if (fVal == sVal) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                } else {
                    dp[i + 1][j + 1] = Math.max(dp[i][j + 1], dp[i + 1][j]);
                }
            }
        }

        return dp;
    }

    private static void getSubsequences(int[][] dp) {
        fSeq = new ArrayList<>();
        sSeq = new ArrayList<>();

        int i = fArr.length - 1;
        int j = sArr.length - 1;

        while (i >= 0 && j >= 0) {
            if (fArr[i] == sArr[j]) {
                fSeq.add(i);
                sSeq.add(j);
                i -= 1;
                j -= 1;
            } else {
                if (dp[i + 1][j + 1] == dp[i][j + 1]) {
                    i -= 1;
                } else {
                    j -= 1;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            n = readInteger(reader);
            fArr = readArr(reader);
            m = readInteger(reader);
            sArr = readArr(reader);

            int[][] dp = getDp();
            System.out.println(dp[n][m]);
            getSubsequences(dp);

            for (int i = fSeq.size() - 1; i >= 0; i--) {
                System.out.print((fSeq.get(i) + 1) + " ");
            }
            System.out.println();
            for (int i = sSeq.size() - 1; i >= 0; i--) {
                System.out.print((sSeq.get(i) + 1) + " ");
            }
        }
    }

    private static int readInteger(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }


    private static int[] readArr(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}
