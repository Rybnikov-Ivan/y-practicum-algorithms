package ru.ypracticum.sprint3.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class D {
    private static int getCountChildren(int n, int[] factor, int m, int[] size) {
        Arrays.sort(factor);
        Arrays.sort(size);

        int res = 0;
        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = index; j < m; j++) {
                if (factor[i] <= size[j]) {
                    res += 1;
                    index = j + 1;
                    break;
                }
            }
        }

        return res;
    }
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            int n = readInt(reader);
            int[] factor = readArr(reader);
            int m = readInt(reader);
            int[] size = readArr(reader);

            System.out.println(getCountChildren(n, factor, m, size));
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
