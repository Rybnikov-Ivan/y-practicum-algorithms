package ru.ypracticum.sprint3.J;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class J {
    private static void bubbleSorting(int[] arr, BufferedWriter writer) throws IOException {

        boolean isSorted = true;
        for (int i = 0; i < arr.length; i++) {
            boolean elementChanged = false;
            for (int j = 0; j < arr.length - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    elementChanged = true;
                    isSorted = false;
                    int buf = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = buf;
                }
            }
            if (elementChanged) {
                print(arr, writer);
            } else if (isSorted) {
                print(arr, writer);
                break;
            }
        }
    }

    private static void print(int[] arr, BufferedWriter writer) throws IOException {
        for (int j : arr) {
            writer.write(j + " ");
        }

        writer.newLine();
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int n = readInt(reader);
            int[] arr = readArr(reader);
            bubbleSorting(arr, writer);

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
