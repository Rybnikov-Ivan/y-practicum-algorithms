package ru.ypracticum.sprint7.finals.B;

/*
-- ПРИНЦИП РАБОТЫ --
В данной задаче я реализовал поиска разбиения массива чисел на 2 части таким образом, что их суммы равны

В начале я проверяю условие что сумма, всех элементов четная. В противном случае, разбиение не возможно

Далее завожу массив типа boolean длины половины суммы входного массива. Этот массив хранит true/false в зависимости от того,
возможно ли разбиение или нет.

Базовый случай - dp[0] = true

Во вложенном цикле я проверяю является ли элемент в массиве признаком разбиения, если нет, обновляю его значение.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Временная сложность составляет O(n), где n количество элементов в исходном массиве

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Пространственная сложность составляет O(n + sum(n)/2), где n - количество элементов в первом массиве, n/2 -
количество элементов в массиве dp
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://contest.yandex.ru/contest/25597/run-report/113412097/
public class B {

    private static int[] arr;

    private static boolean isTwoSum() {
        int sum = getSum();
        if (sum % 2 != 0) {
            return false;
        }

        int halfSum = sum / 2;
        boolean[] dp = new boolean[halfSum + 1];
        dp[0] = true;

        for (int val : arr) {
            if (val == halfSum) {
                return true;
            }
            if (val > halfSum) {
                return false;
            }

            for (int i = halfSum; i > val - 1; i--) {
                dp[i] = dp[i] || dp[i - val];
            }
        }

        return dp[dp.length - 1];
    }

    private static int getSum() {
        return Arrays.stream(arr).sum();
    }

    public static void main(String[] args) throws IOException {
        input();

        boolean res = isTwoSum();

        output(res);
    }

    private static void output(boolean res) {
        if (res) {
            System.out.println("True");
        } else {
            System.out.println("False");
        }
    }

    private static void input() throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = readInteger(reader);
            arr = new int[n];

            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(tokenizer.nextToken());
            }
        }
    }

    private static int readInteger(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }
}
