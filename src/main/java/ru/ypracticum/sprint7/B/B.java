package ru.ypracticum.sprint7.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class B {
    private static List<Less> getProfit(List<Less> lessons) {
        lessons.sort((o1, o2) -> {
            if (o1.end == o2.end) {
                return Double.compare(o1.start, o2.start);
            }
            return Double.compare(o1.end, o2.end);
        });

        List<Less> res = new ArrayList<>();

        Less prev = new Less(0, 0);
        for (Less l : lessons) {
            if (prev.end <= l.start) {
                prev = l;
                res.add(prev);
            }
        }


        return res;
    }
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = readInteger(reader);
            List<Less> lessons = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                double[] arr = readArr(reader);
                lessons.add(new Less(arr[0], arr[1]));
            }

            List<Less> res = getProfit(lessons);
            System.out.println(res.size());
            for (Less less : res) {
                String start = BigDecimal.valueOf(less.start).stripTrailingZeros().toPlainString();
                String end = BigDecimal.valueOf(less.end).stripTrailingZeros().toPlainString();
                System.out.println(start + " " + end);
            }
        }
    }

    private static int readInteger(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static double[] readArr(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" ")).mapToDouble(Double::parseDouble).toArray();
    }

    static class Less {
        double start;
        double end;

        public Less(double start, double end) {
            this.start = start;
            this.end = end;
        }
    }
}
