package ru.ypracticum.sprint3.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class E {

    private static int getCountHouses(int n, int k, int[] costs) {
        Arrays.sort(costs);
        int result = 0;
        for (int i = 0; i < n; i++) {
            k -= costs[i];
            if (k >= 0) {
                result += 1;
            } else {
                break;
            }
        }
        return result;
    }
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int n = readInt(tokenizer.nextToken());
            int k = readInt(tokenizer.nextToken());
            int[] costs = readArr(reader);


            System.out.println(getCountHouses(n, k, costs));
        }
    }
    private static int readInt(String token) throws IOException {
        return Integer.parseInt(token);
    }

    private static int[] readArr(BufferedReader reader) throws IOException {
        String[] arr = reader.readLine().split(" ");
        return Arrays.stream(arr).mapToInt(elem -> Integer.parseInt(elem)).toArray();
    }
}
