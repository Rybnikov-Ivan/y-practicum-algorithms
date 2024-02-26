package ru.ypracticum.sprint3.G;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class G {

    private static int[] getSortedArr(int[] arr) {
        int countZero = 0;
        int countOne = 0;
        int countTwo = 0;

        for (int j : arr) {
            if (j == 0) {
                countZero += 1;
            } else if (j == 1) {
                countOne += 1;
            } else if (j == 2) {
                countTwo += 1;
            }
        }

        return new int[]{countZero, countOne, countTwo};
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int n = readInt(reader);
            if (n == 0) {
                return;
            }
            int[] arr = readArr(reader);

            int res = 0;
            for (int i : getSortedArr(arr)) {
                for (int j = 0; j < i; j++) {
                    writer.write(String.valueOf(res));
                    writer.write(" ");
                }
                res += 1;
            }

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
