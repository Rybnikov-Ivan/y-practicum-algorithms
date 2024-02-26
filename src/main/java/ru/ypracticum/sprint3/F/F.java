package ru.ypracticum.sprint3.F;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class F {

    private static int getMaxPerimeter(int[] arr) {
        Arrays.sort(arr);
        int len = arr.length - 1;
        int res = 0;
        for (int i = 0; i < len - 1; i++) {
            int a = arr[i];
            int b = arr[i + 1];
            int c = arr[i + 2];
            if (c < a + b) {
                int perimeter = a + b + c;
                res = Math.max(res, perimeter);
            }
        }

        return res;
    }
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            int n = readInt(reader);
            int[] arr = readArr(reader);

            System.out.println(getMaxPerimeter(arr));
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
