package ru.ypracticum.sprint3.P;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class P {
    private static int getMaxBlocks(int[] arr) {

        int res = 0;
        Set<Integer> fSet = new HashSet<>();
        Set<Integer> sSet = new HashSet<>();

        for (int i = 0; i < arr.length; i++) {
            fSet.clear();
            for (int j = 0; j < i; j++) {
                fSet.add(arr[j]);
            }

            sSet.clear();
            for (int j = 0; j < i; j++) {
                sSet.add(j);
            }

            if (fSet.equals(sSet)) {
                res += 1;
            }
        }

        return res;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int n = readInt(reader);
            int[] arr = readArr(reader);

            writer.write(String.valueOf(getMaxBlocks(arr)));
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
