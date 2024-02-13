package ru.ypracticum.sprint2.L;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class L {

    private static int getCommitsValue(int n, int k) {
        if (n == 0 || n == 1) {
            return 1;
        }
        int[] arr = new int[n + 1];
        arr[0] = 1;
        arr[1] = 1;

        for (int i = 2; i < n + 1; i++) {
            int pow = (int) Math.pow(10, k);
            int s = (arr[i - 1] + arr[i - 2]);
            arr[i] = s % pow;
        }
        return arr[n];
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int[] nums = readArr(reader);
            System.out.println(getCommitsValue(nums[0], nums[1]));
        }
    }

    private static int[] readArr(BufferedReader reader) throws IOException {
        String[] s = reader.readLine().split(" ");
        return Arrays.stream(s).mapToInt(o -> Integer.parseInt(o)).toArray();
    }
}
