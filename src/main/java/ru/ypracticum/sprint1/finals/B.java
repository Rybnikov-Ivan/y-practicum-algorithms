package ru.ypracticum.sprint1.finals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// https://contest.yandex.ru/contest/22450/run-report/105900133/
public class B {

    private static int getScore(int n, int[] numbers) {
        int score = 0;
        for (int number : numbers) {
            if (number <= n * 2 && number != 0) {
                score += 1;
            }
        }

        return score;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = readInt(reader);
            int[] numbers = new int[10];
            for (int i = 0; i < 4; i++) {
                for (char number : readArr(reader)) {
                    if (number != ('.')) {
                        numbers[Integer.parseInt(String.valueOf(number))] += 1;
                    }
                }
            }
            int score = getScore(n, numbers);
            System.out.println(score);
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static char[] readArr(BufferedReader reader) throws IOException {
        return reader.readLine().toCharArray();
    }
}
