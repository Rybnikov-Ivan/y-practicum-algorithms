package ru.ypracticum.sprint1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class K {
    private static List<Integer> getSum(List<Integer> numberList, int k) {
        List<Integer> kList = new ArrayList<>();
        for (char c : String.valueOf(k).toCharArray()) {
            kList.add(Integer.parseInt(String.valueOf(c)));
        }

        if (numberList.size() >= kList.size()) {
            return getResult(numberList, kList);
        } else {
            return getResult(kList, numberList);
        }
    }

    private static List<Integer> getResult(List<Integer> maxList, List<Integer> minList) {
        List<Integer> result = new ArrayList<>();
        boolean shift = false;

        int maxSize = maxList.size() - 1;
        int minSize = minList.size() - 1;
        for (int i = maxSize; i >= 0; i--) {
            if (minSize < 0) {
                int num = maxList.get(maxSize);
                if (shift) {
                    if (num >= 10) {
                        result.add(num - 10 + 1);
                    } else {
                        result.add(num + 1);
                    }
                    shift = false;
                } else {
                    result.add(num);
                }
                maxSize -= 1;
            } else {
                int sum = maxList.get(maxSize) + minList.get(minSize);
                if (sum >= 10) {
                    if (shift) {
                        result.add(sum - 10 + 1);
                    } else {
                        shift = true;
                        result.add(sum - 10);
                    }
                } else {
                    if (shift) {
                        if (sum + 1 >= 10) {
                            result.add(sum - 10 + 1);
                        } else {
                            result.add(sum + 1);
                            shift = false;
                        }
                    } else {
                        result.add(sum);
                    }
                }
                maxSize -= 1;
                minSize -= 1;
            }
        }
        if (shift) {
            result.add(1);
        }

        Collections.reverse(result);

        return result;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int numberLength = readInt(reader);
            List<Integer> numberList = readList(reader);
            int k = readInt(reader);
            List<Integer> sum = getSum(numberList, k);
            for (int elem : sum) {
                writer.write(elem + " ");
            }
        }
    }

    private static List<Integer> readList(BufferedReader reader) throws IOException {
        return Arrays.asList(reader.readLine().split(" "))
                .stream()
                .map(elem -> Integer.parseInt(elem))
                .collect(Collectors.toList());
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }
}
