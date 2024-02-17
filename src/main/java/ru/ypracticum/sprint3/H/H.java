package ru.ypracticum.sprint3.H;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class H {

    private static String getMaxNumber(String[] arr) {

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (j + 1 < arr.length && isBig(arr[j], arr[j + 1])) {
                    String buf = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = buf;
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = arr.length - 1; i >= 0; i--) {
            sb.append(arr[i]);
        }

        return sb.toString();
    }

    private static boolean isBig(String a, String b) {
        return Integer.parseInt(a + b) > Integer.parseInt(b + a);
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int n = readInt(reader);

            String[] arr = reader.readLine().split(" ");

            writer.write(getMaxNumber(arr));
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }
}
