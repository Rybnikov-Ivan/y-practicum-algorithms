package ru.ypracticum.sprint7.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class C {
    private static long getSum(int m, List<Gold> golds) {
        Collections.sort(golds);
        long sum = 0;

        for (Gold gold : golds) {
            if (m > 0) {
                for (int i = 0; i < gold.getKg(); i++) {
                    if (m > 0) {
                        sum += gold.cost;
                        m -= 1;
                    } else {
                        break;
                    }
                }
            } else {
                break;
            }
        }

        return sum;
    }
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int m = readInteger(reader);
            int n = readInteger(reader);

            List<Gold> list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                int[] arr = readArr(reader);
                list.add(new Gold(arr[0], arr[1]));
            }

            System.out.println(getSum(m, list));
        }
    }

    private static int readInteger(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static int[] readArr(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    static class Gold implements Comparable<Gold> {
        private long cost;
        private long kg;

        public long getCost() {
            return cost;
        }

        public long getKg() {
            return kg;
        }

        public Gold(long cost, long kg) {
            this.cost = cost;
            this.kg = kg;
        }

        @Override
        public int compareTo(Gold o) {
            return (int) (o.cost - this.cost);
        }
    }
}
