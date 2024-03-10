package ru.ypracticum.sprint4.G;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class G {

    private static int getMaxRounds(int[] arr) {
        if (arr.length == 1) {
            return 0;
        }
        if (arr.length == 2) {
            if (arr[0] == arr[1]) return 0;
            else return 2;
        }
        Map<Integer, Integer> map = new HashMap<>();

        int sum = 0;
        int res = 0;
        map.put(0, -1);
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 1) {
                sum += 1;
            } else {
                sum -= 1;
            }

            if (!map.containsKey(sum)) {
                map.put(sum, i);
            } else {
                Integer val = map.get(sum);
                res = Math.max(res, i - val);
            }
        }

        return res;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int n = readInt(reader);
            if (n == 0) {
                writer.write(String.valueOf(0));
                return;
            }
            int[] rounds = readArr(reader);

            writer.write(String.valueOf(getMaxRounds(rounds)));
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
