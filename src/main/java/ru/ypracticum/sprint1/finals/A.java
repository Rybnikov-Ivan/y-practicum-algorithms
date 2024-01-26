package ru.ypracticum.sprint1.finals;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class A {

    private static int[] getDirections(int[] houseNumbers) {
        int[] result = new int[houseNumbers.length];
        int zeroNumbers = 0;

        int direction = 0;
        for (int i = 0; i < houseNumbers.length; i++) {
            if (houseNumbers[i] == 0) {
                result[i] = 0;
                zeroNumbers += 1;
                direction = 0;
            } else {
                direction += 1;
                result[i] = direction;
            }
        }

        int initialZeroNumbers = zeroNumbers;
        direction = 0;
        for (int i = houseNumbers.length - 1; i >= 0; i--) {
            if (result[i] == 0) {
                direction = 0;
                zeroNumbers -= 1;
            } else {
                direction += 1;
                if (initialZeroNumbers != zeroNumbers) {
                    if (result[i] > direction || zeroNumbers == 0) {
                        result[i] = direction;
                    }
                }
            }
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = readInt(reader);
            int[] houseNumbers = readList(reader);
            int[] directions = getDirections(houseNumbers);
            for (int elem : directions) {
                writer.write(elem + " ");
            }
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static int[] readList(BufferedReader reader) throws IOException {
        String[] arr = reader.readLine().split(" ");
        return Arrays.stream(arr).mapToInt(elem -> Integer.parseInt(elem)).toArray();
    }
}
