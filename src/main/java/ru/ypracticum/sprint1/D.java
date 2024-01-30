package ru.ypracticum.sprint1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class D {
    private static int getWeatherRandomness(List<Integer> temperatures) {
        int result = 0;
        if (temperatures.size() == 1) {
            return 1;
        }

        Integer first = temperatures.get(0);
        Integer second = temperatures.get(1);
        if (first > second) {
            result += 1;
        }

        for (int i = 1; i < temperatures.size() - 1; i++) {
            Integer now = temperatures.get(i);
            Integer past = temperatures.get(i - 1);
            Integer future = temperatures.get(i + 1);

            if (past < now && future < now) {
                result += 1;
            }
        }

        Integer last = temperatures.get(temperatures.size() - 1);
        Integer secondLast = temperatures.get(temperatures.size() - 2);
        if (last > secondLast) {
            result += 1;
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int numberOfDays = readInt(reader);
            List<Integer> temperatures = readList(reader);
            int chaosNumber = getWeatherRandomness(temperatures);
            System.out.println(chaosNumber);
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static List<Integer> readList(BufferedReader reader) throws IOException {
        return Arrays.asList(reader.readLine().split(" "))
                .stream()
                .map(elem -> Integer.parseInt(elem))
                .collect(Collectors.toList());
    }
}
