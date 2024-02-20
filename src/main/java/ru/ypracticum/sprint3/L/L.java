package ru.ypracticum.sprint3.L;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class L {
    private static int getDay(int[] arr, int k, int left, int right) {

        while (left <= right) {
            int mid = left + (right - left) / 2;

            int val = arr[mid];
            if (val >= k && (mid == 0 || k > arr[mid - 1])) {
                return mid + 1;
            } else if (val >= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return -1;

    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int n = readInt(reader);
            int[] arr = readArr(reader);
            int k = readInt(reader);

            int firstDay = getDay(arr, k, 0, arr.length - 1);
            int secondDay = -1;
            if (firstDay == -1) {
                writer.write(firstDay + " " + secondDay);
            } else {
                secondDay = getDay(arr, 2 * k, 0, arr.length - 1);
                writer.write(firstDay + " " + secondDay);
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
